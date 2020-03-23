package util.serializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Map;
import java.util.Objects;

import model.Employee;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Serializer;

public class EmployeeSerializer implements Serializer<Employee> {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {

    }

    @Override
    public byte[] serialize(String topic, Employee data) {
        if (Objects.isNull(data)) {
            return null;
        }
        try {
            return objectMapper.writeValueAsBytes(data);
        } catch (Exception e) {
            throw new SerializationException("Error serializing message", e);
        }
    }

    @Override
    public void close() {

    }
}
