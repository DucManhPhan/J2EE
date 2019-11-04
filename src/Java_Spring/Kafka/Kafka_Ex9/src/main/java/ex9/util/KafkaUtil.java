/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ex9.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.Future;
import java.util.stream.StreamSupport;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 *
 * @author Dell
 */

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Dell
 */

public class KafkaUtil {

    private static Logger logger = LogManager.getLogger(KafkaUtil.class);

    public static KafkaConsumer<String, String> initConsumer(String connection, String group, String topic) {

        KafkaConsumer<String, String> consumer = null;
        logger.info("connection: " + connection);
        logger.info("group: " + group);
        logger.info("topic: " + topic);

        try {
            Properties props = new Properties();
            props.put("bootstrap.servers", connection);
            //========
            //props.put("zk.connect", "http://178.128.222.84:2181");
            //==========
            props.put("group.id", group);
            props.put("key.deserializer", StringDeserializer.class.getName());
            props.put("value.deserializer", StringDeserializer.class.getName());

            consumer = new KafkaConsumer<>(props);
            consumer.subscribe(Collections.singletonList(topic));
        } catch (Exception ex) {
            logger.error("initConsumber(): ", ex);
        }
        return consumer;
    }

    public static KafkaProducer<String, String> initProducer(String connection, int batchSize) {
        KafkaProducer<String, String> producer = null;
        logger.info("initProducer(): " + connection);

        try {
            Properties props = new Properties();
            props.put("bootstrap.servers", connection);
            props.put("acks", "all");
            props.put("retries", 0);
            props.put("batch.size", batchSize);
            props.put("linger.ms", 1);
            props.put("buffer.memory", 33554432);
            props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
            props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

            producer = new KafkaProducer<>(props);
        } catch (Exception ex) {
            logger.info("initProducer() - Exception: ", ex);
        }

        return producer;
    }

    public static List<String> getValue(ConsumerRecords<String, String> records) {
        List<String> list = new ArrayList<>();
        if (records.isEmpty()) {
            return list;
        }

        for (TopicPartition partition : records.partitions()) {
            List<ConsumerRecord<String, String>> partitionRecords = records.records(partition);
            for (ConsumerRecord<String, String> record : partitionRecords) {
                list.add(record.value());
            }
        }

        return list;
    }
}
