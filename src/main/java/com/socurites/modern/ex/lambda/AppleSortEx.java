package com.socurites.modern.ex.lambda;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

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
	
	// 람다 표현식의 조합
	// based on Default Method of interface
	// Comparator 조합
	public void composeComparator() {
		List<Apple> inventory = Arrays.asList(
	        new Apple(80, "green"),
	        new Apple(155, "green"),
	        new Apple(120, "red")
	    );
		
		// sorting
		inventory.sort(Comparator.comparing(Apple::getWeight));
		
		// reverse sorting
		inventory.sort(Comparator.comparing(Apple::getWeight).reversed());
		
		// sorting by composite values
		inventory.sort(Comparator.comparing(Apple::getWeight)
				.reversed()
				.thenComparing(Apple::getColor));
	}
	
	
	// Predicate 조합
	public void composePredicate() {
		// is red apple?
		Predicate<Apple> redApple = ((a) -> "red".equals(a.color));
		
		// negate
		Predicate<Apple> notRedApple = redApple.negate();
		
		// and
		Predicate<Apple> redAndHeavyApple = redApple.and((a) -> a.weight > 150);
		
		// or
		Predicate<Apple> redAndHeavyAppleOrGreen = (redApple.and((a) -> a.weight > 150))
				.or((a) -> "green".equals(a.color));
	}
	
	// Function 조합
	public void composeFunction() {
		// andThen
		// h(x) = g(f(x))
		Function<Integer, Integer> f = x -> x + 1;
		Function<Integer, Integer> g = x -> x * 2;
		Function<Integer, Integer> h = f.andThen(g);
		
		// compose
		// h(x) = f(g(x))
	}
	
	
	@Getter
	@Setter
	@AllArgsConstructor
	public static class Apple {
	    private int weight = 0;
	    private String color = "";
	    
	}
}
