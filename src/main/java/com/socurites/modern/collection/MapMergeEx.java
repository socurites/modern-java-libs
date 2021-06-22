package com.socurites.modern.collection;

import java.util.Map;

public class MapMergeEx {
	public static void main(String[] args) {
		Map<String, String> family = Map.ofEntries(Map.entry("Teo", "Star Wars"),
				Map.entry("Cristina", "James Bond"));

		
		Map<String, String> friends = Map.ofEntries(Map.entry("Raphael", "Star Wars"),
				Map.entry("Cristina", "Matrix"));
		
		// Map1 + Map2
		// Old
		// 중복된 키가 덮어쓰짐
		family.putAll(friends);
		
		
		// New
		// 중복된 키에 대한 제어 가능
		friends.forEach((k, v) -> family.merge(k, v, (movie1, movie2) -> movie1 + "&" + movie2));
		
		
		// Counting 문제
		Map<String, Integer> mathScores = Map.ofEntries(Map.entry("Raphael", 3),
				Map.entry("Cristina", 1),
				Map.entry("Olivia", 12),
				Map.entry("Michael", 2));
		
		String name = "newPerson";
		mathScores.merge(name, 1, (key, value) -> value + 1);
		
		// OR
		mathScores.compute(name, (k, v) -> null == v ? 1 : v + 1);
	}
}
