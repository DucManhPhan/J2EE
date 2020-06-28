package com.manhpd;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

public class AtomicCounter {

//    private static AtomicInteger counter = new AtomicInteger(0);
    private static MyAtomicInteger counter = new MyAtomicInteger(0);

    public static void main(String[] args) {

        Runnable increThread = () -> {
//            LongStream.range(0, 1_000).forEach(cnt -> counter.incrementAndGet());
            LongStream.range(0, 1_000).forEach(cnt -> counter.myIncrementAndGet());
        };

        Runnable decreThread = () -> {
//            LongStream.range(0, 1_000).forEach(cnt -> counter.decrementAndGet());
            LongStream.range(0, 1_000).forEach(cnt -> counter.decrementAndGet());
        };

        ExecutorService executorService = Executors.newFixedThreadPool(8);
        List<Future<?>> futures = new ArrayList<>();

        try {
            IntStream.range(0, 4).forEach(cnt -> futures.add(executorService.submit(increThread)));
            IntStream.range(0, 4).forEach(cnt -> futures.add(executorService.submit(decreThread)));

            futures.forEach(future -> {
                try {
                    future.get();
                } catch (InterruptedException | ExecutionException ex) {
                    System.out.println(ex);
                }
            });

            System.out.println("counter = " + counter);
        } finally {
            executorService.shutdown();
        }
    }

}
