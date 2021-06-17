package com.socurites.modern.ex.basic;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

public class ComparatorAppleSortEx {
	public static void main(String[] args) {
		ComparatorAppleSortEx ex = new ComparatorAppleSortEx();
		
		List<Apple> inventory = Arrays.asList(
	        new Apple(80, "green"),
	        new Apple(155, "green"),
	        new Apple(120, "red")
	    );
	}
	
	public void exampleBefore001(List<Apple> inventory) {
		inventory.sort(new Comparator<Apple>() {
			@Override
			public int compare(Apple o1, Apple o2) {
				return o1.weight - o2.weight;
			}
			
		});
	}
	
	public void exampleLambda(List<Apple> inventory) {
		inventory.sort((Apple o1, Apple o2) -> o1.weight - o2.weight);
	}
	
	
	@Getter
	@Setter
	@AllArgsConstructor
	public static class Apple {
	    private int weight = 0;
	    private String color = "";
	}
}
