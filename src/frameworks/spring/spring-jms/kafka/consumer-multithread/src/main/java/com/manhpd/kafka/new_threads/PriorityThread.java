package com.manhpd.kafka.new_threads;

import com.manhpd.dto.Priority;
import com.manhpd.util.KafkaUtils;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PriorityThread implements Runnable {

    private static final Logger logger = LogManager.getLogger(PriorityThread.class);

    private Map<Priority, KafkaConsumer<String, String>> consumers;

    private Priority currentPriority;

    public PriorityThread(Priority currentPriority, Map<Priority, KafkaConsumer<String, String>> consumers) {
        this.consumers = consumers;
        this.currentPriority = currentPriority;
    }

    @Override
    public void run() {
        KafkaConsumer<String, String> consumer = this.consumers.get(this.currentPriority);
        ConsumerRecords<String, String> records = consumer.poll(100);
        if (records.isEmpty()) {
            Priority lowerPriority = currentPriority.previous();
            Runnable priorityThread = new PriorityThread(lowerPriority, this.consumers);

            ExecutorService executorService = Executors.newSingleThreadExecutor();
            executorService.execute(priorityThread);
            executorService.shutdown();
            return;
        }

        KafkaUtils.displayKafkaMessage(records, this.currentPriority);
        consumer.commitAsync();
    }
}
