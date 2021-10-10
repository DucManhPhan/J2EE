package com.manhpd.fourth_way.producer_consumer;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class UsingProducerConsumerWay {

    private String path;

    private BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(5);

    public UsingProducerConsumerWay() {

    }

    public UsingProducerConsumerWay(String path) {
        this.path = path;
    }

    public void readFile() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.execute(new Producer(this.blockingQueue, this.path));
        Thread.sleep(5000);

        executorService.execute(new Consumer(this.blockingQueue));
        executorService.shutdown();
    }

}
