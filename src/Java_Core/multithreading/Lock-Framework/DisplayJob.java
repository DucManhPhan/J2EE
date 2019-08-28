package com.manhpd.lock_framework;

public class DisplayJob implements Runnable{

	private Resources resources;

	public DisplayJob(Resources resources) {
		this.resources = resources;
	}

	@Override
	public void run() {
		System.out.println("Display Job");
		resources.display(new Object());
	}

}
