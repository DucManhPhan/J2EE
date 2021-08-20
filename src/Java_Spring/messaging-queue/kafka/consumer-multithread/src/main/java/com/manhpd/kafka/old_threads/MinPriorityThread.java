package com.manhpd.kafka.old_threads;

import com.manhpd.dto.KafkaConnection;
import com.manhpd.dto.MonitorObject;
import com.manhpd.kafka.ConsumerThreads;
import com.manhpd.util.KafkaUtils;
import com.manhpd.util.PropertiesUtils;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.URISyntaxException;
import java.util.Optional;

public class MinPriorityThread implements Runnable {

    private static final Logger logger = LogManager.getLogger(MinPriorityThread.class);

//    private Object monitor;

    private MonitorObject monitor;

//    public MinPriorityThread(Object monitor) {
//        this.monitor = monitor;
//    }

    public MinPriorityThread(MonitorObject monitor) {
        this.monitor = monitor;
    }

    @Override
    public void run() {
        Optional<KafkaConnection> connection = null;
        try {
            connection = PropertiesUtils.getKafkaConnection("kafka.properties");

            if (connection.isEmpty()) {
                return;
            }

            String topicMinPriority = connection.get().getTopics().get(0);
            KafkaConsumer<String, String> consumer = KafkaUtils.createConsumer(connection.get(), topicMinPriority);

            logger.info("Running ...");

            while (true) {
                ConsumerRecords<String, String> records = consumer.poll(100);
                try {
                    if (!ConsumerThreads.isMinPriorityThreadRunnable || records.isEmpty()) {
//                        logger.info("MinPriorityThread will sleep for 1s.");
                        Thread.sleep(1000);
                        continue;
                    }

                    synchronized (this.monitor) {
                        logger.info("Step into synchronized block of MinPriorityThread.");
                        for (ConsumerRecord record : records) {
                            while (!ConsumerThreads.isMinPriorityThreadRunnable) {
                                this.monitor.wait();
                            }

                            logger.info(String.format("Topic - %s, Partition - %d, Value: %s", ((ConsumerRecord) record).topic(), ((ConsumerRecord) record).partition(), ((ConsumerRecord) record).value()) + " in " + MinPriorityThread.class.getName());
                        }

                        consumer.commitAsync();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } catch (URISyntaxException e) {
            logger.info("Do not get absolute path of kafka properties file.");
            e.printStackTrace();
        } catch (Exception e) {
            logger.info("Do not get absolute path of kafka properties file.");
            e.printStackTrace();
        } catch (Throwable t) {
            logger.info("Do not get absoluate path of kafka properties file.");
            t.printStackTrace();
        } finally {
            logger.info("Finally block in MinPriorityThread.");
        }
    }
}
