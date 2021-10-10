package com.manhpd;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class App {

    private static int NUM_THREADS = 5;

    private static PersonCounter counter = new PersonCounter();

    public static void main( String[] args ) {
        ExecutorService executorService = Executors.newFixedThreadPool(NUM_THREADS);
        IntStream numberOfThreads = IntStream.range(0, NUM_THREADS);

        // The desired value of counter is 5
        // With race condition, value = anything
        numberOfThreads.forEach(id -> {
            executorService.execute(new CounterThread("Thread " + id, counter));
        });

        waitAllThreads(executorService);

        System.out.println("The final value of counter is " + counter.getValue());
    }

    private static void waitAllThreads(ExecutorService executorService) {
        executorService.shutdown();

        try {
            if (!executorService.awaitTermination(60, TimeUnit.SECONDS)) {
                executorService.shutdownNow();
            }
        } catch (InterruptedException e) {
            executorService.shutdownNow();
            Thread.currentThread().interrupt();
            System.out.println("The current thread is interrupted.");
        }
    }
}
