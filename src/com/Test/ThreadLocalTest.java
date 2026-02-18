package com.Test;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class ThreadLocalTest {

	public static void main(String args[]) throws IOException {
		Thread t1 = new Thread(new Task());
		Thread t2 = new Thread(new Task());
		t1.start();
		t2.start();
		
		ThreadLocalExample obj = new ThreadLocalExample();
        for(int i=0 ; i<5; i++){
            Thread t = new Thread(obj, ""+i);
            try {
				Thread.sleep(new Random().nextInt(1000));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            t.start();
        }
        

	}

	/*
	 * Thread safe format method because every thread will use its own DateFormat
	 */
	public static String threadSafeFormat(Date date) {
		DateFormat formatter = PerThreadFormatter.getDateFormatter();
		return formatter.format(date);
	}
}

class ThreadLocalExample implements Runnable{

    // SimpleDateFormat is not thread-safe, so give one to each thread
   /* private static final ThreadLocal<SimpleDateFormat> formatter = new ThreadLocal<SimpleDateFormat>(){
        @Override
        protected SimpleDateFormat initialValue()
        {
            return new SimpleDateFormat("yyyyMMdd HHmm");
        }
    };*/

    private static final ThreadLocal<SimpleDateFormat> formatter= ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy/MM/dd HH:mm"));
    
    @Override
    public void run() {
        System.out.println("Thread Name= "+Thread.currentThread().getName()+" default Formatter = "+formatter.get().toPattern()
                + " Initial Format : "+  formatter.get().format(new Date()));
        try {
            Thread.sleep(new Random().nextInt(1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //formatter pattern is changed here by thread, but it won't reflect to other threads
        formatter.set(new SimpleDateFormat("dd-MM-yyyy HH:mm"));
        
        System.out.println("Thread Name= "+Thread.currentThread().getName()+" formatter = "+formatter.get().toPattern()
             + " New Format : "+  formatter.get().format(new Date())
        );
    }

}


/*
 * Thread Safe implementation of SimpleDateFormat Each Thread will get its own
 * instance of SimpleDateFormat which will not be shared between other threads.
 * *
 */
class PerThreadFormatter {
	private static final ThreadLocal<SimpleDateFormat> dateFormatHolder = new ThreadLocal<SimpleDateFormat>() {
		@Override
		protected SimpleDateFormat initialValue() {
			System.out.println("Creating SimpleDateFormat for Thread : " + Thread.currentThread().getName());
			return new SimpleDateFormat("dd/MM/yyyy");
		}
	};

	/*
	 * Every time there is a call for DateFormat, ThreadLocal will return calling
	 * Thread's copy of SimpleDateFormat
	 */
	public static DateFormat getDateFormatter() {
		return dateFormatHolder.get();
	}
}

class Task implements Runnable {

	@Override
	public void run() {
		for (int i = 0; i < 2; i++) {
			System.out.println("Thread: " + Thread.currentThread().getName() + " Formatted Date: "
					+ ThreadLocalTest.threadSafeFormat(new Date()));
		}
	}
}
