package mappers;

import model.FootBall;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.kstream.KeyValueMapper;

import java.util.ArrayList;
import java.util.List;

public class FootBallMapper implements KeyValueMapper<String,String, List<KeyValue<String,FootBall>>> {

    @Override
    public List<KeyValue<String, FootBall>> apply(String key, String value) {
        List<KeyValue<String,FootBall>> list = new ArrayList<>();
        FootBall footBall= null;
        if(value!=null){
            String[] lines = value.split("[\\r|\\n]");
            FootBall.Builder  builder=  FootBall.newBuilder();
            for (String line :lines){
                footBall=builder.build(line);
                if(null!=footBall){
                    String keyData = footBall.getPlayer();
                    list.add(new KeyValue<>(keyData,footBall));
                }
            }
        }
        return  list;
    }
}
