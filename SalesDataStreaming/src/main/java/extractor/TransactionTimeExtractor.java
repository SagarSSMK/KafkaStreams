package extractor;

import model.windowsaggregartion.SessionTest;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.streams.processor.TimestampExtractor;

public class TransactionTimeExtractor implements TimestampExtractor {


    @Override
    public long extract(ConsumerRecord<Object, Object> record, long previousTimestamp) {
        SessionTest sessionTest= (SessionTest) record.value();
        return sessionTest.getTimeInMs();
    }
}
