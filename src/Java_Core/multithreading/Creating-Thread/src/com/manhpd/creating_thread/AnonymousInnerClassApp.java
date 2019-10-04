package com.manhpd.creating_thread;


import java.util.Arrays;
import java.util.List;

/**
 * Anonymous Inner class is an inner class that is declared without any class name.
 * We can define an anonymous inner class within a method or even within an argument to a method.
 *
 */
public class AnonymousInnerClassApp {

    public static void main(String[] args) {
        System.out.println("Start main thread.");

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Start " + Thread.currentThread().getName() + " thread");
                Integer[] numbers = {1, 3, 5, 9, 3, 8};
                List<Integer> lst = Arrays.asList(numbers);

                lst.stream().forEach(item -> {
                    System.out.println(item);
                });
            }
        }).start();
    }

}
