package com.manhpd;


public class MultithreadingExample {

	private static final int NUM_READ_JOB = 5;

	private static final int NUM_DISPLAY_JOB = 5;

	public static void main(String[] args) {
		Resources resources = new Resources();
		Thread[] threads = new Thread[10];

		// initialize Read Job thread
		for (int i = 0; i < NUM_READ_JOB; ++i) {
			threads[i] = new Thread(new ReadJob(resources), "Thread " + i);
		}

		// initialize Display Job thread
		for (int i = 0; i < NUM_DISPLAY_JOB; ++i) {
			threads[NUM_READ_JOB + i] = new Thread(new DisplayJob(resources), "Thread " + (NUM_DISPLAY_JOB + i));
		}
		
		for (int i = 0; i < NUM_DISPLAY_JOB + NUM_READ_JOB; ++i) {
			threads[i].start();
		}
	}

}
