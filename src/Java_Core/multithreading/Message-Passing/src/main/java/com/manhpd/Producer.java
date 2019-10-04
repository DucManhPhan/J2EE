package com.manhpd;

import java.io.*;
import java.util.Vector;

public class Producer extends Thread {

	static final int MAX = 7;

	private Vector<String> messages = new Vector();

	private static final String path = "./test-file.txt";

	@Override
	public void run() {
		try {
				// producing a message to send the consumer
				System.out.println("Put message to block queue.");
				readFile(path);
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	private synchronized void putMessage(String message) throws InterruptedException {
		// check whether the queue is full or not
		while (messages.size() == MAX) {
			wait();
		}

		messages.addElement(message);
		notify();
	}

	/**
	 * When the wait() method is called in getMessage() method.
	 * Firstly, it releases the lock it holds on Producer object.
	 * Secondly it makes the Consumer thread to go on a waiting state until
	 * all other threads have terminated,
	 * that is it can again acquire a lock on Producer object
	 * and some other method wakes it up by invoking notify or notifyAll on the same object.
	 *
	 * @return
	 * @throws InterruptedException
	 */
	public synchronized String getMessage() throws InterruptedException {
		notify();
		while (messages.size() == 0) {
			wait();
		}

		String message = (String) messages.firstElement();
		messages.removeElement(message);
		return message;
	}

	public synchronized void readFile(String path) throws IOException, InterruptedException {
		BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(path)));
		String line = "";

		while ((line = bufferedReader.readLine()) != null) {
			this.putMessage(line);
		}
	}
}
