package com.manhpd;

public class ReadJob implements Runnable {

	private Resources resources;

	public ReadJob(Resources resources) {
		this.resources = resources;
	}

	@Override
	public void run() {
		System.out.println("Read Job");
		resources.read(new Object());
	}
}
