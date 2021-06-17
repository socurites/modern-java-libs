package com.socurites.modern.ex.basic;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CallableLambdaEx {
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		CallableLambdaEx ex = new CallableLambdaEx();
		
//		ex.exampleBefore001();
		ex.exampleAfter001();
	}
	
	public void exampleBefore001() throws InterruptedException, ExecutionException {
		ExecutorService executorService = Executors.newCachedThreadPool();
		Future<String> future = executorService.submit(new Callable<String>() {
			@Override
			public String call() throws Exception {
				return Thread.currentThread().getName();
			}
		});
		
		String threadName = future.get();
		System.out.println(threadName);
	}
	
	public void exampleAfter001() throws InterruptedException, ExecutionException {
		ExecutorService executorService = Executors.newCachedThreadPool();
		Future<String> future = executorService.submit(() -> Thread.currentThread().getName());
		
		String threadName = future.get();
		System.out.println(threadName);
	}
}
