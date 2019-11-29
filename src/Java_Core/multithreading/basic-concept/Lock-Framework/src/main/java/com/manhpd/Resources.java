package com.manhpd;

import java.util.Date;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Resources {

	private final Lock displayJobLock = new ReentrantLock();

	private final Lock readJobLock = new ReentrantLock();

	public void display(Object document) {
		final Lock lock = this.displayJobLock;
		System.out.println("The " + Thread.currentThread().getName() + " is waiting in display job.");
		lock.lock();
		System.out.println("The " + Thread.currentThread().getName() + " is acquiring this lock in display job.");
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
			System.out.println("The " + Thread.currentThread().getName() + " is waiting in read job.");
			lock.lock();
			System.out.println("The " + Thread.currentThread().getName() + " is acquiring this lock in read job.");
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
