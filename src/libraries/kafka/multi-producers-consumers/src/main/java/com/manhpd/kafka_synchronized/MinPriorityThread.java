package com.manhpd.kafka_synchronized;

import com.manhpd.utils.KafkaUtil;
import com.manhpd.value_object.KafkaConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MinPriorityThread implements Runnable {

    private static final Logger logger = LogManager.getLogger(MinPriorityThread.class);

    private SavedState savedState;

    private KafkaConsumer<String, String> consumer;

    public MinPriorityThread(SavedState savedState) {
        this.savedState = savedState;

        KafkaConfig kafkaConfig = KafkaUtil.getKafkaConfig();
        this.consumer = KafkaUtil.initConsumer(kafkaConfig.getConsumerMinTopic());
    }

    @Override
    public void run() {
        logger.info("Running in a MinPriorityThread.");
        while (true) {
            try {
                ConsumerRecords<String, String> records = this.consumer.poll(100);
                synchronized (this.savedState) {
                    while (this.savedState.isNotMinPriorityThreadRunnable() || records.isEmpty()) {
                        logger.info("Sleeping in min priority thread.");
                        logger.info(this.savedState.toString());
//                        Thread.sleep(1000);
                        this.savedState.wait();
//                        continue;
                        records = this.consumer.poll(100);
                    }

                    logger.info("Continue running min priority thread.");
                    logger.info("Records of min priority thread is: " + records.count());
                    for (ConsumerRecord<String, String> record : records) {
                        while (this.savedState.isNotMinPriorityThreadRunnable()) {
                            this.savedState.wait();
                        }

                        logger.info(String.format("Topic - %s, Partition - %d, Value: %s",
                                ((ConsumerRecord) record).topic(), ((ConsumerRecord) record).partition(),
                                ((ConsumerRecord) record).value()) + " in " + MinPriorityThread.class.getName());
                    }
                }

            } catch (Exception ex) {
                logger.error(ex);
            }
        }
    }

}
