package com.manhpd;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

public class Application {

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(5);
        ExecutorService executorService = Executors.newCachedThreadPool();

        IntStream.range(1, 6)
                 .forEach(count -> {
                     executorService.execute(new CountDownLatchWorker(countDownLatch, count));
                 });

        System.out.println("Main Thread is wating for workers to finish!!!!!!");
        countDownLatch.await();

        System.out.println("Work of All Worker is Completed");
        executorService.shutdown();
    }
}

