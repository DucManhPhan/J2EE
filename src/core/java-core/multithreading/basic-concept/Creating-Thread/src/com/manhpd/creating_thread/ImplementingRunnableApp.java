package com.manhpd.creating_thread;

import java.util.Arrays;
import java.util.List;

public class ImplementingRunnableApp {

	public static void main(String[] args) {
        System.out.println("Start main thread.\n");

        Thread thread = new Thread(new ThreadRunner());
        thread.start();

        System.out.println("End main thread.\n");
	}
}

class ThreadRunner implements Runnable {

    @Override
    public void run() {
        System.out.println("Start " + Thread.currentThread().getName() + " thread");
        Integer[] numbers = {1, 3, 5, 9, 3, 8};
        List<Integer> lst = Arrays.asList(numbers);

        lst.stream().forEach(item -> {
            System.out.println(item);
        });
    }

}