package com.manhpd;

import java.util.concurrent.*;

public class SolutionWithCustomCounter {

    static class MyCounter {
        private int count = 0;

        void increment() {
            count++;
        }

        int getCount() {
            return count;
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ConcurrentHashMap<String, MyCounter> map = new ConcurrentHashMap<>();
        map.put("value", new MyCounter());

        ExecutorService es = Executors.newFixedThreadPool(5);

        Runnable task = () -> {
            for (int i = 0; i < 100; ++i) {
                MyCounter mc = map.get("value");

                // explicit synchronization
                synchronized (mc) {
                    mc.increment();
                }
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

        System.out.println("The value: " + map.get("value").getCount());
    }
}
