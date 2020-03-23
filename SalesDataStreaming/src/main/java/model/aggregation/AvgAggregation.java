/*
package model.aggregation;

import model.Sales;
import model.aggregation.avg.AvgValue;
import org.apache.kafka.streams.kstream.Aggregator;

public class AvgAggregation<K, V, T> implements Aggregator<String,Sales, AvgValue> {

    public  AvgAggregation(){}
    @Override
    public AvgValue apply(String key, Sales value, AvgValue aggregate) {
        return new AvgValue(aggregate.getCount()+1,aggregate.getSum()+value.getTotalProfit());
    }

}
*/
