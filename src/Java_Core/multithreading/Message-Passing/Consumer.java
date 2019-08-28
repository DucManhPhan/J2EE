package com.manhpd.message_passing;

public class Consumer extends Thread {

	private Producer producer;

	public Consumer(Producer producer) {
		this.producer = producer;
	}

	@Override
	public void run() {
		try {
			while (true) {
				String message = producer.getMessage();

				// sends a reply to producer got a message
				System.out.println("Got message: " + message);
				sleep(200);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
