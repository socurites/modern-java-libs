package com.socurites.modern.concurrent.future;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class CompletableFutureBasicEx {
	// Future를 사용J
	// Before Java 8
	// Task A --
	//          |-- Task C
	// Task B -- 
	public void useFuture() {
		ExecutorService executorService = Executors.newCachedThreadPool();
		Future<Double> future = executorService.submit(new Callable<Double>() {
			@Override
			public Double call() throws Exception {
				// do some massive computation A
				return Double.valueOf(0.5);
			}
		});
		
		// do some massive computation B
		
		// do Task C
		try {
			Double result = future.get(1, TimeUnit.SECONDS);
			
			// do some task here using result
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		} catch (TimeoutException e) {
			e.printStackTrace();
		}
	}
}
