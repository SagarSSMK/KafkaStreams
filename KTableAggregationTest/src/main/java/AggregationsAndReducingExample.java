import collectors.FixedSizePriorityQueue;
import mappers.ShareVolumeMapper;
import mappers.StockTransactionMapper;
import model.ShareVolume;
import model.StockTransaction;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.*;
import org.apache.kafka.streams.kstream.*;
import org.apache.kafka.streams.processor.WallclockTimestampExtractor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import serdes.StreamsSerdes;

import java.util.Comparator;
import java.util.Iterator;
import java.util.Properties;

import static org.apache.kafka.streams.Topology.AutoOffsetReset.EARLIEST;

public class AggregationsAndReducingExample {

    private static Logger LOG= LoggerFactory.getLogger(AggregationsAndReducingExample.class);

    public static void main(String[] args) throws Exception{

        StreamsConfig streamsConfig = new StreamsConfig(getProperties());

        Serde<String> stringSerde = Serdes.String();
        Serde<StockTransaction> stockTransactionSerde= StreamsSerdes.StockTransactionSerde();
        Serde<ShareVolume> shareVolumeSerde= StreamsSerdes.ShareVolumeSerde();
        Serde<FixedSizePriorityQueue> fixedSizePriorityQueueSerde = StreamsSerdes.FixedSizePriorityQueueSerde();

        StreamsBuilder builder = new StreamsBuilder();

        StockTransactionMapper stockTransactionMapper= new StockTransactionMapper();
        ShareVolumeMapper shareVolumeMapper= new ShareVolumeMapper();

        Comparator<ShareVolume> comparator=(sv1,sv2) -> sv2.getShares()-sv1.getShares();
        FixedSizePriorityQueue<ShareVolume> fixedQueue= new FixedSizePriorityQueue<>(comparator,5);

       /* ValueMapper<FixedSizePriorityQueue,String> valueMapper= fpq->{
            StringBuilder stringBuilder = new StringBuilder();
            Iterator<ShareVolume> shareVolumeIterator= fpq.iterator();
        }*/

        /*KTable<String, ShareVolume> shareVolume = builder.stream("stocktransaction_report",
                Consumed.with(stringSerde, stockTransactionSerde)
                        .withOffsetResetPolicy(EARLIEST))
                .mapValues(st -> ShareVolume.newBuilder(st).build())
                .groupBy((k, v) -> v.getSymbol(), Serialized.with(stringSerde, shareVolumeSerde))
                .reduce(ShareVolume::sum);*/
        try {
    /*        KStream<String, StockTransaction> source = builder.stream("stocktransaction_report1",Consumed.with(stringSerde,stringSerde))
                    .flatMap(stockTransactionMapper);

            source.to("stocktransaction_report_serialized",Produced.with(stringSerde,stockTransactionSerde));

            source.print(Printed.toSysOut());*/
            KTable<String, ShareVolume> shareVolume = builder.stream("stocktransaction_report_serialized",
                    Consumed.with(stringSerde, stockTransactionSerde)
                            .withOffsetResetPolicy(EARLIEST))
                    .mapValues(st -> ShareVolume.newBuilder(st).build())
                    .groupBy((k, v) -> v.getSymbol(), Serialized.with(stringSerde, shareVolumeSerde))
                    .reduce(ShareVolume::sum);

          /*  KTable<String,ShareVolume> shareVolumeKTable= source.groupBy((k,v)->v.getSymbol(),Serialized.with(stringSerde,shareVolumeSerde))
                    .reduce(ShareVolume::sum);*/

        /*    shareVolumeKTable.groupBy((k,v)-> KeyValue.pair(v.getIndustry(),v),Serialized.with(stringSerde,
                    shareVolumeSerde)).aggregate(()->fixedQueue,
                    (k,v,agg)->agg.add(v),
                    (k,v,agg)->agg.remove(v),
                    Materialized.with(stringSerde,fixedSizePriorityQueueSerde))
                    .mapValues()*/

         //   source.to( "stocktransaction_report_test", Produced.with(stringSerde, shareVolumeSerde));

            shareVolume.toStream().print(Printed.toSysOut());

            KafkaStreams kafkaStreams = new KafkaStreams(builder.build(), streamsConfig);
            kafkaStreams.cleanUp();
            kafkaStreams.start();
            if(kafkaStreams!=null)
                System.out.println("Kafka Stream started");
            Thread.sleep(35000);
            kafkaStreams.close();
        }catch (Exception e){
            System.out.println("Exception "+e.getMessage());
        }




    }
    private static Properties getProperties() {
        Properties props = new Properties();
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, "share_volume");
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "share_volume");
        //props.put(ConsumerConfig.CLIENT_ID_CONFIG, "KTable-aggregations-client");
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        //props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "30000");
      //  props.put(StreamsConfig.COMMIT_INTERVAL_MS_CONFIG, "10000");
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "sagar.server.com:9092");
        //props.put(StreamsConfig.NUM_STREAM_THREADS_CONFIG, "1");
       // props.put(ConsumerConfig.METADATA_MAX_AGE_CONFIG, "10000");
        //props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
        //props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
       // props.put(StreamsConfig.REPLICATION_FACTOR_CONFIG, 1);
       props.put(StreamsConfig.DEFAULT_TIMESTAMP_EXTRACTOR_CLASS_CONFIG, WallclockTimestampExtractor.class);
        return props;

    }
}
