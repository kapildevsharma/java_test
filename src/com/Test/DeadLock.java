package com.Test;

public class DeadLock {
	public static void main(String[] args) throws InterruptedException {

		// second method for deadlock
		Object obj1 = new Object();
		Object obj2 = new Object();
		Object obj3 = new Object();

		Thread t1 = new Thread(new SyncThread(obj1, obj2), "t1");
		Thread t2 = new Thread(new SyncThread(obj2, obj3), "t2");
		Thread t3 = new Thread(new SyncThread(obj3, obj1), "t3");

		t1.start();
		Thread.sleep(5000);
		t2.start();
		Thread.sleep(5000);
		t3.start();
	}
}

class SyncThread implements Runnable {
	private Object obj1;
	private Object obj2;

	public SyncThread(Object o1, Object o2) {
		this.obj1 = o1;
		this.obj2 = o2;
	}

	@Override
	public void run() {
		String name = Thread.currentThread().getName();
		System.out.println(name + " acquiring lock on " + obj1);
		synchronized (obj1) {
			System.out.println(name + " acquired lock on main sync " + obj1);
			work();
			// for remove deadlock so put the below sync block outside sync obj block.
			System.out.println(name + " acquiring lock on after waiting " + obj2);
			synchronized (obj2) {
				System.out.println(name + " acquired lock on secone sync " + obj2);
				work();
			}
			System.out.println(name + " released lock after waiting second obj on " + obj2);
		}
		System.out.println(name + " released lock on main obj " + obj1);
		System.out.println(name + " finished execution.");
	}

	private void work() {
		try {
			Thread.sleep(30000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

// This class is shared by both threads
class Shared {
	// first synchronized method
	synchronized void test1(Shared s2) {
		String name = Thread.currentThread().getName();
		System.out.println(name + " acquiring lock on ");

		System.out.println("test1-begin");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		// taking object lock of s2 enters into test2 method
		s2.test2();
		try {
			wait();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("test1-end");
	}

	// second synchronized method
	synchronized void test2() {
		notify();
		System.out.println("test2-begin");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		// taking object lock of s1 enters into test1 method
		System.out.println("test2-end");
	}
}

class Thread1 extends Thread {
	private Shared s1;
	private Shared s2;

	public Thread1(Shared s1, Shared s2) {
		this.s1 = s1;
		this.s2 = s2;
	}

	@Override
	public void run() {
		s1.test1(s2);
	}
}

class Thread2 extends Thread {
	private Shared s1;
	private Shared s2;

	public Thread2(Shared s1, Shared s2) {
		this.s1 = s1;
		this.s2 = s2;
	}

	@Override
	public void run() {
		s2.test1(s1);
	}
}