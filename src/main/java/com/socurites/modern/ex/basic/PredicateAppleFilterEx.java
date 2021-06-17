package com.socurites.modern.ex.basic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * 필터링 로직만 다른 경우
 * 
 * @author socurites
 *
 */
public class PredicateAppleFilterEx {
	public static void main(String[] args) {
		PredicateAppleFilterEx ex = new PredicateAppleFilterEx();
		
		List<Apple> inventory = Arrays.asList(
	        new Apple(80, "green"),
	        new Apple(155, "green"),
	        new Apple(120, "red")
	    );
	}
	
	/**
	 * Before
	 * 
	 * @param inventory
	 */
	public void filerBefore001(List<Apple> inventory) {
		List<Apple> filteredByColors = filterByColors(inventory);
		List<Apple> filteredByWeights = filterByWeights(inventory);
	}
	
	/**
	 * 필터 행위 1
	 * - 컬러 필터
	 * 
	 * @param inventory
	 * @param color
	 * @return
	 */
	private List<Apple> filterByColors(List<Apple> inventory) {
		List<Apple> result = new ArrayList<>();
	    for (Apple apple : inventory) {
			if ("green".equals(apple.getColor())) {	// <-- 필터 로직
				result.add(apple);
			}
	    }
	    
	    return result;
	}
	
	/**
	 * 필터 행위 2
	 * - 무게 필터
	 * 
	 * @param inventory
	 * @param weight
	 * @return
	 */
	private List<Apple> filterByWeights(List<Apple> inventory) {
		List<Apple> result = new ArrayList<>();
		for (Apple apple : inventory) {
			if (apple.getWeight() > 150) {	// <-- 필터 로직
				result.add(apple);
			}
		}
		
		return result;
	}
	
	
	/**
	 * After: Predicate
	 * 
	 * @param inventory
	 */
	public void filerAfter001(List<Apple> inventory) {
		List<Apple> filteredByColors = filterApples(inventory, PredicateAppleFilterEx::colorFilter);
		List<Apple> filteredByWeights = filterApples(inventory, PredicateAppleFilterEx::weightFilter);	
	}
	
	/**
	 * 필터 로직과 필터 적용(test) 로직의 분리
	 * 
	 * @param inventory
	 * @param p
	 * @return
	 */
	private List<Apple> filterApples(List<Apple> inventory, Predicate<Apple> p) {
		List<Apple> result = new ArrayList<>();
		for (Apple apple : inventory) {
			if (p.test(apple)) {
				result.add(apple);
			}
		}
		
		return result;
	}
	
	
	public static boolean colorFilter(Apple apple) {
		return "green".equals(apple.getColor());
	}
	
	public static boolean weightFilter(Apple apple) {
		return apple.getWeight() > 150;
	}
	
	
	/**
	 * After: 람다
	 * 
	 * @param inventory
	 */
	public void filerAfter002(List<Apple> inventory) {
		List<Apple> filteredByColors = filterApples(inventory, (a) -> "green".equals(a.getColor()));
		List<Apple> filteredByWeights = filterApples(inventory, (a) -> a.getWeight() > 150);	
	}
	
	
	/**
	 * After: 스트림
	 * 
	 * @param inventory
	 */
	public void filerAfter003(List<Apple> inventory) {
		List<Apple> filteredByColors = inventory.stream().filter((a) -> "green".equals(a.getColor())).collect(Collectors.toList());
		List<Apple> filteredByWeights = inventory.stream().filter((a) -> a.getWeight() > 150).collect(Collectors.toList());	
	}
	

	@Getter
	@Setter
	@AllArgsConstructor
	public static class Apple {
	    private int weight = 0;
	    private String color = "";
	}
}
