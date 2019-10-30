package com.manhpd.read_file.multithreding;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import java.util.concurrent.BlockingQueue;

public class Consumer implements Runnable {

    private static final Logger LOGGER = (Logger) LogManager.getLogger(Consumer.class.getName());

    private BlockingQueue<String> blockingQueue;

    public Consumer(BlockingQueue<String> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run() {
        while (true) {//!this.blockingQueue.isEmpty()) {
            synchronized (this.blockingQueue) {
//                LOGGER.info("Blocking queue is empty or not ? - " + this.blockingQueue.isEmpty());
                if (!this.blockingQueue.isEmpty()) {
                    try {
                        String result = this.blockingQueue.take();
                        LOGGER.info("Data from consumer: " + result + "\n");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
