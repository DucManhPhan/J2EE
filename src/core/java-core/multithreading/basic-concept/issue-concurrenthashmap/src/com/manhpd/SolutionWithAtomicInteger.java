package com.manhpd;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class SolutionWithAtomicInteger {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ConcurrentHashMap<String, AtomicInteger> map = new ConcurrentHashMap<>();
        map.put("value", new AtomicInteger(0));

        ExecutorService es = Executors.newFixedThreadPool(5);

        Runnable task = () -> {
            for (int i = 0; i < 100; ++i) {
                map.get("value").incrementAndGet();
            }
        };

        // submit the task twice
        Future future1 = es.submit(task);
        Future future2 = es.submit(task);

        // wait for the threads to finish
        future1.get();
        future2.get();

        // shutdown the executor service
        es.shutdown();

        System.out.println("The value: " + map.get("value"));
    }

}
