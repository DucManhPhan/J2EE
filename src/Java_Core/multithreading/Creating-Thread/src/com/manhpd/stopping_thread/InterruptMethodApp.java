package com.manhpd.stopping_thread;


/**
 * 2. Using interrupt() method
 * Whenever we call interrupt() method on a thread, it sets the interrupted status of a thread.
 * The status can be obtained by interrupted() method.
 * This status is used in a while loop to stop a thread.
 *
 */
public class InterruptMethodApp {

    public static void main(String[] args) {
		Thread thread = new Thread(new StoppingThread());
		thread.start();

		try {
			thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// interrupt the thread
		thread.interrupt();
    }

}

class StoppingThread implements Runnable {

    @Override
    public void run() {
        while (!Thread.interrupted()) {
            System.out.println("I am running....");
        }

        System.out.println("Stopped Running....");
    }

}
