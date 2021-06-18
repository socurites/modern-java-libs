package com.socurites.modern.stream.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.socurites.modern.stream.model.Dish;

/**
 * Dish 객체 리스트를 
 * 1. 필터링
 * 2. 정렬
 * 
 * 하는 방식에 대한 7버전과 8버전의 비교
 * 
 * @author socurites
 *
 */
public class FilterAndSortDishes {
	public static void main(String[] args) {
		
		
	}
	
	public void exampleBefore001() {
		List<Dish> menu = Dish.menu;
		
		List<Dish> lowCaloricDishes = new ArrayList<>();		// <-- 컨테이너 역할만 하는 중간 변수
		
		// Filtering by condition
		for(Dish dish : menu) {
			if(dish.getCalories() < 400) {
				lowCaloricDishes.add(dish);
			}
		}
		
		// Sorting
		Collections.sort(lowCaloricDishes, new Comparator<Dish>() {
			@Override
			public int compare(Dish o1, Dish o2) {
				return Integer.compare(o1.getCalories(), o2.getCalories());
			}
			
		});
		
		// Mapping A -> B
		List<String> lowCaloricDishesName = new ArrayList<>();	// <-- 최종 결과
		for(Dish dish : lowCaloricDishes) {
			lowCaloricDishesName.add(dish.getName());
		}
	}
	
	
	public void exampleAfter001() {
		List<Dish> menu = Dish.menu;
		
		List<String> lowCaloricDishesName = menu.stream()
				.filter(d -> d.getCalories() < 400)
				.sorted(Comparator.comparing(Dish::getCalories))
				.map(Dish::getName)
				.collect(Collectors.toList());
	}
	
	public void exampleAfter002() {
		List<Dish> menu = Dish.menu;
		
		List<String> lowCaloricDishesName = menu.parallelStream()	// <-- 병렬
				.filter(d -> d.getCalories() < 400)
				.sorted(Comparator.comparing(Dish::getCalories))
				.map(Dish::getName)
				.collect(Collectors.toList());
	}
}
