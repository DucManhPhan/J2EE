package com.manhpd.kafka;

import com.manhpd.utils.KafkaUtil;
import com.manhpd.value_object.KafkaConfig;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.nio.file.Paths;

public class MaxPriorityThread implements Runnable {

    private static final Logger logger = LogManager.getLogger(MaxPriorityThread.class);

    private Object lock;

    private KafkaConsumer<String, String> consumer;

    public MaxPriorityThread(Object lock) {
        this.lock = lock;

        KafkaConfig kafkaConfig = KafkaUtil.getKafkaConfig();
        this.consumer = KafkaUtil.initConsumer(kafkaConfig.getConsumerMaxTopic());
    }

    @Override
    public void run() {
        try {
            while (true) {
                ConsumerRecords<String, String> records = this.consumer.poll(100);
                synchronized (this.lock) {
                    
                }
            }

        } catch (Exception ex) {

        }
    }

}
