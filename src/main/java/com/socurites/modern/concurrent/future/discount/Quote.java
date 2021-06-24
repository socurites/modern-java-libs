package com.socurites.modern.concurrent.future.discount;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Quote {
	private final String shopName;
	private final double price;
	private final Discount.Code discountCode;
	
	public static Quote parse(String s) {
		String[] split = s.split(":");
		
		return new Quote(split[0],
				Double.parseDouble(split[1]),
				Discount.Code.valueOf(split[2]));
	}
}
