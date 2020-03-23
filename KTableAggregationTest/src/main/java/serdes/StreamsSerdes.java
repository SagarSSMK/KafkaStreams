package serdes;

import aggrgation.IndustrySalaryCount;
import collectors.FixedSizePriorityQueue;
import model.Employee;
import model.FootBall;
import model.ShareVolume;
import model.StockTransaction;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serializer;
import util.serializer.JsonDeserializer;
import util.serializer.JsonSerializer;

import java.util.Map;


public class StreamsSerdes {

    public static Serde<StockTransaction> StockTransactionSerde() {
        return new StockTransactionSerde();
    }

    public static Serde<ShareVolume> ShareVolumeSerde() {
        return new ShareVolumeSerde();
    }

    public static Serde<Employee> EmployeeSerde() {
        return new EmployeeSerde();
    }
    public static Serde<IndustrySalaryCount> IndustrySalaryCountSerde() {
        return new IndustrySalaryCountSerde();
    }

    public static Serde<FootBall> FootBallSerde() {
        return new FootBallSerde();
    }

    public static Serde<FixedSizePriorityQueue> FixedSizePriorityQueueSerde() {
        return new FixedSizePriorityQueueSerde();
    }


    public static final class ShareVolumeSerde extends WrapperSerde<ShareVolume> {
        public ShareVolumeSerde() {
            super(new JsonSerializer<>(), new JsonDeserializer<>(ShareVolume.class));
        }
    }

    public static final class EmployeeSerde extends WrapperSerde<Employee> {
        public EmployeeSerde() {
            super(new JsonSerializer<>(), new JsonDeserializer<>(Employee.class));
        }
    }
    public static final class IndustrySalaryCountSerde extends WrapperSerde<IndustrySalaryCount> {
        public IndustrySalaryCountSerde() {
            super(new JsonSerializer<>(), new JsonDeserializer<>(IndustrySalaryCount.class));
        }
    }

    public static final class FootBallSerde extends WrapperSerde<FootBall> {
        public FootBallSerde() {
            super(new JsonSerializer<>(), new JsonDeserializer<>(FootBall.class));
        }
    }

    public static final class StockTransactionSerde extends WrapperSerde<StockTransaction> {
        public StockTransactionSerde(){
            super(new JsonSerializer<>(), new JsonDeserializer<>(StockTransaction.class));
        }
    }

    public static final class FixedSizePriorityQueueSerde extends WrapperSerde<FixedSizePriorityQueue> {
        public FixedSizePriorityQueueSerde() {
            super(new JsonSerializer<>(), new JsonDeserializer<>(FixedSizePriorityQueue.class));
        }
    }
    private static class WrapperSerde<T> implements Serde<T> {

        private JsonSerializer<T> serializer;
        private JsonDeserializer<T> deserializer;

        WrapperSerde(JsonSerializer<T> serializer, JsonDeserializer<T> deserializer) {
            this.serializer = serializer;
            this.deserializer = deserializer;
        }

        @Override
        public void configure(Map<String, ?> map, boolean b) {

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
