package app.kstream;

import config.Configuration;
import mapper.SalesMapper;
import mapper.streamjoiner.SalesJoiner;
import model.Key;
import model.Sales;
import model.aggregation.HigherAndLowereResults;
import model.aggregation.KeyAggByRegion;
import model.joiners.CorrelatedSales;
import model.state.Occurances;
import model.state.OccurancesTransformer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.Consumed;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.*;
import org.apache.kafka.streams.state.KeyValueBytesStoreSupplier;
import org.apache.kafka.streams.state.KeyValueStore;
import org.apache.kafka.streams.state.StoreBuilder;
import org.apache.kafka.streams.state.Stores;
import serdes.StreamsSerdes;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class Application {

    final static Logger logger = Logger.getLogger("Application");

    public static void main(String[] args) throws Exception {

        StreamsConfig streamsConfig = new StreamsConfig(Configuration.getConfig());

        // Stream builder initialization to read from source topic
        StreamsBuilder builder = new StreamsBuilder();

        // key and value serde
        Serde<String> stringSerde = Serdes.String();

        Serde<Key> keySerde = StreamsSerdes.keySerde();
        Serde<Sales> salesSerde = StreamsSerdes.salesSerde();
        Serde<HigherAndLowereResults> aggregationSerde = StreamsSerdes.aggregationSerde();
        Serde<KeyAggByRegion> keyAggByRegionSerde = StreamsSerdes.keyAggByRegionSerde();

        //Mapper
        SalesMapper salesMapper = new SalesMapper();

        KStream<Key, Sales> salesStream = builder.stream("salesReport", Consumed.with(stringSerde, stringSerde)).flatMap(salesMapper);


        //   salesStream.print(Printed.<Key, Sales>toSysOut().withLabel("Sales Stream Data"));

        /** #########################################################################################################################*/
        //PREDICATING THE DATA based on offline and online transactions

        Predicate<Key, Sales> offlinePurchases = ((key, value) -> value.getSalesChannel().equalsIgnoreCase("Offline"));
        Predicate<Key, Sales> onlinePurchases = ((key, value) -> value.getSalesChannel().equalsIgnoreCase("Online"));

        int Offline = 0;//just for our convenience about the predicate array
        int Online = 1;

        KStream<Key, Sales>[] kStreamByStatus = salesStream.branch(offlinePurchases, onlinePurchases);

        //kStreamByStatus[Offline].print(Printed.<Key,Sales>toSysOut().withLabel("Offline Purchases"));
        //kStreamByStatus[Online].print(Printed.<Key,Sales>toSysOut().withLabel("Online Purchases"));

        /** #########################################################################################################################*/


        /** #########################################################################################################################*/
                                        //Put state now
        /** #########################################################################################################################*/

        String occurancesStoreName = "StoredOccurances";


        //make sure you have looked over correctly, if your key is distributed in  partition way, you cannot add the data as store stores
        //in a particular task(basically you get the wrong output)

        //This statement could be interpreted to mean that each partition has its own state store, but that’s not the case.
        // Partitions are assigned to a StreamTask, and each StreamTask has its own state store.

        //Adding a state store is a simple matter of creating a StoreSupplier instance with one of the static factory methods on the Stores class.
        KeyValueBytesStoreSupplier storeSupplier = Stores.inMemoryKeyValueStore(occurancesStoreName);

        //providing config for change log
        Map<String, String> changeLogConfigs = new HashMap<>();
        changeLogConfigs.put("retention.ms","172800000" );
        changeLogConfigs.put("retention.bytes", "10000000000");
        changeLogConfigs.put("cleanup.policy", "compact");

        StoreBuilder<KeyValueStore<String, Integer>> storeBuilder = Stores.keyValueStoreBuilder(storeSupplier, Serdes.String(), Serdes.Integer());
        // to use with a StoreBuilder
        storeBuilder.withLoggingEnabled(changeLogConfigs);
        // to use with Materialized
            //   Materialized.as(Stores.inMemoryKeyValueStore("foo").withLoggingEnabled(changeLogConfigs));

        builder.addStateStore(storeBuilder);

        KStream<Key, Occurances> occurancesByCountry = salesStream.transformValues(() -> new OccurancesTransformer(occurancesStoreName), occurancesStoreName);

       // occurancesByCountry.print(Printed.<Key, Occurances>toSysOut().withLabel("Adding Sold units"));

        /** #########################################################################################################################*/
                            //Let's do stream joining
        /** #########################################################################################################################*/

        //Changing key
        KStream<String,Sales> salesByCOuntry=salesStream.selectKey((k,v)->v.getCountry());

      //  salesByCOuntry.print(Printed.<String, Sales>toSysOut().withLabel("With country name as key"));

        Predicate<String,Sales> offlinePurchasesByCountry= (((key, value) -> value.getSalesChannel().equalsIgnoreCase("Offline")));
        Predicate<String,Sales> onlinePurchasesByCountry=(((key, value) -> value.getSalesChannel().equalsIgnoreCase("Online")));

        KStream<String,Sales>[] branchedResults= salesByCOuntry.branch(offlinePurchasesByCountry,onlinePurchasesByCountry);
        //branchedResults[Offline].print(Printed.<String, Sales>toSysOut().withLabel("Offline Purchases by Country"));
        //branchedResults[Online].print(Printed.<String, Sales>toSysOut().withLabel("Online Purchases by Country"));

        ValueJoiner<Sales,Sales, CorrelatedSales> joiner= new SalesJoiner();

        //JoinWindows.after—streamA.join(streamB,...,twentyMinuteWindow.after(5000)....) This specifies that the timestamp of the streamB record
        // is at most 5 seconds after the timestamp of the streamA record. The starting time boundary of the window is unchanged.

        //JoinWindows.before—streamA.join(streamB,...,twentyMinuteWindow.before(5000),...)
        // This specifies that the timestamp of the streamB record is at most 5 seconds before the timestamp of the streamA record.
        // The ending time boundary of the window is unchanged.
        JoinWindows twentyMinuteWindow =  JoinWindows.of(60 * 1000 * 20);

        KStream<String,CorrelatedSales> correlatedSalesKStream=  branchedResults[Online]
                .leftJoin(branchedResults[Offline]
                        ,joiner
                        ,twentyMinuteWindow.after(100),Joined.with(stringSerde,salesSerde,salesSerde));

        //correlatedSalesKStream.print(Printed.<String, CorrelatedSales>toSysOut().withLabel("JoinedOnlineOfflineStreamsBasedOnStream"));

        correlatedSalesKStream.filter((k,v)-> v.getOfflineUnitPrice()>0 &&
                                            v.getOnlineUnitPrice()>0 &&
                                            v.getOnlineUnitSold()>0 &&
                                            v.getOfflineUnitSold()>0);
               // .print(Printed.<String, CorrelatedSales>toSysOut().withLabel("JoinedWithFilter"));
        /** #########################################################################################################################*/

     /*   salesStream
                .groupBy((k,v)-> KeyAggByRegion.newBuilder()
                        .region(v.getRegion()).build(),
                        Serialized.with(keyAggByRegionSerde,salesSerde)
                        ).aggregate(Aggregation::new ,(k,v,agg)->agg.)*/


        KafkaStreams kafkaStreams = new KafkaStreams(builder.build(), streamsConfig);
        kafkaStreams.start();

        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                kafkaStreams.close();
            }
        }));
    }


}
