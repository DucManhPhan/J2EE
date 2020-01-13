package com.manhpd.kafka_synchronized;

import com.manhpd.utils.KafkaUtil;
import com.manhpd.value_object.KafkaConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MediumPriorityThread implements Runnable {

    private static final Logger logger = LogManager.getLogger(MediumPriorityThread.class);

    private SavedState savedState;

    private KafkaConsumer<String, String> consumer;

    public MediumPriorityThread(SavedState savedState) {
        this.savedState = savedState;

        KafkaConfig kafkaConfig = KafkaUtil.getKafkaConfig();
        this.consumer = KafkaUtil.initConsumer(kafkaConfig.getConsumerMediumTopic());
    }

    @Override
    public void run() {
        logger.info("Running in a MediumPriorityThread.");
        while (true) {
            try {
                ConsumerRecords<String, String> records = consumer.poll(100);
                synchronized (this.savedState) {
                    if (this.savedState.isNotMediumPriorityThreadRunnable() || records.isEmpty()) {
                        logger.info(this.savedState.toString());
                        this.savedState.isMediumPriorityThreadRunnable = false;
                        logger.info("Sleep medium priority thread.");
                        Thread.sleep(1000);
//                        this.savedState.wait();
                        continue;
                    }

                    logger.info("Continue running medium priority thread.");
                    this.savedState.isMediumPriorityThreadRunnable = true;

                    logger.info(this.savedState.toString());
                    for (ConsumerRecord<String, String> record : records) {
                        while (this.savedState.isNotMediumPriorityThreadRunnable()) {
                            this.savedState.isMediumPriorityThreadRunnable = false;
                            this.savedState.wait();
                        }

                        this.savedState.isMediumPriorityThreadRunnable = true;
                        logger.info(String.format("Topic - %s, Partition - %d, Value: %s", ((ConsumerRecord) record).topic(), ((ConsumerRecord) record).partition(),
                                ((ConsumerRecord) record).value()) + " in " + MediumPriorityThread.class.getName());
                    }
                }

            } catch (Exception ex) {
                logger.error(ex);
            }
        }
    }

}
