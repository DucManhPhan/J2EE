package com.manhpd;

/**
 * There are two way to stop threading:
 * 1. Using flag boolean variable
 * 2. Using interrupt() method
 * 
 * Actually, using interrupt() method is probably preferrable to using flag boolean variable.
 * The reason behind that if we're in an interruptable blocking call (like Thread.sleep or using java.nio Channel operations),
 * we'll actually be able to break out of those right away. 
 * 
 * If we use a flag, we have to wait for the blocking operation to finish and then we can check our flag.
 * In some cases, we have to do this anyway, such as using standard Inputstream / Outputstream which are not interruptable.
 * 
 * In that case, when a thread is interrupted, it will not interrupt the IO, however, we can easily do this routinely in our code (
 * and we should do this at strategic points where we can safely stop and cleanup).
 * 
 * if (Thread.currentThread().isInterrupted()) {
 * 		// cleanup and stop execution
 * 		// Ex: break in a loop
 * }
 * 
 * The main advantage to Thread.interrupt() method is that we can immediately break out of interruptable calls,
 * which we can't do this with flag approach.
 * 
 */

/**
 * 1. Using boolean variable
 *
 */
//public class StopThreading extends Thread {
//	// Make sure this flag is thread safe by using volatile variable or
//	// by using getter/setter methods which are synchronised with the variable being used as the flag.
//	private volatile boolean flag = true;
//
//	public void stopRunning() {
//		flag = false;
//	}
//
//	@Override
//	public void run() {
//		// check flag boolean to stop thread
//		while (flag) {
//			// implements our operations in while loop
//			// ...
//
//			System.out.println("I am running....");
//		}
//
//		System.out.println("Stopped Running....");
//	}

//	public static void main(String[] args) {
//		StopThreading thread = new StopThreading();
//		thread.start();
//	
//		try {
//			thread.sleep(100);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//	
//		thread.stopRunning();
//		thread.join();	// after, set the flag = false to terminate thread, then, we should call join() method and wait for it to finish.
//	}

//}

/**
 * 2. Using interrupt() method
 * Whenever we call interrupt() method on a thread, it sets the interrupted status of a thread.
 * The status can be obtained by interrupted() method.
 * This status is used in a while loop to stop a thread.
 * 
 */
public class StopThreading implements Runnable{

	@Override
	public void run() {
		while (!Thread.interrupted()) {
			System.out.println("I am running....");
		}

		System.out.println("Stopped Running....");
	}

//	public static void main(String[] args) {
//		Thread thread = new Thread(new StopThreading());
//		thread.start();
//
//		try {
//			thread.sleep(100);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//
//		// interrupt the thread
//		thread.interrupt();
//	}

}