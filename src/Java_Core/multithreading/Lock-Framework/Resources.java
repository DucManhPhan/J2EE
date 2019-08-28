package com.manhpd.lock_framework;

import java.util.Date;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Resources {

	private final Lock displayJobLock = new ReentrantLock();

	private final Lock readJobLock = new ReentrantLock();

	public void display(Object document) {
		final Lock lock = this.displayJobLock;
		lock.lock();
		try {
			Long duration = (long) (Math.random() * 10000);
			System.out.println(Thread.currentThread().getName() + ": Resources: display a Job during "
					+ (duration / 1000) + " seconds ::" + " Time - " + new Date());

			Thread.sleep(duration);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			System.out.printf("%s: The document has been" + " displayed\n", Thread.currentThread().getName());
			lock.unlock();
		}
	}

	public void read(Object document) {
		final Lock lock = this.readJobLock;
		try {
			Long duration = (long) (Math.random() * 10000);
			lock.lock();
			System.out.println(Thread.currentThread().getName() + ": Resources: reading a Job during "
					+ (duration / 1000) + " seconds :: Time - " + new Date());
			Thread.sleep(duration);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			System.out.printf("%s: The document has" + " been read\n", Thread.currentThread().getName());
			lock.unlock();
		}
	}
}
