package com.manhpd.kafka;

import com.manhpd.utils.KafkaUtil;
import com.manhpd.value_object.KafkaConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;

public class MinPriorityThread implements Runnable {

    private Object lock;

    private KafkaConsumer<String, String> consumer;

    public MinPriorityThread(Object lock) {
        this.lock = lock;

        KafkaConfig kafkaConfig = KafkaUtil.getKafkaConfig();
        this.consumer = KafkaUtil.initConsumer(kafkaConfig.getConsumerMaxTopic());
    }

    @Override
    public void run() {

    }

}
