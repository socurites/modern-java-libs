package com.socurites.modern.concurrent.basic;

public class NaiveRunnableThreadEx {
	public static void main(String[] args) throws InterruptedException {
		int x = 1337;
		Result result = new Result();
		
		Thread t1 = new Thread(() -> {
			System.out.println("t1 started");
			result.left = f(x);
			System.out.println("t1 ended");
		});
		
		Thread t2 = new Thread(() -> {
			System.out.println("t2 started");
			result.right = g(x);
			System.out.println("t2 ended");
		});
		
		t1.start(); t2.start();
		t1.join(); t2.join();
		
		int finalResult = result.left + result.right;
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
	
	private static class Result {
		private int left;
		private int right;
	}
}
