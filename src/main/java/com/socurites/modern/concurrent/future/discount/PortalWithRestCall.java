package com.socurites.modern.concurrent.future.discount;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.stream.Collectors;

// Rest Call for multiple shops
// API #1. Shop::getPrice
// AP #2. Discount::applyDiscount
//
// main -> API#1 -> API#2
//               |
//          thenCompose
public class PortalWithRestCall {
	private static List<Shop> shops = Arrays.asList(new Shop("BestPrice"),
			new Shop("LetsSaveBig"),
			new Shop("MyFavoriteShop"),
			new Shop("BuyItAll"));
	
	public static void main(String[] args) {
		String product = "myPhone27s";
		
		long start = System.nanoTime();
		System.out.println(findPrices(product));
		long duration = (System.nanoTime() - start) / 1_000_000;
		System.out.println(String.format("[Original] Done in %d msec", duration));
		
		start = System.nanoTime();
		System.out.println(findPricesAsync(product));
		duration = (System.nanoTime() - start) / 1_000_000;
		System.out.println(String.format("[Parallel] Done in %d msec", duration));
	}
	
	
	// Naive Blocking stream
	public static List<String> findPrices(String product) {
		return shops.stream()
			.map(shop -> shop.getPrice(product))		// <- Rest Call #1 Blocked
			.map(Quote::parse)
			.map(Discount::applyDiscount)				// <- Rest Call #2 Blocked
			.collect(Collectors.toList());
	}
	
	// 개선: CompeletableFuture Call
	private static final int MAX_THREADS = 100;
	private static Executor getExecutor() {
		return Executors.newFixedThreadPool(Math.min(shops.size(), MAX_THREADS), 
				new ThreadFactory() {
					@Override
					public Thread newThread(Runnable r) {
						Thread t = new Thread(r);
						t.setDaemon(true);
						return t;
					}
				});
	}
	
	public static List<String> findPricesAsync(String product) {
		List<CompletableFuture<String>> priceFutures = shops.stream()
			.map(shop -> CompletableFuture.supplyAsync(
					() -> shop.getPrice(product), getExecutor()))
			.map(future -> future.thenApply(Quote::parse))
			.map(future -> future.thenCompose(quote -> 
					CompletableFuture.supplyAsync(
							() -> Discount.applyDiscount(quote))))
			.collect(Collectors.toList());
		
		
		return priceFutures.stream()
				.map(CompletableFuture::join)
				.collect(Collectors.toList());
	}
}
