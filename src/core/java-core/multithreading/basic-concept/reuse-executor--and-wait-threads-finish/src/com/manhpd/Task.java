package com.manhpd;

import java.time.LocalDateTime;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class Task implements Callable<Result> {

    private final String name;

    public Task(String name) {
        this.name = name;
    }

    @Override
    public Result call() throws Exception {
//        System.out.printf("%s: Starting\n", this.name);

        try {
            long duration = (long) (Math.random() * 10);
//            System.out.printf("%s: Waiting %d seconds for results.\n", this.name, duration);
            TimeUnit.SECONDS.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return new Result(this.name, LocalDateTime.now().toString());
    }
}
