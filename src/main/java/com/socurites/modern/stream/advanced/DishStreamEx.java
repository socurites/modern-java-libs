package com.socurites.modern.stream.advanced;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.socurites.modern.stream.model.Dish;

public class DishStreamEx {
	// 필터링
	public void filter() {
		List<Dish> menu = Dish.menu;
		
		// 필터링
		// 채식메뉴만 필터링
		List<Dish> vegererianMenu = menu.stream()
				.filter(Dish::isVegetarian)
				.collect(Collectors.toList());
		
		
		// 중복제거 필터링
		// 짝수만 필터링
		// 중복은 제거
		List<Integer> numbers = Arrays.asList(1, 2, 1, 3, 3, 2, 4);
		numbers.stream()
				.filter((i) -> 0 == i % 2)
				.distinct()
				.forEach(System.out::println);
	}
	
	// 슬라이싱
	public void slice() {
		// takeWhile, takeDrop
		// - Predicate
		// - Java9 이상 지원
		// - 스트림이 정렬되어 있는 경우에만 유효
		
		
		// limit: 축소
		// - 스트림이 정렬되어 있는 경우에만 효과적
		// - 스트림이 정렬되어 있지 않은 경우, 결과도 정렬되지 않음
		List<Dish> sortedMenu = Dish.menu.stream()
				.sorted(Comparator.comparing(Dish::getCalories))
				.collect(Collectors.toList());
		
		List<Dish> dishes = sortedMenu.stream()
				.filter((dish) -> dish.getCalories() > 300)
				.limit(3)
				.collect(Collectors.toList());
		
		
		// skip: 건너뛰기
	}
	
	// 매핑
	public void mapping() {
		List<Dish> menu = Dish.menu;
		
		// 매핑
		// Ex: Object -> Field
		List<String> disheNames1 = menu.stream()
				.map((dish) -> dish.getName())
				.collect(Collectors.toList());
		
		List<String> disheNames2 = menu.stream()
				.map(Dish::getName)
				.collect(Collectors.toList());
		
		
		// 매핑
		// Ex: Item -> Length
		List<String> words = Arrays.asList("Modern", "java", "In", "Action");
		List<Integer> wordLengths = words.stream()
				.map(String::length)
				.collect(Collectors.toList());
		
		
		// 매핑
		// Ex: Object -> Field -> Length
		List<Integer> dishNameLengths = menu.stream()
				.map(Dish::getName)
				.map(String::length)
				.collect(Collectors.toList());
	}
	
	// 매핑 - 플랫 매핑
	// 예시: 고유 문자 목록 반환
	public void flatMapping() {
		List<String> words = Arrays.asList("Hellow", "World");
		
		// Bad #1
		List<String[]> badList1 = words.stream()
		.map(word -> word.split(""))
		.distinct()
		.collect(Collectors.toList());
		
		// Bad #2
		List<Stream<String>> badList2 = words.stream()
		.map(word -> word.split(""))
		.map(Arrays::stream)
		.distinct()
		.collect(Collectors.toList());
		
		// Good
		List<String> goodList = words.stream()
		.map(word -> word.split(""))
		.flatMap(Arrays::stream)
		.distinct()
		.collect(Collectors.toList());
		
		
		
	}
}
