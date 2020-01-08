package com.manhpd.reentrant_lock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

public class UsingReentrantLock {

    public static void main(String[] args) {
        ReentrantLock reentrantLock = new ReentrantLock();
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        IntStream.range(1, 5)
                 .forEach(item -> {
                    executorService.execute(new Worker("Job" + item, reentrantLock));
                 });

        executorService.shutdown();
    }

}
