package com.socurites.modern.flow.temperature;

import java.util.Random;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TempInfo {
	private final String town;
	private final int temp;
	
	public static final Random random = new Random();
	
	
	// Mocing Factory Method
	// town의 현재 온도 랜덤 생성
	public static TempInfo fetch(String town) {
		if (random.nextInt(10) == 0) {
			throw new RuntimeException(String.format("Error! while getting temperature from %s", town));
		} else {
			return new TempInfo(town, random.nextInt(100));
		}
	}
}
