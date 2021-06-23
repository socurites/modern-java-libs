package com.socurites.modern.concurrent.future.shop;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

import lombok.AllArgsConstructor;

import lombok.Data;

@Data
@AllArgsConstructor
public class Shop {
	private String name;
	
	//상품 가격 API
	// Blocking API
	public double getPrice(String product) {
		return calculatePrice(product);
	}
	
	
	// 상품 가격 API
	// Nonblocking API
	public Future<Double> getPriceAsync(String product) {
		CompletableFuture<Double> futurePrice = new CompletableFuture<>();
		new Thread(() -> {
			double price = calculatePrice(product);
			futurePrice.complete(price);
		}).start();
		
		return futurePrice;
	}
	
	// 상품 가격 API
	// Nonblocking API
	// + Exception handling
	public Future<Double> getPriceAsync2(String product) {
		CompletableFuture<Double> futurePrice = new CompletableFuture<>();
		new Thread(() -> {
			try {
				double price = calculatePrice(product);
				futurePrice.complete(price);
			} catch (Exception e) {
				futurePrice.completeExceptionally(e);
			}
		}).start();
		
		return futurePrice;
	}
	
	// CompletableFuture.supplyAsync(Supplier)
	// 상품 가격 API
	// Nonblocking API
	// + Exception handling
	// getPriceAsync2()와 동일
	public Future<Double> getPriceAsync3(String product) {
		return CompletableFuture.supplyAsync(() -> calculatePrice(product));
	}
	
	private double calculatePrice(String product) {
		longTimeProcess();
		
		Random random = new Random(System.currentTimeMillis());
		return random.nextDouble() * product.charAt(0) + product.charAt(1);
	}
	
	// some massive process
	// maybe remote call included
	public static void longTimeProcess() {
		try {
			Thread.sleep(1000L);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}
}
