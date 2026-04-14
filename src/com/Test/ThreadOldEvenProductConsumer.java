package com.Test;

import java.util.LinkedList;
import java.util.concurrent.*;

class Producer implements Runnable {

    final BlockingQueue<Integer> queue;
	Producer() {
        queue = new ArrayBlockingQueue<>(10);
	}

	public void run() {
		synchronized (queue) {
			for (int i = 0; i < 10; i++) {
				try {
                    queue.add(i);
					System.out.println("Produced " + i);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			System.out.println("Buffer is FUll");
            queue.notify();
		}
	}
}

class Consumer implements Runnable {
	Producer p;

	Consumer(Producer temp) {
		p = temp;
	}

	public void run() {
		synchronized (p.queue) {
			System.out.println("Consumer is wait");
			try {
				p.queue.wait();

			for (int i = 0; i < 10; i++) {
                System.out.println("Consumed : " + p.queue.take() );
            }
            } catch (Exception e) {
                e.printStackTrace();
            }
			System.out.println("\n Queue is Empty");
		}
	}
}

public class ThreadOldEvenProductConsumer {
	public static void main(String[] args) throws InterruptedException {

		Producer p = new Producer();
		Consumer c = new Consumer(p);
		Thread t11 = new Thread(p);
		Thread t12 = new Thread(c);
		t12.start();
		t11.start();

		// Object of a class that has both produce() and consume() methods
		final PC pc = new PC();
		// Create producer thread
		Thread t1 = new Thread(pc::produce);
		// Create consumer thread
		Thread t2 = new Thread(pc::consume);

		// Start both threads
        // Infinite loop without shutdown
	/*	t1.start();
		t2.start();*/

        ExecutorService executor = Executors.newFixedThreadPool(2);
        executor.submit(pc::produce);
        executor.submit(pc::consume);

        // Graceful shutdown
        executor.shutdown();
        executor.awaitTermination(10, TimeUnit.SECONDS);
        executor.shutdownNow();

        EvenOldPrinter printer = new EvenOldPrinter();
        Thread oldNumberThread = new Thread(() -> printer.printOdd());
        Thread evenNumberThread = new Thread(() -> printer.printEven());

        oldNumberThread.start();
        evenNumberThread.start();


    }

	// This class has a list, producer (adds items to list and consumer (removes items).
	public static class PC {
		// Create a list shared by producer and consumer size of list is 2.
		LinkedList<Integer> list = new LinkedList<>();
		int capacity = 2;

		// Function called by producer thread
		public void produce() {
			int value = 0;
            try {
                while (true) {
                    synchronized (this) {
                        // producer thread waits while list is full
                        while (list.size() == capacity)
                            wait();

                        // to insert the jobs in the list
                        list.add(value++);
                        System.out.println("Producer produced-" + value);
                        // notifies the consumer thread that now it can start consuming
                        notify();
                        // makes the working of program easier to understand
                    }
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
		}

		// Function called by consumer thread
		public void consume() {
			try {
                while (true) {
                    synchronized (this) {
                        // consumer thread waits while list is empty
                        while (list.isEmpty())
                            wait();

                        // to retrieve the first job in the list
                        int val = list.removeFirst();
                        System.out.println("Consumer consumed-" + val);
                        // Wake up producer thread
                        notify();
                        // and sleep
                        Thread.sleep(1000);
                    }
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
	}
}
// Problem: Given two threads, print the numbers from 1 to 10.
// A thread will print the odd numbers,
// the other thread will print the even numbers.
// Output: 1,2,3,4,5,6,7,8,9,10

class EvenOldPrinter {
    private int number = 1;
    private final int MAX = 10;

    public synchronized void printOdd() {
        while (number <= MAX) {
            if (number % 2 != 0) {
                System.out.print(number + " ");
                number++;
                notify();
            } else {
                try {
                    wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }

        }
        notifyAll(); // important to release any waiting thread
    }

    public synchronized void printEven() {
        while (number <= MAX) {
            if (number % 2 == 0) {
                System.out.print(number + " ");
                number++;
                notify();
            } else {
                try {
                    wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
        notifyAll(); // important to release any waiting thread
    }
}
