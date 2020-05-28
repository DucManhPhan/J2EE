package com.manhpd;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierWorker implements Runnable {

    private CyclicBarrier cyclicBarrier;

    private int workerId;

    private Random random;

    public CyclicBarrierWorker(CyclicBarrier cyclicBarrier, int workerId) {
        this.cyclicBarrier = cyclicBarrier;
        this.workerId = workerId;
        this.random = new Random();
    }

    @Override
    public void run() {
        System.out.println("Starting worker ID " + this.workerId);
        try {
            Thread.sleep(random.nextInt(4000));
            System.out.println("Worker " + workerId + " Completed it's work, Reducing  count of cyclicBarrier " );
            cyclicBarrier.await();

            System.out.println("Worker " + workerId + " process again.");
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}
