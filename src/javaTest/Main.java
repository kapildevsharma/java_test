package javaTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
	
	public enum State {
        BLANK,   // 0
        RED,     // 1
        YELLOW   // 2
    }

    public static State getState(int x, int y) {
        y = 1;
        for (x = 5; x > 0; x--) {
            if (x == State.BLANK.ordinal() && y == State.BLANK.ordinal()) {
                return State.RED;
            }
            //return State.BLANK;
        }
        return State.BLANK;
    }

	public static void main(String[] args) {

        AtomicInteger x1 = new AtomicInteger();
        x1.set(10);
        Runnable r = () -> {
             x1.getAndIncrement(); // compile error
            System.out.println(x1.get());
        };

        r.run();

		ArrayList<String> latestConceptClasses = new ArrayList<>(Arrays.asList("gear-apparel-type", "" ));
		System.out.println("test "+ latestConceptClasses.contains(""));
		
		System.out.println(getState(1,1));
		
		List<String> str1 = new ArrayList<String>();
		str1.add("A");
		str1.add("B");
		str1.add("C");
		str1.add("D");
		System.out.println(str1);

		str1.removeIf(x -> x.equals("C"));  // remove element C
		System.out.println(str1);

        Calendar cal = Calendar.getInstance();
        cal.set(2024,Calendar.MARCH,10);
        System.out.println(cal);

        List<Integer> together = Stream.of(Arrays.asList(1, 2), Arrays.asList(3, 4)) // Stream of List<Integer>
	            .flatMap(List::stream)
	            .map(integer -> integer + 1)
	            .toList();

		together.forEach(System.out:: print);
		System.out.println("\n");

		PrintSequenceRunnable runnable1=new PrintSequenceRunnable(1); 
        PrintSequenceRunnable runnable2=new PrintSequenceRunnable(2);
        PrintSequenceRunnable runnable3=new PrintSequenceRunnable(0);
 
        Thread t1=new Thread(runnable1,"T1");
        Thread t2=new Thread(runnable2,"T2");
        Thread t3=new Thread(runnable3,"T3");
 
        t1.start();
        t2.start();
        t3.start();
        
        // start ThreadPoolExecutor program
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                2, // core pool size
                4, // maximum pool size
                60, // keep-alive time
                TimeUnit.SECONDS, // time unit
                new LinkedBlockingQueue<>(2) // work queue
        );
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());

        // CallerRunsPolicy : Task ko submit karne wale thread execute karta hai instead of pool. No exception thrown.
        // DiscardPolicy  : Task silently ignore kar diya jata hai, no exception.
        // DiscardOldestPolicy : Oldest task ko discard kar diya jata hai, new task submit ho jata hai. No exception.
        // AbortPolicy : Default hota hai. Task submit karne par RejectedExecutionException throw hota hai.

     // Submit tasks
        for (int i = 1; i <= 6; i++) {
            final int taskNumber = i;
            executor.submit(() -> {
                System.out.println("Task " + taskNumber + " executed by " + Thread.currentThread().getName());
            });
        }
        executor.shutdown();
        // end ThreadPoolExecutor program

        ExecutorService executors = Executors.newFixedThreadPool(4);
       // executors = Executors.newSingleThreadExecutor();  // Guaranteed sequential , Automatically replaces dead thread
        executors = Executors.newCachedThreadPool();
        CyclicBarrier cyclicBarrier = new CyclicBarrier(5);
        executors.submit(new Service1(cyclicBarrier));
        executors.submit(new Service1(cyclicBarrier));
        executors.submit(new Service2(cyclicBarrier));
        executors.submit(new Service2(cyclicBarrier));
        executors.submit(new Service2(cyclicBarrier));
        try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        System.out.println("Done");

        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(5);
        scheduledExecutorService.schedule(() -> {
            System.out.println("Hello World once after 5 ms");
        }, 5, TimeUnit.MILLISECONDS);

        try {
            CountDownLatch lock = new CountDownLatch(3);
            scheduledExecutorService = Executors.newScheduledThreadPool(2);

            ScheduledFuture<?> future = scheduledExecutorService.scheduleAtFixedRate(() -> {
                System.out.println("Hello World");
                lock.countDown();
            }, 10, 10, TimeUnit.MILLISECONDS);

         //   lock.await(10, TimeUnit.MILLISECONDS);
            lock.await();  // // wait until latch = 0
            future.cancel(true);
            scheduledExecutorService.shutdown();
            System.out.println("Finished");

        }catch (Exception e){
            e.printStackTrace();
        }

	}
}
