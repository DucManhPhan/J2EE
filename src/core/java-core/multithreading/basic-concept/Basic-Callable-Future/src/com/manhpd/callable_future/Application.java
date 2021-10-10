package com.manhpd.callable_future;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;


/**
 * ExecutorService gives the developer the ability to control the number of generated threads
 * and the granularity of tasks which should be executed by separate threads.
 * The best use case for ExecutorService is the processing of independent tasks,
 * such as transactions or requests according to the scheme “one thread for one task.”
 * --> The ExecutorService runs things from a queue (it has a blocking queue internally, holding the submit tasks.
 * --> Fork/Join was designed to speed up work which can be broken into smaller pieces recursively.
 */
public class Application {

    public static void main(String[] args) throws InterruptedException, TimeoutException, ExecutionException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Callable callableTask = () -> {
            TimeUnit.MILLISECONDS.sleep(300);
            return "Task is running.";
        };

        List<Callable<String>> callableTasks = new ArrayList<>();
        callableTasks.add(callableTask);
        callableTasks.add(callableTask);
        callableTasks.add(callableTask);

        // submit() and invokeAll() methods return an object or a collection of type Future.
        Future<String> future = executorService.submit(callableTask);

        List<Future<String>> futures = executorService.invokeAll(callableTasks);

        // When we call get() method of Future interface, it will block execution
        // until the task is properly executed and the result is available. It can make our application's performance
        // degrade. So, we can use timeout with get() method.
        String result = future.get(200, TimeUnit.MILLISECONDS);

        // cancel() method will cancel the task execution
        boolean canceled = future.cancel(true);

        // To check cancellation, use isCanceled() method
        boolean isCanceled = future.isCancelled();
    }

}

// How to use Callable and Future
// The Future interface provides a special blocking method get() which returns an actual result of the Callable task's execution
// or null in the case of Runnable task. Calling the get() method
// while the task is still running will cause execution to block until the task is properly executed and the result is available.
