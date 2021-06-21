package com.socurites.modern.concurrent.basic;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ExecutorServiceThreadEx {
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		int x = 1337;
		
		ExecutorService executorService = Executors.newFixedThreadPool(2);
		
		Future<Integer> future1 = executorService.submit(() -> {
			System.out.println("t1 started");
			int left = f(x);
			System.out.println("t1 ended");
			
			return left;
		});
		
		Future<Integer> future2 = executorService.submit(() -> {
			System.out.println("t2 started");
			int right = g(x);
			System.out.println("t2 ended");
			
			return right;
		});
		
		int finalResult = future1.get() + future2.get();
		
		System.out.println("main ended: " + finalResult);
		
		executorService.shutdown();
	}

	private static int f(int x) {
		// some massvie calculation
		return x;
	}
	
	private static int g(int x) {
		// some massvie calculation
		return x;
	}
}
