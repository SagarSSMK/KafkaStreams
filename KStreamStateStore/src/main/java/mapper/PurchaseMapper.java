package mapper;

import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.kstream.KeyValueMapper;
import purchase.Purchase;

import java.util.ArrayList;
import java.util.List;

public class PurchaseMapper implements KeyValueMapper<String, String, List<KeyValue<String, Purchase>>> {

    @Override
    public List<KeyValue<String, Purchase>> apply(String k, String value) {
        List<KeyValue<String,Purchase>> keyValueList = new ArrayList<>();
        Purchase purchase=null;
        if(value!=null){
            String[] lines =value.split("[\\r|\\n]");
            Purchase.Builder builder= Purchase.newBuilder();
            for (String  line:lines){
                purchase=builder.build(line);
                if(null!=purchase){
                    String keyData= purchase.getCustomerId();
                    keyValueList.add(new KeyValue<>(keyData,purchase));
                }
            }
        }
        return keyValueList;
    }
}
