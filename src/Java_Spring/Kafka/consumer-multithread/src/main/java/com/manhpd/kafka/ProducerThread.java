package com.manhpd.kafka;

import com.manhpd.dto.KafkaConnection;
import com.manhpd.util.KafkaUtils;
import com.manhpd.util.PropertiesUtils;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.net.URISyntaxException;
import java.util.Optional;
import java.util.stream.IntStream;

public class ProducerThread {

    public static void start() throws URISyntaxException {
        Optional<KafkaConnection> connection = PropertiesUtils.getKafkaConnection("kafka.properties");
        if (connection.isEmpty()) {
            return;
        }

        String topicMinPriority = connection.get().getTopics().get(0);
        String topicMediumPriority = connection.get().getTopics().get(1);

        KafkaProducer producer = KafkaUtils.createProducer(connection.get());
        IntStream.range(1, 10)
                 .forEach(item -> {
                     ProducerRecord<String, String> record = new ProducerRecord<>(topicMinPriority, String.valueOf(item), "The " + String.valueOf(item) + "th message in Producer.");
                     producer.send(record);
                 });

        IntStream.range(11, 20)
                .forEach(item -> {
                    ProducerRecord<String, String> record = new ProducerRecord<>(topicMediumPriority, String.valueOf(item), "The " + String.valueOf(item) + "th message in Producer.");
                    producer.send(record);
                });

        producer.flush();
        producer.close();
    }

}
