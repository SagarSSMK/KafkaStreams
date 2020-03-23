package app.ktable;

import config.Configuration;
import mapper.SalesMapper;
import model.Key;
import model.Sales;
import model.aggregation.AggOnKTable.AvgUnitsKey;
import model.aggregation.AggOnKTable.AvgUnitsValue;
import model.aggregation.AggOnKTable.Region;
import model.aggregation.AggOnKTable.RegionAggregate;
import model.aggregation.HigherAndLowereResults;
import model.aggregation.KeyAggByRegion;
import model.aggregation.avg.AvgValue;
import model.aggregation.avg.MappedData;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.*;
import org.apache.kafka.streams.kstream.*;
import serdes.StreamsSerdes;

import java.time.Duration;

public class Application {

    public static void main(String[] args) {

        StreamsConfig streamsConfig = new StreamsConfig(Configuration.getConfig());

        // Stream builder initialization to read from source topic
        StreamsBuilder builder = new StreamsBuilder();

        // key and value serde
        Serde<String> stringSerde = Serdes.String();

        Serde<Key> keySerde = StreamsSerdes.keySerde();
        Serde<Sales> salesSerde = StreamsSerdes.salesSerde();
        Serde<HigherAndLowereResults> aggregationSerde = StreamsSerdes.aggregationSerde();
        Serde<KeyAggByRegion> keyAggByRegionSerde = StreamsSerdes.keyAggByRegionSerde();
        Serde<AvgValue> avgValueSerde = StreamsSerdes.avgValueSerde();
        Serde<AvgUnitsKey> avgUnitsKeySerde=StreamsSerdes.avgUnitsKeySerde();
        Serde<AvgUnitsValue> avgUnitsValueSerde =StreamsSerdes.avgValueSerdeSerde();
        Serde<MappedData> mappedDataSerde=StreamsSerdes.mappedDataSerde();
        Serde<Region> regionSerde =StreamsSerdes.regionSerde();
        Serde<RegionAggregate> regionAggregateSerde = StreamsSerdes.regionAggregateSerde();

        //Mapper
        SalesMapper salesMapper = new SalesMapper();

        /** #########################################################################################################################*/
                                                //KSTREAM && KTABLE difference
        /** #########################################################################################################################*/

      //  KStream<Key, Sales> salesStream = builder.stream("salesreport", Consumed.with(stringSerde, stringSerde)).flatMap(salesMapper);

        //salesStream.to("salesTableReportWithKey", Produced.with(keySerde,salesSerde));

        //KStream<Key,Sales> salesKStream = builder.stream("salesTableReportWithKey",Consumed.with(keySerde,salesSerde));

         KTable<Key,Sales> keySalesKTable =builder.table("salesTableReportWithKey",Consumed.with(keySerde,salesSerde));

       // salesStream.print(Printed.<Key, Sales>toSysOut().withLabel("Stream"));
       keySalesKTable.toStream().print(Printed.<Key,Sales>toSysOut().withLabel("Table"));

        /** #########################################################################################################################*/

        /** #########################################################################################################################*/
                                                    //KTABLE AGGREGATION
        /** #########################################################################################################################*/

        KTable<Region,RegionAggregate> regionRegionAggregateKTable=keySalesKTable
                .groupBy((k,v)->KeyValue.pair(Region.Builder().region(v.getRegion()).build(),v),Serialized.with(regionSerde,salesSerde))
                .aggregate(RegionAggregate::new,
                        (k,v,ragg)->ragg.add(v),
                        (k,v,rsub)->rsub.sub(v),
                        Materialized.with(regionSerde,regionAggregateSerde));


        regionRegionAggregateKTable.toStream().print(Printed.<Region,RegionAggregate>toSysOut().withLabel("RegionAggregate"));

                /*

        KTable<AvgUnitsKey, AvgUnitsValue> keyAvgUnitsValueKTable = mappedDataKTable
                .groupBy((key,value)->KeyValue.pair(AvgUnitsKey.Builder()
                                .avgUnitCost(value.getAvgUnitCost()).build(),value),
                        Serialized.with(avgUnitsKeySerde,mappedDataSerde))
                .aggregate(AvgUnitsValue::new,(k,v,ag)->ag.aggregate(v),(k,v,sub)->sub.subtract(v),Materialized.with(avgUnitsKeySerde,avgUnitsValueSerde));

                 */

        /** #########################################################################################################################*/
        /** #########################################################################################################################*/

        /** #########################################################################################################################*/
                                                  //Aggregation by region
        /** #########################################################################################################################*/

       //   KeyValueMapper<Key,Sales,KeyAggByRegion> regionKeyValueMapper=(key,sales)-> KeyAggByRegion.newBuilder().region(sales.getRegion()).build();
        //modify key to region
       //  KStream<KeyAggByRegion,Sales> keyAggByRegionSalesKStream= salesStream.selectKey((k,v)->KeyAggByRegion.newBuilder().region(v.getRegion()).build());

       // KStream<String,Sales> keyStringByRegionSalesKStream= salesStream.selectKey((k,v)->v.getRegion());
        //keyStringByRegionSalesKStream.print(Printed.<String, Sales>toSysOut().withLabel("Changed Key to KeyAggByRegion"));

   /*     KTable<String, HigherAndLowereResults> aggByRegionAggregationKTable =
                salesStream
                        .groupBy((k,v)->v.getRegion(),Serialized.with(stringSerde,salesSerde)).aggregate(
                                HigherAndLowereResults::new,(k, v, agg)->agg.getAggregation(v)
                        ,Materialized.with(stringSerde,aggregationSerde));


        aggByRegionAggregationKTable.toStream().print(Printed.<String, HigherAndLowereResults>toSysOut().withLabel("Aggregation"));

        KTable<String,HigherAndLowereResults> agg= aggByRegionAggregationKTable.mapValues((v)->v.getAvg(v));

        KTable<String, MappedData> mappedDataKTable = agg.mapValues((v)-> v.mappedData(v));

        KTable<AvgUnitsKey, AvgUnitsValue> keyAvgUnitsValueKTable = mappedDataKTable
                .groupBy((key,value)->KeyValue.pair(AvgUnitsKey.Builder()
                                .avgUnitCost(value.getAvgUnitCost()).build(),value),
                        Serialized.with(avgUnitsKeySerde,mappedDataSerde))
                .aggregate(AvgUnitsValue::new,(k,v,ag)->ag.aggregate(v),(k,v,sub)->sub.subtract(v),Materialized.with(avgUnitsKeySerde,avgUnitsValueSerde));

        agg.toStream().print(Printed.<String, HigherAndLowereResults>toSysOut().withLabel("Avg found brother"));

        mappedDataKTable.toStream().print(Printed.<String,MappedData>toSysOut().withLabel("Mapped Data"));

        keyAvgUnitsValueKTable.toStream().print(Printed.<AvgUnitsKey,AvgUnitsValue>toSysOut().withLabel("Final Output"));*/

        /** #########################################################################################################################*/

        /** #########################################################################################################################*/
                                            //Aggregation on KTable
        /** #########################################################################################################################*/



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
