package serdes;

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
import model.streamtablejoin.LookUpResults;
import model.windowsaggregartion.SessionAggregation;
import model.windowsaggregartion.SessionTest;
import utils.JsonDeserializer;
import utils.JsonSerializer;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serializer;

import java.util.Map;

public class StreamsSerdes {


    public static Serde<Key> keySerde(){ return  new KeySerde();}
    public static Serde<Sales> salesSerde(){return new SalesSerde();}
    public static Serde<HigherAndLowereResults> aggregationSerde(){ return  new AggregationSerde();}
    public static Serde<KeyAggByRegion> keyAggByRegionSerde(){return new KeyAggByRegionSerde();}
    public static Serde<AvgValue> avgValueSerde(){return new AvgValueSerde();}
    public static Serde<AvgUnitsKey> avgUnitsKeySerde(){return new AvgUnitsKeySerde();}
    public static Serde<AvgUnitsValue> avgValueSerdeSerde(){return new AvgUnitsValueSerde();}
    public static Serde<MappedData> mappedDataSerde(){return  new MappedDataSede();}
    public static Serde<Region> regionSerde(){return  new RegionSerde();}
    public static Serde<RegionAggregate> regionAggregateSerde(){return  new RegionAggregateSerde();}
    public static Serde<SessionTest> sessionTestSerde(){return new SessionTestSerde();}
    public static Serde<SessionAggregation> sessionAggregationSerde(){return new SessionAggregationSerde();}
    public static Serde<LookUpResults> lookUpResultsSerde(){ return new LookUpResultsSerde();}


    public static final class KeySerde extends WrapperSerde<Key>{
       public KeySerde(){
           super(new JsonSerializer<>(),new JsonDeserializer<>(Key.class));
       }
    }

    public static final class AvgValueSerde extends WrapperSerde<AvgValue>{
        public  AvgValueSerde(){super(new JsonSerializer<>(),new JsonDeserializer<>());}
    }

    public static final  class  SalesSerde extends WrapperSerde<Sales>{
        public SalesSerde(){
            super(new JsonSerializer<>(),new JsonDeserializer<>(Sales.class));
        }
    }

    public static final class AggregationSerde extends WrapperSerde<HigherAndLowereResults>{
        public AggregationSerde(){
            super(new JsonSerializer<>(),new JsonDeserializer<>(HigherAndLowereResults.class));
        }
    }

    public static final class KeyAggByRegionSerde extends WrapperSerde<KeyAggByRegion>{
        public KeyAggByRegionSerde(){
            super(new JsonSerializer<>(),new JsonDeserializer<>(KeyAggByRegion.class));
        }
    }
    public static final class MappedDataSede extends WrapperSerde<MappedData>{
        public MappedDataSede(){
            super(new JsonSerializer<>(),new JsonDeserializer<>(MappedData.class));
        }
    }

    public static final class AvgUnitsKeySerde extends WrapperSerde<AvgUnitsKey>{
        public AvgUnitsKeySerde(){
            super(new JsonSerializer<>(),new JsonDeserializer<>(AvgUnitsKey.class));
        }
    }

    public static final class AvgUnitsValueSerde extends WrapperSerde<AvgUnitsValue>{
        public AvgUnitsValueSerde(){
            super(new JsonSerializer<>(),new JsonDeserializer<>(AvgUnitsValue.class));
        }
    }

    public static final class RegionSerde extends WrapperSerde<Region>{
        public RegionSerde(){
            super(new JsonSerializer<>(),new JsonDeserializer<>(Region.class));
        }
    }

    public static final class RegionAggregateSerde extends WrapperSerde<RegionAggregate>{
        public RegionAggregateSerde(){
            super(new JsonSerializer<>(),new JsonDeserializer<>(RegionAggregate.class));
        }
    }

    public static final class SessionTestSerde extends WrapperSerde<SessionTest>{
        public SessionTestSerde(){
            super(new JsonSerializer<>(),new JsonDeserializer<>(SessionTest.class));
        }
    }

    public static final class SessionAggregationSerde extends WrapperSerde<SessionAggregation>{
        public SessionAggregationSerde(){
            super(new JsonSerializer<>(),new JsonDeserializer<>(SessionAggregation.class));
        }
    }

    public static final class LookUpResultsSerde extends WrapperSerde<LookUpResults>{
        public LookUpResultsSerde(){
            super(new JsonSerializer<>(),new JsonDeserializer<>(LookUpResults.class));
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
