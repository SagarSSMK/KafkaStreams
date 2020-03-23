package util.serializer;

import model.Employee;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Map;
import java.util.Objects;

import model.Employee;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;

public class EmployeeDeserializer implements Deserializer<Employee> {
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {

    }

    @Override
    public Employee deserialize(String topic, byte[] bytes) {
        if (Objects.isNull(bytes)) {
            return null;
        }

        Employee data;
        try {
            data = objectMapper.treeToValue(objectMapper.readTree(bytes), Employee.class);
        } catch (Exception e) {
            throw new SerializationException(e);
        }

        return data;
    }

    @Override
    public void close() {

    }
}
