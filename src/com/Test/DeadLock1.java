package com.Test;

public class DeadLock1 {
	static class ThreadOne implements Runnable {

		public void run() {
			synchronized (Integer.class) {
				System.out.println(Thread.currentThread().getName() + " - Got lock on Integer.class");
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				synchronized (String.class) {
					System.out.println(Thread.currentThread().getName() + " - Got lock on String.class");
				}
			}
		}
	}

	static class ThreadTwo implements Runnable {

		public void run() {
			synchronized (String.class) {
				System.out.println(Thread.currentThread().getName() + " - Got lock on String.class");
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				synchronized (Integer.class) {
					System.out.println(Thread.currentThread().getName() + " - Got lock on Integer.class");
				}
			}
		}

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new Thread(new ThreadOne(), "ThreadOne").start();
		new Thread(new ThreadTwo(), "ThreadTwo").start();
	}

}
