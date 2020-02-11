package com.manhpd;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Application {

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        int loopCount = 5;

        for (int counter = 0; counter < loopCount; ++counter) {
            List<Task> taskList = new ArrayList<>();
            for (int i = 0; i < 5; i++) {
                Task task = new Task("Task-" + i + counter);
                taskList.add(task);
            }

            //Execute all tasks and get reference to Future objects
            List<Future<Result>> resultList = null;

            try {
                resultList = executor.invokeAll(taskList);
                System.out.println("The number of result: " + resultList.size());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

//            executor.shutdown();

            System.out.println("\n========Printing the results with counter = " + counter + " ======");

            for (int i = 0; i < resultList.size(); i++) {
                Future<Result> future = resultList.get(i);
                try {
                    Result result = future.get();
                    System.out.println(result.getName() + ": " + result.getTimestamp());
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
            }
        }

        System.out.println("Finish all threads of loops");
        executor.shutdown();
    }

}
