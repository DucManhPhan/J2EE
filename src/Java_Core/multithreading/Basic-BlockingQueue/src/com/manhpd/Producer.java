package com.manhpd;

import java.util.Random;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;

public class Producer implements Runnable {

    private BlockingQueue<Integer> blockingQueue;

    private Random random;

    public Producer(BlockingQueue<Integer> blockingQueue) {
        this.blockingQueue = blockingQueue;
        this.random = new Random();
    }

    @Override
    public void run() {
        while (true) {
            System.out.println("Producer adds element.");
            try {
                this.blockingQueue.put(random.nextInt(3000));
                System.out.println("Added Element : Current Size of Q " + this.blockingQueue.size());
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
