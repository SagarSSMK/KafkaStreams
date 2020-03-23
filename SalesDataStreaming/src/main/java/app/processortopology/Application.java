package app.processortopology;

import config.Configuration;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.serialization.Serializer;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.processor.UsePreviousTimeOnInvalidTimestamp;
import processor.SalesMethodTransfer;

public class Application {

    public static void main(String[] args) {

        StreamsConfig streamsConfig =  new StreamsConfig(Configuration.getConfig());

        Serde<String> stringSerde= Serdes.String();

        Serializer<String> stringSerializer= stringSerde.serializer();
        Deserializer<String> stringDeserializer = stringSerde.deserializer();

        Topology topology= new Topology();

        String salesPurchaseSource = "Sales";
        String offlineSales="Offline-Sales";
        String onlineSales="Online-Sales";
        String salePurchaseProcessor ="sale-processor";

        SalesMethodTransfer salesMethodTransfer= new SalesMethodTransfer(offlineSales,onlineSales);
        topology.addSource(Topology.AutoOffsetReset.EARLIEST,
                salesPurchaseSource,
                new UsePreviousTimeOnInvalidTimestamp(),
                stringDeserializer,
                stringDeserializer,
                "sales")
                .addProcessor(
                        salePurchaseProcessor,
                        ()->salesMethodTransfer,
                        salesPurchaseSource);
    }
}
