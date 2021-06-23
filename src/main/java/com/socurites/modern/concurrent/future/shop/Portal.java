package com.socurites.modern.concurrent.future.shop;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

// 호출할 API가 Non blocking이라면
public class Portal {
	public static void main(String[] args) {
		Shop shop = new Shop("Best Shop");
		
		long start = System.nanoTime();
		Future<Double> futurePrice = shop.getPriceAsync("my favorite product");
		long invocationTime = (System.nanoTime() - start) / 1_000_000;
		System.out.println(String.format("Invocation returned after %d msecs", invocationTime));
		
		// getPriceAsync() 호출 기다리는 동안
		// do some massive things here
		
		// finally
		double price;
		try {
			price = futurePrice.get();
			System.out.println(String.format("Price is %.2f", price));
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		
		long retrievalTime = (System.nanoTime() - start) / 1_000_000;
		System.out.println(String.format("Price returned after %d msecs", retrievalTime));
	}
}
