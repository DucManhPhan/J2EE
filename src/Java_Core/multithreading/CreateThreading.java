package com.manhpd;

/**
 * 1. Extending the Thread class
 * 
 */
//public class CreateThreading extends Thread {
//
//	@Override
//	public void run() {
//		try {
//			System.out.println("Thread " + Thread.currentThread().getId() + " is running.");
//		} catch (Exception e) {
//			System.out.println("Exception is caught.");
//		}
//	}
//
//	// In main method
////	public static void main(String[] args) {
////		int n = 8;	// number of threads
////		for (int i = 0; i < n; ++i) {
////			CreateThreading thread = new CreateThreading();
////			thread.start();		// threading will be running with start() method
////		}
////	}
//	
//}

/**
 * 2. Implementing the Runable interface
 *
 */
public class CreateThreading implements Runnable {

	@Override
	public void run() {
		try {
			System.out.println("Thread " + Thread.currentThread().getId() + " is running.");
		} catch (Exception e) {
			System.out.println("Exception is caught.");
		}
	}

	// In main method
//	public static void main(String[] args) {
//		int n = 8;
//		for (int i = 0; i < n; ++i) {
//			Thread thread = new Thread(new CreateThreading());
//			thread.start();
//		}
//	}
	
}
