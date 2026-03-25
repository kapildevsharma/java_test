package com.Test;

//Java program to demonstrate the Implementation of getActiveThreadCount()

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.RecursiveTask;

class NewTask extends RecursiveAction {
	private static final long serialVersionUID = 1L;
	private long Load = 0;

	public NewTask(long Load) {
		this.Load = Load;
	}

    @Override
	protected void compute() {
		// fork tasks into smaller subtasks
		List<NewTask> subtasks = new ArrayList<NewTask>();
		subtasks.addAll(createSubtasks());

		for (RecursiveAction subtask : subtasks) {
			subtask.fork();
		}
	}

	// function to create and add subtasks
	private List<NewTask> createSubtasks() {
		// create subtasks
		List<NewTask> subtasks = new ArrayList<NewTask>();
		NewTask subtask1 = new NewTask(this.Load / 2);
		NewTask subtask2 = new NewTask(this.Load / 2);
		NewTask subtask3 = new NewTask(this.Load / 2);

		// to add the subtasks
		subtasks.add(subtask1);
		subtasks.add(subtask2);
		subtasks.add(subtask3);

		return subtasks;
	}
}
class Fibonacci extends RecursiveTask<Integer> {
    final int n;
    Fibonacci(int n) { this.n = n; }
    protected Integer compute() {
        if (n <= 1)
            return n;
        Fibonacci f1 = new Fibonacci(n - 1);
        f1.fork(); // async execution
        Fibonacci f2 = new Fibonacci(n - 2);
        return f2.compute() + f1.join();  // compute one directly, join the other
    }
}

public class JavaForkJoingetActivethreadcountExample1 {
	public static void main(final String[] arguments) throws InterruptedException {
		// get no. of available core available
		int proc = Runtime.getRuntime().availableProcessors();
		System.out.println("Number of available core in the processor is: " + proc);

		// get no. of threads active
		ForkJoinPool pool = ForkJoinPool.commonPool();
		System.out.println("Number of active thread before invoking: " + pool.getActiveThreadCount());

		pool.invoke( new NewTask(400));
		System.out.println("Number of active thread after invoking: " + pool.getActiveThreadCount());
		System.out.println("Common Pool Size is: " + pool.getPoolSize());

        long sum = pool.invoke(new Fibonacci(10));
        System.out.println("Sum = " + sum);
        System.out.println("Number of active thread after invoking: " + pool.getActiveThreadCount());
        System.out.println("Common Pool Size is: " + pool.getPoolSize());
	}
}
