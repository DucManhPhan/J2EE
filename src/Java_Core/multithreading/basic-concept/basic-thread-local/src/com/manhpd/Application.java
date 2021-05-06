package com.manhpd;

import com.manhpd.utils.ThreadUtils;

import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Application {

    public static void main(String[] args) throws InterruptedException {
        Runnable first = () -> {
            ThreadUtils.sleep(5000);

            ThreadId.increment();
            System.out.println("THREAD_ID: " + ThreadId.get());
            ThreadId.remove();
        };

        Runnable second = () -> {
            ThreadUtils.sleep(3000);

            System.out.println("THREAD_ID: " + ThreadId.get());
            ThreadId.remove();
        };

//        startThreads(first, second);
        startThreadsWithJoin(first, second);
    }

    public static void startThreads(Runnable ... runnables) {
        ExecutorService service = Executors.newFixedThreadPool(2);
        Arrays.asList(runnables).forEach(runnable -> service.submit(runnable));
        service.shutdown();
    }

    public static void startThreadsWithJoin(Runnable ... runnables) throws InterruptedException {
        for (Runnable runnable : Arrays.asList(runnables)) {
            Thread thread = new Thread(runnable);
            thread.start();

            thread.join();
        }
    }

}
