package com.socurites.modern.concurrent.basic;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CompletableFutureApiEx {
	private static ExecutorService executorService = Executors.newFixedThreadPool(2);		// <- application의 thread pool
	
	// Not Good
//	public static void main(String[] args) throws InterruptedException, ExecutionException {
//		int x = 1337;
//		
//		CompletableFuture<Integer> future1 = new CompletableFuture<>();
//		executorService.submit(() -> future1.complete(f(x)));
//		int b = g(x);
//		
//		int finalResult = future1.get() + b;
//	
//		System.out.println("main ended: " + finalResult);
//	}
	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		int x = 1337;
		
		CompletableFuture<Integer> future1 = new CompletableFuture<>();
		CompletableFuture<Integer> future2 = new CompletableFuture<>();
		CompletableFuture<Integer> finalFuture = future1.thenCombine(future2, (y, z) -> y + z);
		
		executorService.submit(() -> future1.complete(f(x)));
		executorService.submit(() -> future2.complete(g(x)));
		
		System.out.println("main ended: " + finalFuture.get());	// <- non-blocking until 조합된 cf가 모두 완료되기 전까지
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
