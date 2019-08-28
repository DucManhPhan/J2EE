package com.manhpd.message_passing;

import java.util.Date;
import java.util.Vector;

public class Producer extends Thread {

	static final int MAX = 7;
	private Vector<String> messages = new Vector();

	@Override
	public void run() {
		try {
			while (true) {
				// producing a message to send the consumer
				putMessage();
				System.out.println("Put message to block queue.");

				// producer goes to sleep when the queue is full
				sleep(100);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private synchronized void putMessage() throws InterruptedException {
		// check whether the queue is full or not
		while (messages.size() == MAX) {
			wait();
		}

		messages.addElement(new Date().toString());
		notify();
	}

	public synchronized String getMessage() throws InterruptedException {
		notify();
		while (messages.size() == 0) {
			wait();
		}

		String message = (String) messages.firstElement();
		messages.removeElement(message);
		return message;
	}

//	public static void main(String[] args) {
//		Producer producer = new Producer();
//		producer.start();
//
//		new Consumer(producer).start();
//	}
}
