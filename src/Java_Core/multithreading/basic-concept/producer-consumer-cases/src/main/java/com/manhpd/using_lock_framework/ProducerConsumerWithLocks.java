package com.manhpd.using_lock_framework;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

public class ProducerConsumerWithLocks {

    public static boolean isEmpty(List<Integer> buffer) {
        return buffer.size() == 0;
    }

    public static boolean isFull(List<Integer> buffer) {
        return buffer.size() == 10;
    }

    public static void main(String[] args) {
        List<Integer> buffer = new ArrayList<>();
        Lock lock = new ReentrantLock();
        Condition isEmpty = lock.newCondition();
        Condition isFull = lock.newCondition();

        class Consumer implements Callable<String> {
            public String call() throws InterruptedException, TimeoutException {
                int count = 0;
                while (count++ < 50) {
                    try {


                        lock.lock();
                        while (isEmpty(buffer)) {
                            // wait
                            if (!isEmpty.await(10, TimeUnit.MILLISECONDS)) {
                                throw new TimeoutException("Consumer time out");
                            }
                        }

                        buffer.remove(buffer.size() - 1);
                        // signal
                        isFull.signalAll();
                    } finally {
                        lock.unlock();
                    }
                }

                return "Consumer " + (count - 1);
            }
        }

        class Producer implements Callable<String> {

            @Override
            public String call() throws Exception {
                int count = 0;
                while (count++ < 50) {
                    try {
                        lock.lock();

                        while (isFull(buffer)) {
                            // wait
                            isFull.await();
                        }

                        buffer.add(1);
                        // signal
                        isEmpty.signalAll();
                    } finally {
                        lock.unlock();
                    }
                }

                return "Producer " + (count - 1);
            }
        }

        List<Producer> producers = new ArrayList<>();
        IntStream.range(0, 4).forEach(item -> producers.add(new Producer()));

        List<Consumer> consumers = new ArrayList<>();
        IntStream.range(0, 4).forEach(item -> consumers.add(new Consumer()));

        System.out.println("Producers and Consumers launched");

        List<Callable<String>> producersAndConsumers = new ArrayList<>();
        producersAndConsumers.addAll(producers);
        producersAndConsumers.addAll(consumers);

        ExecutorService executorService = Executors.newFixedThreadPool(8);
        try {
            List<Future<String>> futures = executorService.invokeAll(producersAndConsumers);
            futures.forEach(future -> {
                try {
                    System.out.println(future.get());
                } catch (InterruptedException | ExecutionException ex) {
                    System.out.println("Exception: " + ex.getMessage());
                }
            });
        } catch (InterruptedException e) {
            System.out.println(e);
        } finally {
            executorService.shutdown();
            System.out.println("Executor service shutdown");
        }
    }

}
