package com.Test;

import java.util.concurrent.atomic.AtomicBoolean;

public class ThreadStopExample implements Runnable {

	private Thread worker;
	private final AtomicBoolean running = new AtomicBoolean(false);
	private final int interval;

	public ThreadStopExample(int sleepInterval) {
		interval = sleepInterval;
	}

	public void start() {
		worker = new Thread(this);
		worker.start();
	}

	public void stop() {
		running.set(false);
		worker.interrupt();
	}

	public void run() {
		running.set(true);
		while (running.get()) {
			try {
				System.out.println("Thread was started");
				Thread.sleep(interval);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
				System.out.println("Thread was interrupted, Failed to complete operation");
			}
			// do something here
		}
	}

	public static void main(String[] args) throws InterruptedException {
		ThreadStopExample obj = new ThreadStopExample(100);
		obj.start();

		Thread.sleep(1000);
		obj.stop();
	}
}