package app;

import com.github.kafka.producer.Producer.serdes.StreamsSerdes;
import mapper.PurchaseMapper;
import org.apache.hadoop.yarn.webapp.hamlet.HamletSpec;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.Consumed;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.KStream;
import purchase.Purchase;
import serdes.StreamSerdes;

import java.util.Properties;

public class StateStoreApp {

    public static void main(String[] args) throws InterruptedException {

        Properties properties= new Properties();
        properties.put("bootstrap.servers","sagar.server.com:9092");
        properties.put("key.serializer","org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer","org.apache.kafka.common.serialization.StringSerializer");
        properties.put(StreamsConfig.APPLICATION_ID_CONFIG,"APplication_ID");


        //Create a StreamsConfig instance.
        StreamsConfig streamsConfig = new StreamsConfig(properties);

        StreamsBuilder builder = new StreamsBuilder();

        //Create a Serde object.
        Serde<String> stringSerde = Serdes.String();
        Serde<Purchase> purchaseSerde = StreamSerdes.purchaseSerde();

        PurchaseMapper mapper= new PurchaseMapper();


        KStream<String, Purchase> simpleStream = builder.stream("purchase_in", Consumed.with(stringSerde, stringSerde)).flatMap(mapper);



    }
}
