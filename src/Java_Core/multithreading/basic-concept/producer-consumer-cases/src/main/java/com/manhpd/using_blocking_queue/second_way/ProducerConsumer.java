package com.manhpd.using_blocking_queue.second_way;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.IntStream;

public class ProducerConsumer {

    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<String> queue = new ArrayBlockingQueue<>(50);

        Callable<String> consumer = () -> {
            int count = 0;
            while (count++ < 50) {
                queue.take();
            }

            return "Consumed " + (count - 1);
        };

        Callable<String> producer = () -> {
            int count = 0;
            while (count++ < 50) {
                queue.put(Integer.toString(count));
            }

            return "Produced " + (count - 1);
        };

        List<Callable<String>> producersAndConsumers = new ArrayList<>();
        IntStream.range(0, 2).forEach(cnt -> producersAndConsumers.add(producer));
        IntStream.range(0, 2).forEach(cnt -> producersAndConsumers.add(consumer));

        System.out.println("Producers and Consumers launched");

        ExecutorService executorService = Executors.newFixedThreadPool(4);
        try {
            List<Future<String>> futures = executorService.invokeAll(producersAndConsumers);
            futures.forEach(future -> {
                try {
                    System.out.println(future.get());
                } catch (InterruptedException | ExecutionException ex) {
                    System.out.println(ex);
                }
            });
        } finally {
            executorService.shutdown();
        }
    }

}
