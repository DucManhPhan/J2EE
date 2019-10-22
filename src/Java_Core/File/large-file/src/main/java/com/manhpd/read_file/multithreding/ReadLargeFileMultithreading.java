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

    public void readFile() {
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<String>(5);
        ExecutorService service = Executors.newFixedThreadPool(2);

        service.submit(new Producer(blockingQueue, this.path));
        service.submit(new Consumer(blockingQueue));

        service.shutdown();
    }

}
