package com.socurites.modern.concurrent.future.shop;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.stream.Collectors;

// 호출할 API가 Blocking이라면
public class AsyncPortal {
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
		System.out.println(findPrices2(product));
		duration = (System.nanoTime() - start) / 1_000_000;
		System.out.println(String.format("[Parallel] Done in %d msec", duration));
		
		start = System.nanoTime();
		System.out.println(findPrices2(product));
		duration = (System.nanoTime() - start) / 1_000_000;
		System.out.println(String.format("[CompletableFuture] Done in %d msec", duration));
		
	}

	// Blocking stream
	public static List<String> findPrices(String product) {
		return shops.stream()
			.map(shop -> String.format("%s price is %.2f", 
					shop.getName(), shop.getPrice(product)))
			.collect(Collectors.toList());
	}
	
	// 개선: parallel stream
	// 하드웨어 런타임 쓰레드 갯수에 최대 성능이 제약
	// 상점이 늘어나면 선형적으로 호출시간이 증가
	public static List<String> findPrices2(String product) {
		return shops.parallelStream()
			.map(shop -> String.format("%s price is %.2f", 
					shop.getName(), shop.getPrice(product)))
			.collect(Collectors.toList());
	}
	
	// 개선: CompletableFuture Call
	// Nonblocking call
	// 상점이 늘어나더라도
	// Executor 쓰레드 풀을 이용하여 성능 최적화 가능
	public static List<String> findPrices3(String product) {
		List<CompletableFuture<String>> priceFutures = shops.stream()
			.map(shop -> CompletableFuture.supplyAsync(
					() -> String.format("%s price is %.2f", 
							shop.getName(), shop.getPrice(product))))
			.collect(Collectors.toList());
		
		return priceFutures.stream()
				.map(CompletableFuture::join)
				.collect(Collectors.toList());
	}
	
	// 개선: CompletableFuture Call
	// Thread Pool 사용
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
	
	public static List<String> findPrices4(String product) {
		List<CompletableFuture<String>> priceFutures = shops.stream()
			.map(shop -> CompletableFuture.supplyAsync(
					() -> String.format("%s price is %.2f", 
							shop.getName(), shop.getPrice(product)), getExecutor()))
			.collect(Collectors.toList());
		
		return priceFutures.stream()
				.map(CompletableFuture::join)
				.collect(Collectors.toList());
	}
	
	
	// Conclusion: 어던 병렬화 기법을 사용할 것인가?
	// Stream: I/O가포함되지 않는 계산 중심의 동작을 실행하느 경우
	// CompletableFuture: I/O를 기다리는 작업을 병렬로 실행해야 할 때
}
