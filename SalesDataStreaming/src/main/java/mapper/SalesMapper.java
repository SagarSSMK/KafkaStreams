package mapper;

import model.Key;
import model.Sales;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.kstream.KeyValueMapper;

import java.util.ArrayList;
import java.util.List;

public class SalesMapper implements KeyValueMapper<String,String, List<KeyValue<Key,Sales>>> {

    @Override
    public List<KeyValue<Key, Sales>> apply(String key, String values) {
        List<KeyValue<Key,Sales>> dataList= new ArrayList<>();
        if(null!=values){
            String lines[]=values.split("[\\r|\\n]");
            Sales.Builder builder= Sales.newBuilder();
            for(String value:lines){
                Sales sales= builder.build(value);
                if(null!=sales){
                    Key orderId= Key.newBuilder().withOrderId(sales.getOrderId()).build();
                    dataList.add(new KeyValue<>(orderId,sales));
                }
            }
        }
        return dataList;
    }
}
