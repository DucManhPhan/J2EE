package com.manhpd.read_file.multithreding;

import java.util.concurrent.BlockingQueue;

public class Consumer implements Runnable {

    private BlockingQueue<String> blockingQueue;

    public Consumer(BlockingQueue<String> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run() {
        while (!this.blockingQueue.isEmpty()) {
            synchronized (this.blockingQueue) {
                if (!this.blockingQueue.isEmpty()) {
                    try {
                        String result = this.blockingQueue.take();
                        System.out.println(result + "\n");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
