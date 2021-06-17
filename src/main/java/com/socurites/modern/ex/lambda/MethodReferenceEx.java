package com.socurites.modern.ex.lambda;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class MethodReferenceEx {
	public static void main(String[] args) {
		// static method 참조
		// (args) -> ClassName.staticMethod(args)
		// ClassName::staticMethod
		List<String> list = Arrays.asList("1", "2", "3", "4", "5");
		
		list.stream().map((String s) -> Integer.parseInt(s));
		list.stream().map(Integer::parseInt);
		
		
		// 인스턴스 method 참조
		// (args) -> args.instanceMethod()
		// ClassName::instanceMethod
		List<String> strList = Arrays.asList("1", "2", "3", "4", "5");
		strList.stream().map((s) -> s.toUpperCase());
		strList.stream().map(String::toUpperCase);
		
		
		// 인스턴스 method 참조
		// (args, res) -> args.instanceMethod(res)
		// ClassName::instanceMethod
		
		
		// 기존 객체의 인스턴스 Method 참조
		// (args) -> expr.instanceMethod(args)
		// expr::instanceMethod
		
		
		// 생성자 참조
		Supplier<Apple> c1 = () -> new Apple();
		Supplier<Apple> c2 = Apple::new;
	}
	
	public void examples() {
		List<String> list = Arrays.asList("a", "B", "c", "D");
		list.sort((s1, s2) -> s1.compareToIgnoreCase(s2));
		list.sort(String::compareToIgnoreCase);
	}
	
	@Getter
	@Setter
	@AllArgsConstructor
	@NoArgsConstructor
	public static class Apple {
	    private int weight = 0;
	    private String color = "";
	}
}
