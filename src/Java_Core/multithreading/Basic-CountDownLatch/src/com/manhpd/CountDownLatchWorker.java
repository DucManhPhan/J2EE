package com.manhpd;


import java.util.concurrent.CountDownLatch;

/**
 * CountDownLatch enables a java thread to wait until other set of threads completes their tasks.
 *
 * A CountDownLatch is initialized with a given count.
 * The await methods block until the current count reaches zero due to invocations of the countDown() method,
 * after which all waiting threads are released and any subsequent invocations of await return immediately.
 *
 * This is a one-shot phenomenon – the count cannot be reset. If you need a version that resets the count,
 * consider using a CyclicBarrier.
 *
 * e.g. Assume we have divided one task in 5 small independent task.
 * Now main thread should wait, till other 5 Threads finish there work.
 * In these scenarios CountDownLatch can be used.
 */
public class CountDownLatchWorker implements Runnable {

    private CountDownLatch countDownLatch;

    private int workerId;

    public CountDownLatchWorker(CountDownLatch countDownLatch, int workerId) {
        this.countDownLatch = countDownLatch;
        this.workerId = workerId;
    }

    @Override
    public void run() {
        System.out.println("Worker " + workerId + " Started" );
        try {
            Thread.sleep(workerId*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Worker " + workerId + " Completed it's work, Reducing  count of countDownLatch " );
        countDownLatch.countDown();
    }
}

//    await Method
//        Causes the current thread to wait until the latch has counted down to zero, unless the thread is interrupted.
//        If the current count is zero then this method returns immediately.
//        If the current count is greater than zero then the current thread becomes disabled for thread scheduling purposes and lies dormant until one of two things happen:
//
//     countDown Method
//        Decrements the count of the latch, releasing all waiting threads if the count reaches zero.
//        If the current count is greater than zero then it is decremented. If the new count is zero then all waiting threads are re-enabled for thread scheduling purposes.
//        If the current count equals zero then nothing happens.
