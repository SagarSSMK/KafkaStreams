package config;

import extractor.TransactionTimeExtractor;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.processor.WallclockTimestampExtractor;

import java.util.Properties;

public interface Configuration {

    public static Properties getConfig() {

        Properties properties = new Properties();
        properties.put(StreamsConfig.APPLICATION_ID_CONFIG, "SalesApplication32");
        properties.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "sagar.server.com:9092");
        properties.put(StreamsConfig.CLIENT_ID_CONFIG, "salesclientid32");
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, "salesconsumer50");

        properties.put(StreamsConfig.topicPrefix("retention.ms"), 120000);
        properties.put(StreamsConfig.STATE_DIR_CONFIG, "D:\\Github\\Data\\localstore\\kafkastreams");
      //  properties.put(StreamsConfig.DEFAULT_TIMESTAMP_EXTRACTOR_CLASS_CONFIG, WallclockTimestampExtractor.class);

        properties.put(StreamsConfig.DEFAULT_TIMESTAMP_EXTRACTOR_CLASS_CONFIG, TransactionTimeExtractor.class);
        //KTABLE properties
      //  properties.put(StreamsConfig.CACHE_MAX_BYTES_BUFFERING_CONFIG,100);
        return properties;

    }

    String LEAST_NUMBERS="LEAST_UNITS";
    String MODERATE_NUMBERS="MODERATE_UNITS";
    String CONSISTENCY_NUMBERS="CONSISTENCY_UNITS";
    String HIGHER_NUMBERS="HIGHESHT_UNITS";
    String EXTENDED_NUMBERS="EXTENDED_UNITS";

    double LEAST_NUMBER_NUMERICAL=400.0;
    double MODERATE_NUMBER_NUMERICAL=600.0;
    double CONSISTENCY_NUMBERS_NUMERICAL= 800.0;
    double HIGHER_NUMBERS_NUMERICAL=1000.0;

}
