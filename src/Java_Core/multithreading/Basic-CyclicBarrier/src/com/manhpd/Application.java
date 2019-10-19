package com.manhpd;


import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

/**
 * CyclicBarrier allows a set of threads to all wait for each other to reach a common barrier point.
 * CyclicBarriers are useful in programs involving a fixed sized party of threads that must occasionally wait for each other.
 * The barrier is called cyclic because it can be re-used after the waiting threads are released.
 *
 * CyclicBarrier are Similar to CountDownLatch but CyclicBarrier provide some additional features like
 * Reseting CyclicBarrier & Supports an optional Runnable command that is run once per barrier point.
 *
 */
public class Application {

    public static void main(String[] args) throws BrokenBarrierException, InterruptedException {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(5, ()->System.out.println("Barrier point reach ::: All Task Completed"));
        ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(10);
        IntStream.range(1,6)
                .forEach(cnt->{newFixedThreadPool.submit(new CyclicBarrierWorker(cyclicBarrier, cnt));
                });
        System.out.println("All Task Submited");
//        cyclicBarrier.await();

        newFixedThreadPool.shutdown();
    }

}
