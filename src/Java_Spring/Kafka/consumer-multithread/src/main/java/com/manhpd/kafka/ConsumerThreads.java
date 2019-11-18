package com.manhpd.kafka;

import com.manhpd.dto.KafkaConnection;
import com.manhpd.dto.MonitorObject;
import com.manhpd.dto.Priority;
import com.manhpd.dto.ThreadInfo;
import com.manhpd.kafka.new_threads.PriorityThread;
import com.manhpd.kafka.old_threads.MediumPriorityThread;
import com.manhpd.kafka.old_threads.MinPriorityThread;
import com.manhpd.kafka.old_threads.multi_consumer_threads.FirstPriorityThread;
import com.manhpd.kafka.old_threads.multi_consumer_threads.LowerPriorityThread;
import com.manhpd.util.KafkaUtils;
import com.manhpd.util.PropertiesUtils;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.utils.CopyOnWriteMap;

import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ConsumerThreads {

    public static volatile boolean isMinPriorityThreadRunnable = false;

    // (1)
//    private static Object monitor = new Object();

    // (2)
//    private static volatile MonitorObject monitor = new MonitorObject(Priority.MEDIUM_PRIORITY.getName());
//
//    private static Map<Priority, KafkaConsumer<String, String>> consumers;

    // (3)
    private static Object monitor = new Object();

    public static ConcurrentHashMap<Priority, Boolean> concurrentHashMap = new ConcurrentHashMap<>() {{
        put(Priority.MIN_PRIORITY, false);
        put(Priority.MEDIUM_PRIORITY, false);
        put(Priority.MAX_PRIORITY, false);
    }};

    public static void start() throws URISyntaxException {
        Optional<ThreadInfo> info = PropertiesUtils.getThreadInfo("application.properties");
        if (info.isEmpty()) {
            return;
        }

        // (1) - create old threads for medium priority thread and min priority thread
//        int numberOfThreads = info.get().getNumberOfThreads();
//        ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);
//
//        executorService.execute(new MediumPriorityThread(monitor));
//        executorService.execute(new MinPriorityThread(monitor));
//        executorService.shutdown();

        // (2) - use only one thread to manage the creation of consumers
//        Optional<KafkaConnection> connection = PropertiesUtils.getKafkaConnection("kafka.properties");
//        if (connection.isEmpty()) {
//            return;
//        }
//
//        String topicMinPriority = connection.get().getTopics().get(0);
//        String topicMediumPriority = connection.get().getTopics().get(1);
//
//        ConsumerThreads.consumers = new HashMap<>() {{
//            put(Priority.MEDIUM_PRIORITY, KafkaUtils.createConsumer(connection.get(), topicMediumPriority));
//            put(Priority.MIN_PRIORITY, KafkaUtils.createConsumer(connection.get(), topicMinPriority));
//        }};
//
//        ExecutorService executorService = Executors.newSingleThreadExecutor();
//        executorService.execute(new PriorityThread(Priority.MEDIUM_PRIORITY, ConsumerThreads.consumers));
//        executorService.shutdown();

        // (3) - Use multiple threads > 2.
        int numberOfThreads = info.get().getNumberOfThreads();
        ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);

        executorService.execute(new FirstPriorityThread(ConsumerThreads.monitor, Priority.MAX_PRIORITY));
        executorService.execute(new LowerPriorityThread(ConsumerThreads.monitor, Priority.MIN_PRIORITY));
        executorService.execute(new LowerPriorityThread(ConsumerThreads.monitor, Priority.MEDIUM_PRIORITY));
        executorService.shutdown();
    }
}
