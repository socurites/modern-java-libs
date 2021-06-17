package com.socurites.modern.ex.lambda;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.function.Supplier;

/**
 * Java 8에서 제공하는 함수형 인터페이스 종류
 * 
 * @author socurites
 *
 */
public class FunctionalInterfaceEx {
	// Comparator
	// int compare(T o1, T o2);
	public void comaprator() {
		List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
		
		list.sort((o1, o2) -> o2 - o1);
	}
	
	// Runnable
	// public abstract void run();
	public void runnable() {
		Thread t = new Thread(() -> System.out.println(Thread.currentThread().getName()));
	}
	
	// Callable
	// V call() throws Exception;
	public void callable() {
		ExecutorService executorService = Executors.newCachedThreadPool();
		Future<String> future = executorService.submit(() -> Thread.currentThread().getName());
	}
	
	// Predicate
	// boolean test(T t);
	public void predicate() {
		List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
		
		list.stream().filter((Integer i) -> i > 3);
	}
	
	// Consumer
	// void accept(T t);
	public void consumer() {
		List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
		
		list.forEach((Integer i) -> System.out.println(i));
		list.forEach((i) -> System.out.println(i));
	}
	
	// Function
	// R apply(T t);
	public void function() {
		List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
		
		list.stream().map((Integer i) -> i.toString());
	}
	
	// Supplier
	// T get();
	public void supplier() {
		Supplier<Integer> five = () -> new Integer(5);
	}
}
