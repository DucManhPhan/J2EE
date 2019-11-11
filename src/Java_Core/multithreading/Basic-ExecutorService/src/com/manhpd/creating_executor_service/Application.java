package com.manhpd.creating_executor_service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class Application {

    public static void main(String[] args) {
        // (1) - use newCachedThreadPool() method
        // creates an executor having an expandable thread pool.
        // Whenever a thread is needed, pool returns a thread from cache and if not available,
        // a new thread is created for a short time. When the timeout of thread is over, that thread is vanished.
//        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
//        IntStream.range(0, 10)
//                 .forEach(item -> {
//                     cachedThreadPool.execute(new Worker(item));
//                 });
//        cachedThreadPool.shutdown();

        // (2) - use newFixedThreadPool
        // creates a thread pool that reuses a fixed number of threads
        // It means that if in our thread pool have 5 threads, but we want to run 10 threads.
        // After the first 5 threads finished completely, the next 5 thread will run.
//        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(5);
//        IntStream.range(0, 10)
//                 .forEach(item -> {
//                     fixedThreadPool.execute(new Worker(item));
//                 });
//        fixedThreadPool.shutdown();

        // (3) - use newSingleThreadExecutor
        // creates an Executor that uses a single worker thread
        // It means that if in our thread pool definitely have only 1 threads, but we want to run 10 threads.
        // After the first thread will be ran completely, the next threads will run sequence.
//        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
//        IntStream.range(0, 10)
//                 .forEach(item -> {
//                     singleThreadExecutor.execute(new Worker(item));
//                 });
//        singleThreadExecutor.shutdown();

        // (4) - use newScheduledThreadPool
        // creates an Executor that will repeat command at one or multiple times.

        // run task at once time
//        Runnable taskAtOnce = () -> {
//            System.out.println("Running this task at once time.");
//        };
//
//        ScheduledExecutorService scheduleService = Executors.newScheduledThreadPool(1);
//        scheduleService.schedule(taskAtOnce, 5, TimeUnit.SECONDS);
//        System.out.println("Running immediately.");
//
//        scheduleService.shutdown();

        // run task repeatedly after the given delay time: initialDelay + (n * period)
        Runnable taskRepeatedlyDelayTime = () -> {
            System.out.println("Running this task repeatedly.");
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        ScheduledExecutorService scheduledExecutor = Executors.newScheduledThreadPool(1);
        scheduledExecutor.scheduleAtFixedRate(taskRepeatedlyDelayTime, 5, 1, TimeUnit.SECONDS);
        System.out.println("Running immediately.");

//        scheduledExecutor.shutdown();

        // run task repeatedly after the give delay time. But the first time is ran after the initialDelay.


    }
}

class Worker implements Runnable {

    private int id;

    Worker(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        System.out.println("Worker " + id + " is working at " + System.currentTimeMillis());
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

// How to use shutdown() method
// It does not cause an immediate destruction of the ExecutorService.
// It will make the ExecutorService stop accepting new tasks
// and shut down after all running threads finish their current work.


// How to use shutdownNow() method
// It tries to destroy the ExecutorService immediately, but it does not guarantee that all the running threads
// will be stopped at the same time.
// This method returns a list of tasks which are waiting to be processed.
// List<Runnable> notExecutedTasks = executorService.shutdownNow();

// The good way to shutdown the ExecutorService is to use both of these methods combined with the awaitTermination() method.
// Ex:
// executorService.shutdown();
// try {
//          if (!executorService.awaitTermination(800, TimeUnit.MILLISECONDS)) {
//              executorService.shutdownNow();
//          }
// } catch (InterruptException e) {
//      executorService.shutdownNow();
// }
