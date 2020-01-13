package com.manhpd.kafka_synchronized;

import com.manhpd.utils.KafkaUtil;
import com.manhpd.value_object.KafkaConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MaxPriorityThread implements Runnable {

    private static final Logger logger = LogManager.getLogger(MaxPriorityThread.class);

    private SavedState savedState;

    private KafkaConsumer<String, String> consumer;

    public MaxPriorityThread(SavedState savedState) {
        this.savedState = savedState;

        KafkaConfig kafkaConfig = KafkaUtil.getKafkaConfig();
        this.consumer = KafkaUtil.initConsumer(kafkaConfig.getConsumerMaxTopic());
    }

    @Override
    public void run() {
        logger.info("Running in a MaxPriorityThread.");
        while (true) {
            try {
                ConsumerRecords<String, String> records = this.consumer.poll(100);
                synchronized (this.savedState) {
                    if (records.isEmpty()) {
                        logger.info(this.savedState.toString());
                        logger.info("Sleeping in max priority thread.");
                        this.savedState.isMaxPriorityThreadRunnable = false;
                        this.savedState.notifyAll();
                        logger.info("Notify for all other threads.");
                        continue;
                    }
                }

                logger.info("Continue running max priority thread.");
                logger.info("Records of max priority thread is: " + records.count());
                this.savedState.isMaxPriorityThreadRunnable = true;
                for (ConsumerRecord<String, String> record : records) {
                    logger.info(String.format("Topic - %s, Partition - %d, Value: %s",
                            ((ConsumerRecord) record).topic(), ((ConsumerRecord) record).partition(),
                            ((ConsumerRecord) record).value()) + " in " + MaxPriorityThread.class.getName());
                }

                this.savedState.isMaxPriorityThreadRunnable = false;
                logger.info(this.savedState.toString());
            } catch (Exception ex) {
                logger.error("Exception ", ex);
            }
        }
    }

}
