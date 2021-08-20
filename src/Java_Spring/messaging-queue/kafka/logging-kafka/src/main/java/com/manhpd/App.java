package com.manhpd;

import com.manhpd.dto.KafkaConnection;
import com.manhpd.utils.Constant;
import com.manhpd.utils.KafkaUtils;
import com.manhpd.utils.PropertiesUtils;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.net.URISyntaxException;
import java.util.Optional;
import java.util.stream.IntStream;


public class App {
    public static void main(String[] args) throws URISyntaxException {
        Optional<KafkaConnection> optionalKafkaConnection = PropertiesUtils.getKafkaConnection(Constant.KAFKA_FILE_NAME);
//        optionalKafkaConnection.ifPresent(conn -> System.out.println(conn.toString()));
        KafkaConnection kafkaConnection = optionalKafkaConnection.orElse(new KafkaConnection());

        String topic = kafkaConnection.getTopics().get(0);
        KafkaProducer producer = KafkaUtils.createProducer(kafkaConnection);
        IntStream.range(1, 100)
                .forEach(item -> {
                    ProducerRecord<String, String> record = new ProducerRecord<>(topic, String.valueOf(item), "The " + String.valueOf(item) + "th message in Producer.");
                    producer.send(record);
                });
    }
}
