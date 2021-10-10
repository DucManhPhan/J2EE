package com.manhpd.creating_thread;

import java.util.Arrays;
import java.util.List;


/**
 * Runnable is a functional interface, so we can use lambda expression to provide its implementation
 * rather than using anonymous class.
 *
 */
public class Java8ThreadApp {

    public static void main(String[] args) {
        Runnable task = () -> {
            System.out.println("Start " + Thread.currentThread().getName() + " thread");
            Integer[] numbers = {1, 3, 5, 9, 3, 8};
            List<Integer> lst = Arrays.asList(numbers);

            lst.stream().forEach(item -> {
                System.out.println(item);
            });
        };

        new Thread(task).start();
    }

}
