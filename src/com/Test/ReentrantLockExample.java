package com.Test;

//Java code to illustrate Reentrant Locks
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class worker1 implements Runnable {
	String name;
	ReentrantLock re;

	public worker1(ReentrantLock rl, String n) {
		re = rl;
		name = n;
	}

	public void run() {
		boolean done = false;
		while (!done) {
			// Getting Outer Lock
			boolean ans = re.tryLock();

			// Returns True if lock is free
			if (ans) {
				try {
					Date d = new Date();
					SimpleDateFormat ft = new SimpleDateFormat("hh:mm:ss");
					System.out.println(
							"task name - " + name + " outer lock acquired at " + ft.format(d) + " Doing outer work");
					Thread.sleep(1500);

					// Getting Inner Lock
					re.lock();
					try {
						d = new Date();
						ft = new SimpleDateFormat("hh:mm:ss");
						System.out.println("task name - " + name + " inner lock acquired at " + ft.format(d)
								+ " Doing inner work");
						System.out.println("Lock Hold Count - " + re.getHoldCount());
						Thread.sleep(1500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					} finally {
						// Inner lock release
						System.out.println("task name - " + name + " releasing inner lock");

						re.unlock();
					}
					System.out.println("Lock Hold Count - " + re.getHoldCount());
					System.out.println("task name - " + name + " work done");

					done = true;
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
					// Outer lock release
					System.out.println("task name - " + name + " releasing outer lock");

					re.unlock();
					System.out.println("Lock Hold Count - " + re.getHoldCount());
				}
			} else {
				System.out.println("task name - " + name + " waiting for lock");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}

public class ReentrantLockExample {
	static final int MAX_T = 2;

	public static void main(String[] args) throws InterruptedException {
		ReentrantLock rel = new ReentrantLock();
		ExecutorService pool = Executors.newFixedThreadPool(MAX_T);
		Runnable w1 = new worker1(rel, "Job1");
		Runnable w2 = new worker1(rel, "Job2");
		Runnable w3 = new worker1(rel, "Job3");
		Runnable w4 = new worker1(rel, "Job4");
		pool.execute(w1);
		pool.execute(w2);
		pool.execute(w3);
		pool.execute(w4);
		pool.shutdown();

        SharedResource resource = new SharedResource();
        // Writer thread
        Thread writer = new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                resource.write(i);
                try { Thread.sleep(100); } catch (InterruptedException e) { e.printStackTrace(); }
            }
        }, "Writer");

        // Reader threads
        Thread reader1 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                resource.read();
                try { Thread.sleep(50); } catch (InterruptedException e) { e.printStackTrace(); }
            }
        }, "Reader-1");

        Thread reader2 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                resource.read();
                try { Thread.sleep(50); } catch (InterruptedException e) { e.printStackTrace(); }
            }
        }, "Reader-2");

        writer.start();
        reader1.start();
        reader2.start();

        try {
            writer.join();
            reader1.join();
            reader2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        // Create a thread pool with 5 threads
        ExecutorService executor = Executors.newFixedThreadPool(5);

        // Submit writer tasks
        executor.submit(() -> {
            for (int i = 1; i <= 5; i++) {
                resource.write(i);
                try { Thread.sleep(100); } catch (InterruptedException e) { e.printStackTrace(); }
            }
        });

        // Submit multiple reader tasks
        for (int i = 0; i < 3; i++) {
            executor.submit(() -> {
                for (int j = 0; j < 5; j++) {
                    resource.read();
                    try { Thread.sleep(50); } catch (InterruptedException e) { e.printStackTrace(); }
                }
            });
        }

        // Shutdown executor gracefully
        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.MINUTES);
    }
}


class SharedResource {
    private final ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();
    private int data;

    public int read() {
        rwLock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + " reading: " + data);
            return data;
        } finally {
            rwLock.readLock().unlock();
        }
    }

    public void write(int value) {
        rwLock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + " writing: " + value);
            data = value;
        } finally {
            rwLock.writeLock().unlock();
        }
    }
}