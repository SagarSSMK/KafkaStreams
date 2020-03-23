package mappers;

import model.ShareVolume;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.kstream.KeyValueMapper;

import java.util.ArrayList;
import java.util.List;

public class ShareVolumeMapper implements KeyValueMapper<String,String, List<KeyValue<String, ShareVolume>>> {
    @Override
    public List<KeyValue<String, ShareVolume>> apply(String key, String value) {
        List<KeyValue<String, ShareVolume>> rows= new ArrayList<>();
        ShareVolume shareVolume= null;
        if(null!=value){
            System.out.println("Value "+value);
            String[] lines = value/*.replace(".","").replace("_","").replace("-","")*/.split("[\\r|\\n]");
            ShareVolume.Builder builder= ShareVolume.newBuilder();
            for(String line :lines){
                shareVolume=builder.build(line);
                if(null!=shareVolume){
                    String keyData= shareVolume.getSymbol();
                    rows.add(new KeyValue<String, ShareVolume>(keyData,shareVolume));
                }
            }

        }
        return rows;
    }
}
