package com.manhpd.utils;

import com.manhpd.value_object.KafkaConfig;
import com.manhpd.value_object.Priority;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.TopicPartition;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

public class KafkaUtil {

    private static Logger logger = LogManager.getLogger(KafkaUtil.class);

    private static KafkaConfig kafkaConfig = null;

    public static KafkaConfig getKafkaConfig() {
        logger.info("Read kafka's configuration file.");
        if (kafkaConfig == null) {
            InputStream kafkaConfigFile = FileUtil.getFileFromResources("kafka.properties");

            kafkaConfig = KafkaUtil.readKafkaConfigFile(kafkaConfigFile);
            logger.info("Info of kafka: " + kafkaConfig.toString());
        }

        return kafkaConfig;
    }

    public static KafkaConsumer<String, String> initConsumer(String topic) {
        KafkaConfig kafkaConfig = KafkaUtil.getKafkaConfig();
        KafkaConsumer<String, String> consumer = null;
        logger.info("connection: " + kafkaConfig.getBootstrapServers());
        logger.info("group: " + kafkaConfig.getConsumerGroupId());
        logger.info("topic: " + topic);

        try {
            Properties props = new Properties();
            props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaConfig.getBootstrapServers());
            props.put(ConsumerConfig.GROUP_ID_CONFIG, kafkaConfig.getConsumerGroupId());
            props.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, kafkaConfig.getMaxPollRecords());
//            props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "true");
            props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, kafkaConfig.getAutoCommitIntervalMs());
            props.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, kafkaConfig.getSessionTimeoutMs());
            props.put(ConsumerConfig.HEARTBEAT_INTERVAL_MS_CONFIG, kafkaConfig.getHearbeatInternalMs());
            props.put(ConsumerConfig.MAX_POLL_INTERVAL_MS_CONFIG, kafkaConfig.getMaxPollInternalMs());
            props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
            props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
            props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, kafkaConfig.getAutoOffsetReset());

            logger.info("Before creating kafka consumer...");
            logger.info("Data of Properties in Consumer is: " + props.toString());
            consumer = new KafkaConsumer<>(props);
            consumer.subscribe(Collections.singletonList(topic));
            logger.info("After creating kafka consumer ...");
            logger.error("After creating kafka consumer ...");
        } catch (Exception ex) {
            logger.error("initConsumber(): ", ex);
        }
        return consumer;
    }

    public static KafkaProducer<String, String> initProducer() {
        KafkaConfig kafkaConfig = KafkaUtil.getKafkaConfig();
        KafkaProducer<String, String> producer = null;
        logger.info("initProducer(): " + kafkaConfig.getBootstrapServers());

        try {
            Properties props = new Properties();
            props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaConfig.getBootstrapServers());
            props.put(ProducerConfig.ACKS_CONFIG, kafkaConfig.getAcks());
            //If the request fails, the producer can automatically retry,
            props.put(ProducerConfig.RETRIES_CONFIG, kafkaConfig.getRetries());
            props.put(ProducerConfig.BATCH_SIZE_CONFIG, kafkaConfig.getBatchSize());
            //Reduce the no of requests less than 0
            props.put(ProducerConfig.LINGER_MS_CONFIG, kafkaConfig.getLingerMs());
            //The buffer.memory controls the total amount of memory available to the producer for buffering.
            props.put(ProducerConfig.BUFFER_MEMORY_CONFIG, kafkaConfig.getBufferMemory());
            props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
            props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");

            logger.info("Before initializing Producer()");
            logger.info("Data of Properties that used for Producer: " + props.toString());
            producer = new KafkaProducer<>(props);
            logger.info("After inititializing Producer()");
            logger.error("initProducer(): " + "sau khoi tao");
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

    public static Priority checkPriority(String priority) {
        switch (priority) {
            case "1":
                return Priority.MAX_PRIORITY;

            case "2":
                return Priority.MEDIUM_PRIORITY;

            case "3":
                return Priority.MIN_PRIORITY;

            default:
                return Priority.UNDETERMINED;
        }
    }

    private static KafkaConfig readKafkaConfigFile(InputStream path) {
        return KafkaUtil.readFile(path);
    }

    private static KafkaConfig readFile(InputStream is) {
        KafkaConfig kafkaConfig = new KafkaConfig();

        try {
            // read configuration file
            Properties properties = new Properties();
//            FileInputStream kafkaConfigFile = new FileInputStream(file);
            BufferedReader kafkaConfigFile = new BufferedReader(new InputStreamReader(is));
            if (kafkaConfigFile == null) {
                logger.error("Do not find kafka.conf");
                return null;
            }

            properties.load(kafkaConfigFile);

            // parse each field
            // use bootstrap servers
            String tmp = properties.getProperty(Constant.KAFKA_BOOTSTRAP_SERVERS);
            if (StringUtil.isEmpty(tmp)) {
                logger.error("Do not have kafka servers.");
            }
            kafkaConfig.setBootstrapServers(tmp);

            // use acks
            tmp = properties.getProperty(Constant.KAFKA_ACKS);
            if (StringUtil.isEmpty(tmp)) {
                logger.error("Acks is empty");
            }
            kafkaConfig.setAcks(tmp);

            // use acks
            tmp = properties.getProperty(Constant.KAFKA_RETRIES);
            if (StringUtil.isEmpty(tmp)) {
                logger.error("Retries are empty");
            }
            kafkaConfig.setRetries(tmp);

            tmp = properties.getProperty(Constant.KAFKA_BATCH_SIZE);
            if (StringUtil.isEmpty(tmp)) {
                logger.error("Batch size is empty");
            }
            kafkaConfig.setBatchSize(tmp);

            tmp = properties.getProperty(Constant.KAFKA_LINGER_MS);
            if (StringUtil.isEmpty(tmp)) {
                logger.error("Linger ms is empty");
            }
            kafkaConfig.setLingerMs(tmp);

            tmp = properties.getProperty(Constant.KAFKA_BUFFER_MEMORY);
            if (StringUtil.isEmpty(tmp)) {
                logger.error("Buffer memory is empty");
            }
            kafkaConfig.setBufferMemory(tmp);

            tmp = properties.getProperty(Constant.KAFKA_CONSUMER_GROUP_ID);
            if (StringUtil.isEmpty(tmp)) {
                logger.error("Consumer group id is empty");
            }
            kafkaConfig.setConsumerGroupId(tmp);

            tmp = properties.getProperty(Constant.KAFKA_MAX_POLL_RECORDS);
            if (StringUtil.isEmpty(tmp)) {
                logger.error("Max poll records is empty");
            }
            kafkaConfig.setMaxPollRecords(tmp);

            tmp = properties.getProperty(Constant.KAFKA_AUTO_COMMIT_INTERVAL_MS);
            if (StringUtil.isEmpty(tmp)) {
                logger.error("Auto commit internal in ms is empty");
            }
            kafkaConfig.setAutoCommitIntervalMs(tmp);

            tmp = properties.getProperty(Constant.KAFKA_SESSION_TIMEOUT_MS);
            if (StringUtil.isEmpty(tmp)) {
                logger.error("Session timeout in ms is empty");
            }
            kafkaConfig.setSessionTimeoutMs(tmp);

            tmp = properties.getProperty(Constant.KAFKA_HEARTBEAT_INTERVAL_MS);
            if (StringUtil.isEmpty(tmp)) {
                logger.error("Hearbeat interal in ms is empty");
            }
            kafkaConfig.setHearbeatInternalMs(tmp);

            tmp = properties.getProperty(Constant.KAFKA_MAX_POLL_INTERVAL_MS);
            if (StringUtil.isEmpty(tmp)) {
                logger.error("Max poll internal in ms is empty");
            }
            kafkaConfig.setMaxPollInternalMs(tmp);

            tmp = properties.getProperty(Constant.KAFKA_AUTO_OFFSET_RESET);
            if (StringUtil.isEmpty(tmp)) {
                logger.error("Auto offset reset is empty");
            }
            kafkaConfig.setAutoOffsetReset(tmp);

            tmp = properties.getProperty(Constant.KAFKA_CONSUMER_MEDIUM_TOPIC);
            if (StringUtil.isEmpty(tmp)) {
                logger.error("Consumer Medium topic is empty");
            }
            kafkaConfig.setConsumerMediumTopic(tmp);

            tmp = properties.getProperty(Constant.KAFKA_CONSUMER_MAX_TOPIC);
            if (StringUtil.isEmpty(tmp)) {
                logger.error("Consumer Max topic is empty");
            }
            kafkaConfig.setConsumerMaxTopic(tmp);

            tmp = properties.getProperty(Constant.KAFKA_CONSUMER_MIN_TOPIC);
            if (StringUtil.isEmpty(tmp)) {
                logger.error("Consumer Min topic is empty");
            }
            kafkaConfig.setConsumerMinTopic(tmp);
        } catch (Exception ex) {
            logger.error(ex);
        }

        return kafkaConfig;
    }

}
