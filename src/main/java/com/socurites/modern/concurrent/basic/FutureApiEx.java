package com.socurites.modern.concurrent.basic;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FutureApiEx {
	private static ExecutorService executorService = Executors.newFixedThreadPool(2);		// <- application의 thread pool
	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		int x = 1337;
		
		Future<Integer> future1 = f(x);
		Future<Integer> future2 = g(x);
		
		int finalResult = future1.get() + future2.get();
		
		System.out.println("main ended: " + finalResult);
	}

	// Future API
	private static Future<Integer> f(int x) {
		Future<Integer> future1 = executorService.submit(() -> {
			System.out.println("t1 started");
			// some massive calcucation
			System.out.println("t1 ended");
			
			return x;
		});
		
		return future1;
	}
	
	// Future API
	private static Future<Integer> g(int x) {
		Future<Integer> future1 = executorService.submit(() -> {
			System.out.println("t2 started");
			// some massive calcucation
			System.out.println("t2 ended");
			
			return x;
		});
		
		return future1;
	}
}
