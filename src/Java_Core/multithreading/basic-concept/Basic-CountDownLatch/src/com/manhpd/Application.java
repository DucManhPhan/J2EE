package com.manhpd;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

public class Application {

    private static final Logger logger = LogManager.getLogger(Application.class);

    public static void main(String[] args) throws InterruptedException {
//        CountDownLatch countDownLatch = new CountDownLatch(5);
//        ExecutorService executorService = Executors.newCachedThreadPool();
//
//        IntStream.range(1, 6)
//                 .forEach(count -> {
//                     executorService.execute(new CountDownLatchWorker(countDownLatch, count));
//                 });
//
//        System.out.println("Main Thread is wating for workers to finish!!!!!!");
//        countDownLatch.await();
//
//        System.out.println("Work of All Worker is Completed");
//        executorService.shutdown();

        try {
            int a = 5 / 0;
        } catch (Exception ex) {

        }
    }
}

