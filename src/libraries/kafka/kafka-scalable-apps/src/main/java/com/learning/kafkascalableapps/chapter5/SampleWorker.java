package com.learning.kafkascalableapps.chapter5;


import com.yammer.metrics.stats.Sample;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class SampleWorker implements Runnable {

    private static BlockingQueue<String> requestQueue
            = new ArrayBlockingQueue<String>(100);

    private static final AtomicInteger pendingItems = new AtomicInteger();

    private String workerId;

    public SampleWorker(String workerId) {

        super();
        System.out.println("Creating worker for " + workerId);
        this.workerId =workerId;
    }

    public static void addToQueue(String order) {
        pendingItems.incrementAndGet();
        try {
            requestQueue.put(order);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static int getPendingCount() {

        return pendingItems.get();
    }

    @Override
    public void run() {

        while(true) {
            try {
                String order = requestQueue.take();
                System.out.println("Worker " + workerId
                            + " Processing : " + order);

                //Do all required processing
                Thread.sleep(100);

                //After all processing is done
                pendingItems.decrementAndGet();
            }
            catch(Exception e) {
                e.printStackTrace();
            }
        }
    }
}
