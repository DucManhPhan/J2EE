package com.manhpd.util;

import com.manhpd.dto.KafkaConnection;
import com.manhpd.dto.Priority;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Collections;
import java.util.Properties;

public class KafkaUtils {

    private static final Logger logger = LogManager.getLogger(KafkaUtils.class);

    public static KafkaProducer createProducer(KafkaConnection connection) {
        KafkaProducer<String, String> producer = null;
        logger.info("Initialize producer.");

        try {
            logger.info("Before creating KafkaProducer.");
            Properties prop = KafkaUtils.getPropertiesProducer(connection);
            producer = new KafkaProducer<String, String>(prop);
            logger.info("After creating KafkaProducer.");

        } catch(Exception ex) {
            logger.info("Exception happen when initializing Producer.");
        }

        return producer;
    }

    public static KafkaConsumer createConsumer(KafkaConnection connection, String topic) {
        KafkaConsumer<String, String> consumer = null;
        logger.info("Initialize consumer.");

        try {
            logger.info("Before creating KafkaConsumer.");
            Properties prop = KafkaUtils.getPropertiesConsumer(connection);
            consumer = new KafkaConsumer<>(prop);
            consumer.subscribe(Collections.singletonList(topic));
            logger.info("After creating KafkaConsumer.");
        } catch (Exception e) {
            logger.info("Exception happen when initialize Consumer.");
            e.printStackTrace();
        }

        return consumer;
    }

    private static Properties getPropertiesProducer(KafkaConnection connection) {
        Properties prop = new Properties();
        prop.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, connection.getConnection());
        prop.put(ProducerConfig.ACKS_CONFIG, "all");
        prop.put(ProducerConfig.RETRIES_CONFIG, 0);
        prop.put(ProducerConfig.BATCH_SIZE_CONFIG, 16384);
        prop.put(ProducerConfig.LINGER_MS_CONFIG, 1);
        prop.put(ProducerConfig.BUFFER_MEMORY_CONFIG, 33554432);
        prop.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        prop.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");

        return prop;
    }

    private static Properties getPropertiesConsumer(KafkaConnection connection) {
        Properties prop = new Properties();
        prop.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, connection.getConnection());
        prop.put(ConsumerConfig.GROUP_ID_CONFIG, connection.getGroup());
        prop.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, true);
        prop.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, 1000);
        prop.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "latest");
        prop.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        prop.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        prop.put(ConsumerConfig.AUTO_OFFSET_RESET_DOC, "earliest");

        return prop;
    }

    public static void displayKafkaMessage(ConsumerRecords<String, String> records, Priority type) {
        for (ConsumerRecord record : records) {
            logger.info(String.format("Topic - %s, Partition - %d, Value: %s", ((ConsumerRecord) record).topic(), ((ConsumerRecord) record).partition(), ((ConsumerRecord) record).value()) + " in " + type.getName());
        }
    }
}
