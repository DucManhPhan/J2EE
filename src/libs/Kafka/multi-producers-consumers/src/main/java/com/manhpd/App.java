package com.manhpd;


import com.manhpd.kafka_synchronized.MaxPriorityThread;
import com.manhpd.kafka_synchronized.MediumPriorityThread;
import com.manhpd.kafka_synchronized.MinPriorityThread;
import com.manhpd.kafka_synchronized.SavedState;
import com.manhpd.utils.KafkaUtil;
import com.manhpd.value_object.KafkaConfig;
import com.manhpd.value_object.Priority;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class App {

    private static final Logger logger = LogManager.getLogger(App.class);

    private static Map<Priority, KafkaProducer<String, String>> producers;

    private static ExecutorService executor;

    public static void main(String[] args) {
        // read all information about kafka's configuration
        KafkaConfig kafkaConfig = KafkaUtil.getKafkaConfig();
        System.out.println(kafkaConfig.toString());

        // push data to kafka
        SavedState savedState = new SavedState();

        producers = new HashMap<Priority, KafkaProducer<String, String>>() {
            {
                put(Priority.MAX_PRIORITY, KafkaUtil.initProducer());
                put(Priority.MEDIUM_PRIORITY, KafkaUtil.initProducer());
                put(Priority.MIN_PRIORITY, KafkaUtil.initProducer());
            }
        };

        // create thread for consumer
        executor = Executors.newFixedThreadPool(3);
        executor.execute(new MaxPriorityThread(savedState));
        executor.execute(new MediumPriorityThread(savedState));
        executor.execute(new MinPriorityThread(savedState));
        executor.shutdown();

        Random random = new Random();
        for (int i = 0; i < 100000; ++i) {
            int rand = random.nextInt(100000);
            logger.info("Random number is: " + rand);


            if (rand % 2 == 0) {
                String maxPriority = Priority.MAX_PRIORITY.getNameTopic();
                logger.info("Topic of max priority is: " + maxPriority);
                ProducerRecord<String, String> record = new ProducerRecord<>(Priority.MAX_PRIORITY.getNameTopic(), String.valueOf(rand));
                logger.info("Data is sent to Max Priority Thread: " + record.value());
                producers.get(Priority.MAX_PRIORITY).send(record);
            } else if ((rand + 1) % 2 == 0) {
                String mediumPriority = Priority.MEDIUM_PRIORITY.getNameTopic();
                logger.info("Topic of max priority is: " + mediumPriority);
                ProducerRecord<String, String> record = new ProducerRecord<>(Priority.MEDIUM_PRIORITY.getNameTopic(), String.valueOf(rand));
                logger.info("Data is sent to Medium Priority Thread: " + record.value());
                producers.get(Priority.MEDIUM_PRIORITY).send(record);
            }

            else if ((rand + 1) % 2 == 0) { //(rand % 7 == 0) {
                String minPriority = Priority.MIN_PRIORITY.getNameTopic();
                logger.info("Topic of max priority is: " + minPriority);
                ProducerRecord<String, String> record = new ProducerRecord<>(Priority.MIN_PRIORITY.getNameTopic(), String.valueOf(rand));
                logger.info("Data is sent to Min Priority Thread: " + record.value());
                producers.get(Priority.MIN_PRIORITY).send(record);
            }
        }
    }
}
