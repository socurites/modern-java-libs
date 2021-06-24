package com.socurites.modern.concurrent.future.discount;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class Discount {
	public enum Code {
		NONE(0),
		SILVER(5),
		GOLD(10),
		PLATINUM(15),
		DIAMOND(20);
		
		private final int percentage;
		
		Code(int percentage) {
			this.percentage = percentage;
		}
	}
	
	public static String applyDiscount(Quote quote) {
		return String.format("%s price is %f", quote.getShopName(),
				Discount.apply(quote.getPrice(), quote.getDiscountCode()));
	}

	private static Object apply(double price, Code discountCode) {
		delay();
		return format(price * (100 - discountCode.percentage) / 100);
		
	}
	
	// Not thread-safe
	private static final DecimalFormat formatter = new DecimalFormat("#.##", new DecimalFormatSymbols(Locale.US));
	private static double format(double number) {
		synchronized (formatter) {
			return Double.valueOf(formatter.format(number));
		}
	}
	
	// Rest API Delay
	public static void delay() {
		try {
			Thread.sleep(1000L);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}
}
