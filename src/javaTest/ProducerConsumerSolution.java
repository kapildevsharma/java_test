package javaTest;

import java.util.concurrent.*;

public class ProducerConsumerSolution {

    public static void main(String[] args) throws InterruptedException {
        // Capacity = 1 (acts like a handoff buffer)
        BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(1);

        // Create Producer and Consumer threads
        Thread producer = new Thread(new Producer(queue));
        Thread consumer = new Thread(new Consumer(queue));

        producer.start();
        consumer.start();

        // use to executor service in real application  Simple concurrency --> BlockingQueue + Executor
        BlockingQueue<Integer> executorQueue = new ArrayBlockingQueue<>(2);
        ExecutorService executor = Executors.newFixedThreadPool(2);

        executor.submit(new Producer(executorQueue));
        executor.submit(new Consumer(executorQueue));

        executor.shutdown();
        boolean result  = executor.awaitTermination(10, TimeUnit.SECONDS);

        // CompletableFuture async logic for producer & consumer
        BlockingQueue<Integer> cfQueue = new ArrayBlockingQueue<>(1);
        CompletableFuture<Void> producerFuture =
                CompletableFuture.runAsync(new Producer(cfQueue));
        CompletableFuture<Void> consumerFuture = CompletableFuture.runAsync(new Consumer(cfQueue));
        CompletableFuture.allOf(producerFuture, consumerFuture).join();

        // CompletableFuture with ExecutorService for producer & consumer
        CompletableFuture<Void> producerFuture1 =
                CompletableFuture.runAsync(() ->produce(queue), executor);
        CompletableFuture<Void> consumerFuture1 = CompletableFuture.runAsync(() ->consume(queue), executor);

        CompletableFuture.allOf(producerFuture1, consumerFuture1).join();
        executor.shutdown();
        System.out.println("Finished");
    }

    // ---------------- PRODUCE ----------------
    static void produce(BlockingQueue<Integer> queue) {
        try {
            for (int i = 1; i <= 5; i++) {
                System.out.println("Producing: " + i);
                queue.put(i);
                Thread.sleep(500);
            }
            queue.put(-1); // Indicate end of production
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    // ---------------- CONSUME ----------------
    static void consume(BlockingQueue<Integer> queue) {
        try {
            while (true) {
                int value = queue.take();
                if (value == -1) break;
                System.out.println("Consuming: " + value);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    static class Producer implements Runnable {
        private final BlockingQueue<Integer> queue;

        public Producer(BlockingQueue<Integer> queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            try {
                for (int i = 0; i < 10; i++) {
                    System.out.println("Producing: " + i);
                    queue.put(i); // Blocks if the queue is full
                    Thread.sleep(100); // Simulate time taken to produce
                }
                queue.put(-1); // Indicate end of production
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    static class Consumer implements Runnable {
        private final BlockingQueue<Integer> queue;

        public Consumer(BlockingQueue<Integer> queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            try {
                while (true) {
                    Integer value = queue.take(); // Blocks if the queue is empty
                    if (value == -1) break; // End of production signal
                    System.out.println("Consuming: " + value);
                    Thread.sleep(150); // Simulate time taken to consume
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}