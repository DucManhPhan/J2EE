package com.manhpd.using_semaphore;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

public class ProducerConsumerSemaphore {

    public static void main(String[] args) {
        Buffer buffer = new Buffer();

        Runnable producer = () -> {
            IntStream.range(1, 51).forEach(cnt -> buffer.put(cnt));
        };

        Runnable consumer = () -> {
            IntStream.range(1, 51).forEach(cnt -> buffer.get());
        };

        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.submit(producer);
        executorService.submit(consumer);

        System.out.println("Working producer and consumer with using Semaphore");

        executorService.shutdown();
    }



}
