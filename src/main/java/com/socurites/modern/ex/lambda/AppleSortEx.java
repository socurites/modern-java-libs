package com.socurites.modern.ex.lambda;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

public class AppleSortEx {
	public static void main(String[] args) {
		List<Apple> inventory = Arrays.asList(
	        new Apple(80, "green"),
	        new Apple(155, "green"),
	        new Apple(120, "red")
	    );
		
		// 1: Comparator 직접 구현
		
		// 2: Anonymous class
		inventory.sort(new Comparator<Apple>() {
			@Override
			public int compare(Apple o1, Apple o2) {
				return o1.weight - o2.weight;
			}
			
		});
		
		// 3: lambda
		inventory.sort((o1, o2) -> o1.weight - o2.weight);
		
		// method reference
		// Comparator<Apple> c = Comparator.comparing((Apple a) -> a.getWeight());
		inventory.sort(Comparator.comparing((a) -> a.getWeight()));
		inventory.sort(Comparator.comparing(Apple::getWeight));
	}
	
	@Getter
	@Setter
	@AllArgsConstructor
	public static class Apple {
	    private int weight = 0;
	    private String color = "";
	    
	}
}
