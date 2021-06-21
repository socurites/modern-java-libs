package com.socurites.modern.concurrent.basic;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class NaiveCallableThreadEx {
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		int x = 1337;
		
//		Thread t1 = new Thread(new FutureTask<>(new Callable<Integer>() {
//			@Override
//			public Integer call() throws Exception {
//				return null;
//			}
//		}));
		
//		Thread t1 = new Thread(new FutureTask<>(() -> {
//			System.out.println("t1 started");
//			int left = f(x);
//			System.out.println("t1 ended");
//			
//			return left;
//		}));
//		
//		Thread t2 = new Thread(new FutureTask<>(() -> {
//			System.out.println("t2 started");
//			int left = g(x);
//			System.out.println("t2 ended");
//			
//			return left;
//		}));
		
		FutureTask<Integer> futureTask1 = new FutureTask<>(() -> {
			System.out.println("t1 started");
			int left = f(x);
			System.out.println("t1 ended");
			
			return left;
		});
		
		FutureTask<Integer> futureTask2 = new FutureTask<>(() -> {
			System.out.println("t2 started");
			int right = g(x);
			System.out.println("t2 ended");
			
			return right;
		});
		
		Thread t1 = new Thread(futureTask1);
	
		Thread t2 = new Thread(futureTask2);
		
		
		t1.start(); t2.start();
		t1.join(); t2.join();
		
		int finalResult = futureTask1.get() + futureTask2.get();
		
		System.out.println("main ended: " + finalResult);
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
