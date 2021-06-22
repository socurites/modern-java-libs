package com.socurites.modern.collection;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

public class MapEx {
	public static void main(String[] args) {
		Map<String, Integer> mathScores = Map.ofEntries(Map.entry("Raphael", 3),
				Map.entry("Cristina", 1),
				Map.entry("Olivia", 12),
				Map.entry("Michael", 2));
		
		// forEach
		// BinConsumer<T, U> (T, U) -> void
		mathScores.forEach((k, v) -> System.out.println(String.format("%s: %s", k, v)));
		
		
		System.out.println("=[Entry.comparningByKey]======================================");
		
		// 정렬 #1: Collectors.toMap
		// Entry.comparningByKey
		Map<String, Integer> sortedByKeyMathScores = mathScores.entrySet().stream()
			.sorted(Entry.comparingByKey())
			.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (o, n) -> n, LinkedHashMap::new));
		
		sortedByKeyMathScores.forEach((k, v) -> System.out.println(String.format("%s: %s", k, v)));
		

		// 정렬 #2: forEachOrdered
		Map<String, Integer> sortedByKeyMathScores2 = new LinkedHashMap<>();
		mathScores.entrySet().stream()
			.sorted(Entry.comparingByKey())
			.forEachOrdered(entry -> sortedByKeyMathScores2.put(entry.getKey(), entry.getValue()));
		

		
		System.out.println("=[Entry.comparningByKey Reversed]======================================");
		
		Map<String, Integer> sortedReversedByKeyMathScores = mathScores.entrySet().stream()
			.sorted(Entry.comparingByKey(Comparator.reverseOrder()))
			.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (o, n) -> n, LinkedHashMap::new));
		
		sortedReversedByKeyMathScores.forEach((k, v) -> System.out.println(String.format("%s: %s", k, v)));
		
		
		
		System.out.println("=[Entry.comparingByValue]======================================");
		
		// Entry.comparingByValue
		Map<String, Integer> sortedByValueMathScores = mathScores.entrySet().stream()
			.sorted(Entry.comparingByValue())
			.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (o, n) -> n, LinkedHashMap::new));
		
		sortedByValueMathScores.forEach((k, v) -> System.out.println(String.format("%s: %s", k, v)));
		
		
		System.out.println("=[Entry.comparingByValue Reversed]======================================");
		
		// Entry.comparingByValue
		Map<String, Integer> sortedByValueReversedMathScores = mathScores.entrySet().stream()
			.sorted(Entry.comparingByValue(Comparator.reverseOrder()))
			.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (o, n) -> n, LinkedHashMap::new));
		
		sortedByValueReversedMathScores.forEach((k, v) -> System.out.println(String.format("%s: %s", k, v)));
		
	}
}
