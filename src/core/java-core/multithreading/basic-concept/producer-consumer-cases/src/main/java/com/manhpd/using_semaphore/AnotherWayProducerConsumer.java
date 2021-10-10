package com.manhpd.using_semaphore;

import java.util.LinkedList;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.stream.IntStream;

public class AnotherWayProducerConsumer {

    private static int MAX_NUM_ELEMENTS = 10;

    private static int[] buffer = new int[MAX_NUM_ELEMENTS];

    private static int count = 0;

    // show the number of empty spaces in the buffer
    private static Semaphore empty = new Semaphore(MAX_NUM_ELEMENTS);

    // show the number of items in the buffer
    private static Semaphore full = new Semaphore(0);

    public static void main(String[] args) {

        Runnable producer = () -> {
            while (true) {
                try {
                    empty.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                // produce items
                buffer[count++] = 1;
                System.out.println("Thread " + Thread.currentThread().getName() + " is working.");
                full.release();
            }
        };

        Runnable consumer = () -> {
            while (true) {
                try {
                    full.acquire();
                } catch (InterruptedException ex) {
                    System.out.println(ex);
                }

                System.out.println("Get element " + buffer[count--] + " in thread " + Thread.currentThread().getName());
                empty.release();
            }
        };

        ExecutorService executor = Executors.newFixedThreadPool(2);
        IntStream.range(0, 1).forEach(cnt -> executor.execute(producer));
        IntStream.range(0, 1).forEach(cnt -> executor.execute(consumer));

        executor.shutdown();
        System.out.println("The end of working");
    }

}
