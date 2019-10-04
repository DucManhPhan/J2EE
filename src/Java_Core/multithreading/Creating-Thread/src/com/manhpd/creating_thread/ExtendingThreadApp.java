package com.manhpd.creating_thread;

import java.util.Arrays;
import java.util.List;

/**
 * 1. Extending the Thread class
 * 
 */
public class ExtendingThreadApp {

	public static void main(String[] args) {
		System.out.println("Start main thread.");

		Thread thread = new ChildThread();
		thread.start();

		System.out.println("End main thread.");
	}

}

class ChildThread extends Thread {

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