package mappers;

import model.Employee;
import model.ShareVolume;
import org.apache.hadoop.yarn.webapp.hamlet.Hamlet;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.kstream.KeyValueMapper;

import java.util.ArrayList;
import java.util.List;

public class EmployeeMapper implements KeyValueMapper<String,String, List<KeyValue<String, Employee>>> {
    @Override
    public List<KeyValue<String, Employee>> apply(String key, String value) {
        List<KeyValue<String, Employee>> rows= new ArrayList<>();
        Employee employee= null;
        if(null!=value){
            System.out.println("Value "+value);
            String[] lines = value/*.replace(".","").replace("_","").replace("-","")*/.split("[\\r|\\n]");
            Employee.Builder builder= Employee.newBuilder();
            for(String line :lines){
                employee=builder.build(line);
                if(null!=employee){
                    String keyData= employee.getId();
                    rows.add(new KeyValue<String, Employee>(keyData, employee));
                }
            }

        }
        return rows;
    }
}
