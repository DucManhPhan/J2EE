package com.manhpd.kafka.old_threads;

import com.manhpd.dto.KafkaConnection;
import com.manhpd.dto.MonitorObject;
import com.manhpd.dto.Priority;
import com.manhpd.kafka.ConsumerThreads;
import com.manhpd.util.KafkaUtils;
import com.manhpd.util.PropertiesUtils;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.URISyntaxException;
import java.util.Optional;

public class MediumPriorityThread implements Runnable {

    private static final Logger logger = LogManager.getLogger(MediumPriorityThread.class);

//    private Object monitor;

//    public MediumPriorityThread(Object monitor) {
//        this.monitor = monitor;
//    }

    private MonitorObject monitor;

    public MediumPriorityThread(MonitorObject monitor) {
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

            String topicMediumPriority = connection.get().getTopics().get(1);
            KafkaConsumer<String, String> consumer = KafkaUtils.createConsumer(connection.get(), topicMediumPriority);

            logger.info("Running ...");
            while (true) {
                    ConsumerRecords<String, String> records = consumer.poll(100);
                    synchronized (this.monitor) {
//                    logger.info("Step into synchronized block of MediumPriorityThread.");
                        if (records.isEmpty()) {
//                        logger.info("Records of MediumPriorityThread is empty.");
                            ConsumerThreads.isMinPriorityThreadRunnable = true;
                            this.monitor.notify();
                            continue;
                        }
                    }

                    ConsumerThreads.isMinPriorityThreadRunnable = false;
                    KafkaUtils.displayKafkaMessage(records, Priority.MEDIUM_PRIORITY);
                    consumer.commitAsync();
            }
        } catch (URISyntaxException e) {
            logger.info("Do not get absoluate path of kafka properties file.");
            e.printStackTrace();
        } catch (Exception e) {
            logger.info("Do not get absoluate path of kafka properties file. Really!");
            e.printStackTrace();
        } finally {
            logger.info("Finally block is called in MediumPriorityThread.");
        }
    }

}
