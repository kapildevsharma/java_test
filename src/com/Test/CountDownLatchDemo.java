package com.Test;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchDemo {
	public static void main(String args[]) throws InterruptedException {
		// Let us create task that is going to
		// wait for four threads before it starts
		CountDownLatch latch = new CountDownLatch(4);

		// Let us create four worker
		// threads and start them.
		Worker first = new Worker(1000, latch, "WORKER-1");
		Worker second = new Worker(2000, latch, "WORKER-2");
		Worker third = new Worker(3000, latch, "WORKER-3");
		Worker fourth = new Worker(4000, latch, "WORKER-4");
		first.start();
		second.start();
		third.start();
		fourth.start();

		// The main task waits for four threads to complete their works
		latch.await();
		System.out.println("After await : "+latch.getCount());
		// Main thread has started
		System.out.println(Thread.currentThread().getName() + " has finished");
	}
}

// A class to represent threads for which
// the main thread waits.
class Worker extends Thread {
	private int delayTime;
	private CountDownLatch latch;

	public Worker(int delayTime, CountDownLatch latch, String threadName) {
		super(threadName);
		this.delayTime = delayTime;
		this.latch = latch;
	}

	@Override
	public void run() {
		try {
			Thread.sleep(delayTime);
			latch.countDown();
			System.out.println(Thread.currentThread().getName() + " finished");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}