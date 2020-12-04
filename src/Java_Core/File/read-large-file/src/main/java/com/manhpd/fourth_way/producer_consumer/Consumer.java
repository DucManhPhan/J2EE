package com.manhpd.fourth_way.producer_consumer;

import java.util.concurrent.BlockingQueue;

public class Consumer implements Runnable {

    private BlockingQueue<String> blockingQueue;

    public Consumer(BlockingQueue<String> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (this.blockingQueue) {
                if (!this.blockingQueue.isEmpty()) {
                    try {
                        String result = this.blockingQueue.take();
                        System.out.println("Data from Consumer: " + result);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
