package com.manhpd.read_file.multithreding;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ReadLargeFileMultithreading {

    private String path;

    private BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<String>(5);

    public ReadLargeFileMultithreading() {
        this("large-random-number.txt");
    }

    public ReadLargeFileMultithreading(String path) {
        this.path = path;
    }

    public void readFile() throws InterruptedException {
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<String>(5);
        ExecutorService service = Executors.newFixedThreadPool(2);

        service.execute(new Producer(blockingQueue, this.path));
        Thread.sleep(5000);
        service.execute(new Consumer(blockingQueue));

        service.shutdown();
        System.out.println("The end - Two threads will be destroyed.");
    }

}
