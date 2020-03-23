import mappers.FootBallMapper;
import model.Employee;
import model.FootBall;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.Consumed;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KTable;
import org.apache.kafka.streams.kstream.Printed;
import org.apache.kafka.streams.kstream.Produced;
import org.apache.kafka.streams.processor.WallclockTimestampExtractor;
import serdes.StreamsSerdes;

import java.util.Properties;

public class KTableVsKStreaming {

    public static void main(String[] args) {
        StreamsConfig streamsConfig = new StreamsConfig(getProperties());

        Serde<String> stringSerde = Serdes.String();
        Serde<FootBall> footBallSerde = StreamsSerdes.FootBallSerde();


        StreamsBuilder builder = new StreamsBuilder();
        FootBallMapper footBallMapper= new FootBallMapper();


        try {

            /*KStream<String, FootBall> footBallKStream= builder.stream("football",Consumed.with(stringSerde,stringSerde))
                    .flatMap(footBallMapper);

            footBallKStream.to("football_serialized", Produced.with(stringSerde,footBallSerde));*/
         /*   KStream<String,FootBall> footBallKStream= builder.stream("football_serialized",Consumed.with(stringSerde,
                    footBallSerde));
*/
            KTable<String,FootBall> footBallKTable= builder.table("football_serialized",Consumed.with(stringSerde,
                    footBallSerde));

           // footBallKStream.print(Printed.<String, FootBall>toSysOut().withLabel("KSTREAM"));
            footBallKTable.toStream().print(Printed.<String, FootBall>toSysOut()
                    .withLabel("Football-KTable"));

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
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, "KtableVsKstream");
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "KtableVsKstream");
        //props.put(ConsumerConfig.CLIENT_ID_CONFIG, "KTable-aggregations-client");
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        //props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "30000");
        //  props.put(StreamsConfig.COMMIT_INTERVAL_MS_CONFIG, "10000");
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "sagar.server.com:9092");
        //props.put(StreamsConfig.NUM_STREAM_THREADS_CONFIG, "1");
        // props.put(ConsumerConfig.METADATA_MAX_AGE_CONFIG, "10000");
        props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
        // props.put(StreamsConfig.REPLICATION_FACTOR_CONFIG, 1);
        props.put(StreamsConfig.DEFAULT_TIMESTAMP_EXTRACTOR_CLASS_CONFIG, WallclockTimestampExtractor.class);
        return props;

    }
}
