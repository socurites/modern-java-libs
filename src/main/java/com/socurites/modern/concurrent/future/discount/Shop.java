package com.socurites.modern.concurrent.future.discount;

import java.util.Random;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Shop {
	private String name;
	
	//상품 가격 API
	// Blocking API
	public String getPrice(String product) {
		double price = calculatePrice(product);
		
		Random random = new Random(System.currentTimeMillis());
		Discount.Code code = Discount.Code.values()[
		                          random.nextInt(Discount.Code.values().length)];
		
		return String.format("%s:%.2f:%s", name, price, code);
				
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
