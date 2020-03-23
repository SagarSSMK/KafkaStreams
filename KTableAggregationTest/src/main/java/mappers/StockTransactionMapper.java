package mappers;

import model.ShareVolume;
import model.StockTransaction;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.kstream.KeyValueMapper;


import java.util.ArrayList;
import java.util.List;

public class StockTransactionMapper implements KeyValueMapper<String,String, List<KeyValue<String,StockTransaction>>>{

    @Override
    public List<KeyValue<String,StockTransaction>> apply(String key,String value) {
        List<KeyValue<String, StockTransaction>> rows= new ArrayList<>();
        StockTransaction stockTransaction= null;
        if(null!=value) {
            String[] lines = value.split("[\\r|\\n]");
            StockTransaction.Builder builder = StockTransaction.newBuilder();
            for (String line : lines) {
                stockTransaction = builder.build(line);
                if (null != stockTransaction) {
                    String keyData = stockTransaction.getSymbol();
                    rows.add(new KeyValue<String, StockTransaction>(keyData, stockTransaction));
                }
            }
        }
        return rows;
    }
}
