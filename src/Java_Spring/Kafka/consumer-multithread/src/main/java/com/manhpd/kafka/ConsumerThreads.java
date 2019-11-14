package com.manhpd.kafka;

import com.manhpd.dto.ThreadInfo;
import com.manhpd.util.PropertiesUtils;

import java.net.URISyntaxException;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class ConsumerThreads {

    public static volatile boolean isMinPriorityThreadRunnable = false;

    private static Object monitor = new Object();

    public static void start() throws URISyntaxException {
        Optional<ThreadInfo> info = PropertiesUtils.getThreadInfo("application.properties");
        if (info.isEmpty()) {
            return;
        }

        int numberOfThreads = info.get().getNumberOfThreads();
        ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);

        executorService.execute(new MediumPriorityThread(monitor));
        executorService.execute(new MinPriorityThread(monitor));
        executorService.shutdown();
    }
}
