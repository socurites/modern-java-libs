package com.socurites.modern.concurrent.future.exchange;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.stream.Collectors;

import com.socurites.modern.concurrent.future.exchange.ExchangeService.Money;
import com.socurites.modern.concurrent.future.shop.Shop;

//Rest Call for multiple shops
//API #1. Shop::getPrice
//AP #2. ExchangeService::getRate
//
//main -> API#1    
// 				| -> thenCombine
//     -> API#2
public class PortalAsyncCombine {
	private static List<Shop> shops = Arrays.asList(new Shop("BestPrice"),
			new Shop("LetsSaveBig"),
			new Shop("MyFavoriteShop"),
			new Shop("BuyItAll"));
	
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
	
	public static void main(String[] args) {
		String product = "myPhone27s";
		
		long start = System.nanoTime();
		System.out.println(findPricesAsync(product));
		long duration = (System.nanoTime() - start) / 1_000_000;
		System.out.println(String.format("[Original] Done in %d msec", duration));
	}
	
	// Naive stream
	// call
	// call
	// merge
//	public static List<String> findPrices(String product) {
//		shops.stream()
//			.map(shop -> shop.getPrice(product))
//			;
//		
//		return null;
//	}
	
	public static List<Double> findPricesAsync(String product) {
		List<CompletableFuture<Double>> priceFutres = shops.stream()
			.map(shop -> CompletableFuture.supplyAsync(
					() -> shop.getPrice(product))
					.thenCombineAsync(CompletableFuture.supplyAsync(
							() -> ExchangeService.getRate(Money.EUR, Money.USD)), 
							(price, rate) -> price * rate))
			.collect(Collectors.toList());
		
		
		return priceFutres.stream()
			.map(CompletableFuture::join)
			.collect(Collectors.toList());
	}
}
