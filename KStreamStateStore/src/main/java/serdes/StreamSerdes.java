package serdes;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serializer;
import  com.github.kafka.producer.Producer.serializers.JsonSerializer;
import  com.github.kafka.producer.Producer.serializers.JsonDeserializer;
import purchase.Purchase;

import java.lang.reflect.Type;
import java.util.Map;

public class StreamSerdes {

    public static Serde<Purchase> purchaseSerde(){return new PurchaseSerde();}

    public static final class  PurchaseSerde extends AbstractSerde<Purchase>{
        PurchaseSerde(){
            super(new JsonSerializer<Purchase>(), new JsonDeserializer<>(Purchase.class));
        }

    }

    private static class AbstractSerde<T> implements  Serde<T>{

        private JsonSerializer<T> serializer;
        private JsonDeserializer<T> deserializer;

        AbstractSerde(JsonSerializer<T> serializer,JsonDeserializer<T> deserializer){
            this.serializer=serializer;
            this.deserializer=deserializer;
        }
        @Override
        public void configure(Map<String, ?> configs, boolean isKey) {

        }

        @Override
        public void close() {

        }

        @Override
        public Serializer<T> serializer() {
            return serializer;
        }

        @Override
        public Deserializer<T> deserializer() {
            return deserializer;
        }
    }
}
