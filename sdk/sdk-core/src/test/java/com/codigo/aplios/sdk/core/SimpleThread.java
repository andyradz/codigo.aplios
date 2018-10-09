package com.codigo.aplios.sdk.core;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

// http://winterbe.com/posts/2015/04/07/java8-concurrency-tutorial-thread-executor-examples/

public class SimpleThread {

	public static void main(final String[] args) {

		// simpleThreads();
		// simpleExecutors();
		// simpleExecutorStop();
		// simpleCallable();
		// invokeAllCallable();
		SimpleThread.scheludeExecutor();
	}

	public static void scheludeExecutor() {

		final ScheduledExecutorService executor = Executors.newScheduledThreadPool(3);

		final Runnable task = () -> {
			// TimeUnit.SECONDS.sleep(1);
			System.out.println("Scheduling-" + Thread.currentThread()
					.getName() + " : " + System.nanoTime());
		};
		executor.scheduleWithFixedDelay(task, 0, 1, TimeUnit.SECONDS);
		executor.scheduleWithFixedDelay(task, 0, 5, TimeUnit.SECONDS);
		executor.scheduleWithFixedDelay(task, 0, 10, TimeUnit.SECONDS);
	}

	public static void invokeAllCallable() {

		final ExecutorService executor = Executors.newWorkStealingPool();

		final Callable<String> task = () -> {
			try {
				TimeUnit.SECONDS.sleep(60);
				return "123";
			}
			catch (final InterruptedException e) {
				throw new IllegalStateException(
					"task interrupted", e);
			}
		};

		// List<Callable<String>> tasks = Arrays.asList(() -> "Task1", () -> "Task2", () -> "Task3",
		// () -> "Task4", () -> "Task5", () -> "Task6");
		final List<Callable<String>> tasks = Arrays.asList(task, task, task);
		try {
			executor.invokeAll(tasks)
					.stream()
					.map(future -> {
						try {
							return future.get();
						}
						catch (final Exception e) {
							throw new IllegalStateException();
						}
					})
					.forEach(System.out::println);
		}
		catch (final InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (int idx = 0; idx < 1000; idx++)
			System.out.println(idx);

	}

	public static void simpleCallable() {

		final Callable<Integer> task = () -> {
			try {
				TimeUnit.SECONDS.sleep(6);
				return 123;
			}
			catch (final InterruptedException e) {
				throw new IllegalStateException(
					"task interrupted", e);
			}
		};

		final ExecutorService executor = Executors.newSingleThreadExecutor();
		final Future<Integer> future = executor.submit(task);

		System.out.println("future done? " + future.isDone());

		Integer result = 0;
		try {
			result = future.get(6, TimeUnit.SECONDS);
			executor.shutdownNow();
		}
		catch (final InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (final ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (final TimeoutException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("future done? " + future.isDone());
		System.out.println("Task is running...");

		System.out.print("result: " + result);
	}

	public static void simpleExecutorStop() {

		final Runnable task = () -> {
			final String threadName = Thread.currentThread()
					.getName();
			System.out.println("Thread name is: " + threadName);
			try {
				TimeUnit.MILLISECONDS.sleep(100L);
			}
			catch (final InterruptedException e) {
				e.printStackTrace();
			}
		};

		final ExecutorService executor = Executors.newSingleThreadExecutor();
		executor.submit(task);

		System.out.println("attempt to shutdown executor");
		executor.shutdown();
		try {
			executor.awaitTermination(5, TimeUnit.SECONDS);
		}
		catch (final InterruptedException e) {
			System.err.println("tasks interrupted");
		}
		finally {
			if (!executor.isTerminated()) {
				System.err.println("cancel non-finished tasks");
				executor.shutdownNow();
				System.out.println("shutdown finished");
			}
		}
	}

	public static void simpleExecutors() {

		final Runnable task = () -> {
			final String threadName = Thread.currentThread()
					.getName();
			System.out.println("Thread name is: " + threadName);
			try {
				TimeUnit.SECONDS.sleep(4);
			}
			catch (final InterruptedException e) {
				e.printStackTrace();
			}
		};

		final ExecutorService executor = Executors.newSingleThreadExecutor();
		executor.submit(task);
	}

	public static void simpleThreads() {

		final Runnable task = () -> {
			final String threadName = Thread.currentThread()
					.getName();
			System.out.println("Thread name is: " + threadName);
			try {
				TimeUnit.SECONDS.sleep(4);
			}
			catch (final InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		};
		// task.run();
		final Thread th1 = new Thread(
			task);
		th1.start();

		final Thread th2 = new Thread(
			task);
		th2.start();

		final Thread th3 = new Thread(
			task);
		th3.start();

		final Thread th4 = new Thread(
			task);
		th4.start();

		final Thread th5 = new Thread(
			task);
		th5.start();

		final Thread th6 = new Thread(
			task);
		th6.start();

		final Thread th7 = new Thread(
			task);
		th7.start();

		final Thread th8 = new Thread(
			task);
		th8.start();

		final Thread th9 = new Thread(
			task);
		th9.start();

		final Thread th10 = new Thread(
			task);
		th10.start();

		System.out.println("Done!");
	}
}
