package com.socurites.modern.stream.collect;

import java.util.Comparator;
import java.util.HashSet;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import com.socurites.modern.stream.model.Dish;
import com.socurites.modern.stream.model.Dish.Type;

public class CollectorsEx {
	/**
	 * Collectors
	 * 	|- 구조변환
	 * 	|- 리듀싱 & 요약
	 * 	|- 그루핑
	 * 		|- 분할
	 */
	
	
	/**
	 * 리듀싱
	 */
	public void reducing( ) {
		List<Dish> menu = Dish.menu;
		
		// counting: 메뉴의 갯수
		long howManyDishes = menu.stream()
			.collect(Collectors.counting());
		
		long howManyDishes2 = menu.stream()
				.count();
		
		
		// 최대 최소
		// 최대 칼로리 메뉴, 최소 칼로리 메뉴
		Optional<Dish> maxDish = menu.stream()
			.collect(Collectors.maxBy(Comparator.comparingInt(Dish::getCalories)));
		
		Optional<Dish> minDish = menu.stream()
			.collect(Collectors.minBy(Comparator.comparing(Dish::getCalories)));
		
		
		// 요약
		// 칼로리 합계
		Integer totalCalories = menu.stream()
			.collect(Collectors.summingInt(Dish::getCalories));
		
		// 칼로리 평균
		Double averageCalories = menu.stream()
			.collect(Collectors.averagingInt(Dish::getCalories));
		
		// 칼로리 통계: SummaryStatistics 
		//	|- count
		//	|- sum
		//	|- min
		//	|- max
		//	|- average
		IntSummaryStatistics menuStatistics = menu.stream()
			.collect(Collectors.summarizingInt(Dish::getCalories));
		
		
		// 문자열 연결
		String joinedMenu = menu.stream()
			.map(Dish::getName)
			.collect(Collectors.joining(", "));
	}
	
	/**
	 * 범용 리듀싱
	 */
	public void reducing2() {
		List<Dish> menu = Dish.menu;
		
		// 최대 칼로리
		// 전용 리듀싱
		Optional<Dish> maxDish = menu.stream()
				.collect(Collectors.maxBy(Comparator.comparingInt(Dish::getCalories)));
		
		// 범용 리듀싱
		Integer maxDish2 = menu.stream()
			.collect(Collectors.reducing(0, Dish::getCalories, (i, j) -> i + j));
		
		// 범용 리듀싱 2
		Integer maxDish3 = menu.stream()
				.collect(Collectors.reducing(0, Dish::getCalories, Integer::sum));
		
	}
	
	
	/**
	 * 그루핑
	 */
	public void grouping() {
		List<Dish> menu = Dish.menu;
		
		// 메뉴별 그루핑
		Map<Type, List<Dish>> dishesByType = menu.stream()
			.collect(Collectors.groupingBy(Dish::getType));
		
		// 그루핑 Key가 메서드 참조가 아닌 경우
		menu.stream()
			.collect(Collectors.groupingBy(dish -> {
				if (dish.getCalories() <= 400) return CaloricLevel.DIET; 
				else if (dish.getCalories() <= 700) return CaloricLevel.NORMAL;
				else return CaloricLevel.FAT;
			}));
	}
	
	public enum CaloricLevel { DIET, NORMAL, FAT }
	
	/**
	 * 그루핑 고급
	 */
	public void grouping2() {
		List<Dish> menu = Dish.menu;
		
		// 메뉴별 그루핑
		// - 조건: 칼로리가 500 초과
		
		// 잘못된(?) 예
		// 필터링이 먼저 적요되므로
		// 필터링에 특정 타입이 모두 걸러지면
		// 해당 타입의 Key는 그룹핑 결과에서 사라진다
		Map<Type, List<Dish>> dishesByType = menu.stream()
			.filter(dish -> dish.getCalories() > 500)			// <-- 필터링 선행 처리
			.collect(Collectors.groupingBy(Dish::getType));
		
		
		// 비어 있는 value를 가지더라도 Key를 살아남게 하려면
		// Collectors.grouping <- Java 9
		
		
		
		// 메뉴별 그루핑
		// - Dish가 아닌 요리명만 모으기
		// mapping
		Map<Type, List<String>> dishNamesByType = menu.stream()
			.collect(Collectors.groupingBy(Dish::getType, Collectors.mapping(Dish::getName, Collectors.toList())));
	}
	
	/**
	 * n계층 그루핑
	 */
	public void grouping3() {
		List<Dish> menu = Dish.menu;
		
		// Level1: Dish.Type
		// 	|- Level2: CaloricLevel
		Map<Type, Map<CaloricLevel, List<Dish>>> dishesByTypeCaloricLevel = menu.stream()
			.collect(Collectors.groupingBy(Dish::getType,
					Collectors.groupingBy(dish -> {
						if (dish.getCalories() <= 400) return CaloricLevel.DIET; 
						else if (dish.getCalories() <= 700) return CaloricLevel.NORMAL;
						else return CaloricLevel.FAT;
					})));
	}
	
	/**
	 * 그루핑 고급 
	 */
	public void grouping4() {
		List<Dish> menu = Dish.menu;
		
		// groupingBy(f) == groupingBy(f, toList())
		
		// counting
		Map<Type, Long> typesCount = menu.stream()
			.collect(Collectors.groupingBy(Dish::getType, 
					Collectors.counting()));
		
		// summing
		Map<Type, Integer> totalCaloriesByType = menu.stream()
			.collect(Collectors.groupingBy(Dish::getType, 
					Collectors.summingInt(Dish::getCalories)));
		
		
		// 메뉴별 그루핑 하되
		// 메뉴별 칼로리가 가장 큰 메뉴만 모으기
		Map<Type, Optional<Dish>> mostCaloricByType = menu.stream()
			.collect(Collectors.groupingBy(Dish::getType, 
					Collectors.maxBy(Comparator.comparing(Dish::getCalories))));
		
		// collectingAndThen
		Map<Type, Dish> mostCaloricByType2 = menu.stream()
				.collect(Collectors.groupingBy(Dish::getType, 
						Collectors.collectingAndThen(Collectors.maxBy(Comparator.comparing(Dish::getCalories)), Optional::get)));
	}
	
	/**
	 * 그루핑 고급
	 */
	public void grouping5() {
		List<Dish> menu = Dish.menu;
		
		// 메뉴 타입별로 그루핑
		// 결과: Dish -> CaloricLevel (mapping)
		Map<Type, Set<CaloricLevel>> caloricLevelByType = menu.stream()
			.collect(Collectors.groupingBy(Dish::getType, 
					Collectors.mapping(dish -> {
						if (dish.getCalories() <= 400) return CaloricLevel.DIET; 
						else if (dish.getCalories() <= 700) return CaloricLevel.NORMAL;
						else return CaloricLevel.FAT;
					}, Collectors.toSet())));
		
		// 결과 구현체 지정
		Map<Type, Set<CaloricLevel>> caloricLevelByType2 = menu.stream()
				.collect(Collectors.groupingBy(Dish::getType, 
						Collectors.mapping(dish -> {
							if (dish.getCalories() <= 400) return CaloricLevel.DIET; 
							else if (dish.getCalories() <= 700) return CaloricLevel.NORMAL;
							else return CaloricLevel.FAT;
						}, Collectors.toCollection(HashSet::new))));
	}
}
