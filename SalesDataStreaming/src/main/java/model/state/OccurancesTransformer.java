package model.state;

import model.Sales;
import org.apache.kafka.streams.kstream.ValueTransformer;
import org.apache.kafka.streams.processor.ProcessorContext;
import org.apache.kafka.streams.state.KeyValueStore;

import java.util.List;
import java.util.Objects;

public class OccurancesTransformer implements ValueTransformer<Sales,Occurances> {

    private KeyValueStore<String, Integer> stateStore;
    private final String storeName;
    private ProcessorContext context;

    public OccurancesTransformer( String storeName) {
        Objects.requireNonNull(storeName,"Store Name can't be null");
        this.storeName = storeName;
    }

    @Override
    public void init(ProcessorContext context) {
        this.context=context;
        stateStore= (KeyValueStore)this.context.getStateStore(storeName);
    }

    @Override
    public Occurances transform(Sales value) {
        Occurances occurances= Occurances.builder(value).build();
        Integer unitsSold= stateStore.get(occurances.getCountry());

        if(unitsSold!=null)
            occurances.addCountryOccuraces(unitsSold);

        stateStore.put(occurances.getCountry(),occurances.getTotalCountryOccurances());
        return occurances;
    }

    @Override
    public Occurances punctuate(long timestamp) {
        return null;
    }

    @Override
    public void close() {

    }
}
