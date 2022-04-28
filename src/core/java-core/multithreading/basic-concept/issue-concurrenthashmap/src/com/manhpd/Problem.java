package com.manhpd;

import java.util.concurrent.*;

public class Problem {

    /**
     * The expected result is: 200, but we will receive the value that is different than our expectation.
     *
     * @param args
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();
        map.put("value", 0);

        ExecutorService es = Executors.newFixedThreadPool(5);

        Runnable task = () -> {
            for (int i = 0; i < 100; ++i) {
                map.put("value", map.get("value") + 1);
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
