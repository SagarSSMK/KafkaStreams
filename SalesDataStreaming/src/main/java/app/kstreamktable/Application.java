package app.kstreamktable;

import config.Configuration;
import mapper.SalesMapper;
import mapper.streamtable.SalesTableLookUpJoiner;
import model.Key;
import model.Sales;
import model.aggregation.HigherAndLowereResults;
import model.streamtablejoin.LookUpResults;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.Consumed;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.*;
import serdes.StreamsSerdes;

public class Application {

    public static void main(String[] args) {

        StreamsConfig streamsConfig = new StreamsConfig(Configuration.getConfig());

        // Stream builder initialization to read from source topic
        StreamsBuilder builder = new StreamsBuilder();

        Serde<String> stringSerde = Serdes.String();
        Serde<Sales> salesSerde = StreamsSerdes.salesSerde();
        Serde<HigherAndLowereResults> higherAndLowereResultsSerde= StreamsSerdes.aggregationSerde();
        Serde<LookUpResults> lookUpResultsSerde= StreamsSerdes.lookUpResultsSerde();

        /** #########################################################################################################################*/

        /** #########################################################################################################################*/
                                                            //Kstream ktable join AGGREGATION
        /** #########################################################################################################################*/

        SalesMapper salesMapper= new SalesMapper();

        KStream<Key, Sales> salesStream = builder.stream("salesReport", Consumed.with(stringSerde, stringSerde)).flatMap(salesMapper);

        KStream<String,Sales> changedKey= salesStream.selectKey((k,v)->v.getRegion());
        changedKey.print(Printed.<String, Sales>toSysOut().withLabel("Changed Key"));

        KTable<String, HigherAndLowereResults> aggByRegionAggregationKTable =
                salesStream
                        .groupBy((k,v)->v.getRegion(),Serialized.with(stringSerde,salesSerde)).aggregate(
                        HigherAndLowereResults::new,(k, v, agg)->agg.getAggregation(v)
                        ,Materialized.with(stringSerde,higherAndLowereResultsSerde));

        KTable<String,HigherAndLowereResults> agg= aggByRegionAggregationKTable.mapValues((v)->v.getAvg(v));
        agg.toStream().print(Printed.<String,HigherAndLowereResults>toSysOut().withLabel("Aggregate"));


        SalesTableLookUpJoiner salesTableLookUpJoiner= new SalesTableLookUpJoiner();

        KStream<String,LookUpResults> lookUpResultsKStream=changedKey.join(agg,salesTableLookUpJoiner,
                Joined.with(stringSerde,salesSerde,higherAndLowereResultsSerde));

       lookUpResultsKStream.print(Printed.<String, LookUpResults>toSysOut().withLabel("KStream and KTable join"));

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
