package com.github.kafka.producer.Producer;

import com.github.kafka.producer.Producer.model.Purchase;
import com.github.kafka.producer.Producer.serdes.StreamsSerdes;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.Consumed;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Produced;

import java.util.Properties;

public class ProducerKafkaRecord {

    public static void main(String[] args) throws InterruptedException {

        Properties properties= new Properties();
        properties.put("bootstrap.servers","sagar.server.com:9092");
        properties.put("key.serializer","org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer","org.apache.kafka.common.serialization.StringSerializer");
        properties.put(StreamsConfig.APPLICATION_ID_CONFIG,"APplication_ID");
       // properties.put("")

        //Create a StreamsConfig instance.
        StreamsConfig streamsConfig = new StreamsConfig(properties);

        StreamsBuilder builder = new StreamsBuilder();

        //Create a Serde object.
        Serde<String> stringSerde = Serdes.String();
        Serde<Purchase> purchaseSerde = StreamsSerdes.purchaseSerde();

        KStream<String, String> simpleFirstStream = builder.stream("purchase_in", Consumed.with(stringSerde, stringSerde));

        //Construct a processing topology.
       // KStream<String,Purchase> streamToUpper= simpleFirstStream.filter((a,b)->b.getCustomerId().contentEquals("1236"));
        KStream<String,String> streamToUpper = simpleFirstStream.mapValues(String::toUpperCase);

        simpleFirstStream.to("motive_test_2", Produced.with(stringSerde,stringSerde));

        //Start the Kafka Streams program.
        KafkaStreams kafkaStreams = new KafkaStreams(builder.build(),streamsConfig);

        kafkaStreams.start();

        Thread.sleep(35000);
        kafkaStreams.close();
    }
}
