import aggrgation.IndustrySalaryCount;
import mappers.EmployeeMapper;
import model.Employee;
import model.ShareVolume;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.protocol.types.Field;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.Consumed;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.*;
import org.apache.kafka.streams.processor.WallclockTimestampExtractor;
import serdes.StreamsSerdes;
import util.serializer.EmployeeDeserializer;
import util.serializer.EmployeeSerializer;

import java.util.Properties;

public class AggregareEmployee {

    public static void main(String[] args) {

        StreamsConfig streamsConfig = new StreamsConfig(getProperties());

        Serde<String> stringSerde = Serdes.String();
        Serde<Long> longSerde = Serdes.Long();

        Serde<Employee> employeeSerde = StreamsSerdes.EmployeeSerde();
      //  Serde<Employee> employeeSerde = Serdes.serdeFrom(new EmployeeSerializer(), new EmployeeDeserializer());
        Serde<IndustrySalaryCount> industrySalaryCountSerde = StreamsSerdes.IndustrySalaryCountSerde();

        StreamsBuilder builder = new StreamsBuilder();

        EmployeeMapper employeeMapper= new EmployeeMapper();

        try {

           /* KStream<String, Employee> employeeStream = builder.stream("employee_report",Consumed.with(stringSerde,stringSerde))
                    .flatMap(employeeMapper);*/

           // employeeStream.to("employee_report_serialized", Produced.with(stringSerde,employeeSerde));
           KStream<String,Employee> emplyeeSerializedStream = builder.stream("employee_report_serialized",
                   Consumed.with(stringSerde,employeeSerde));

            emplyeeSerializedStream.print(Printed.toSysOut().toSysOut());

            //  kStream.print(Printed.toSysOut());
            //employeeStream.print(Printed.toSysOut().toSysOut());

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
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, "Data1");
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "Data2");
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
