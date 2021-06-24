package com.socurites.modern.concurrent.future.exchange;

import java.util.Random;

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
	
	private double calculatePrice(String product) {
		delay();
		
		Random random = new Random(System.currentTimeMillis());
		return random.nextDouble() * product.charAt(0) + product.charAt(1);
	}
	
	//Rest API Delay
	public static void delay() {
		try {
			Thread.sleep(1000L);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}
}
