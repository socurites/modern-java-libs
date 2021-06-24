package com.socurites.modern.functional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class FunctionalSubsetEx {
	public static void main(String[] args) {
		List<Integer> list = Arrays.asList(1, 4, 9);
		
		List<List<Integer>> subsets = subsets(list);
		subsets.forEach(System.out::println);

	}

	// a: 1을 포함하지 않는 subset
	// b: 1을 포함하는 subset <-- a & 1
	public static List<List<Integer>> subsets(List<Integer> list) {
		if (list.isEmpty()) {
			List<List<Integer>> ans = new ArrayList<>();
			ans.add(Collections.emptyList());
			return ans;
		}

		Integer first = list.get(0);
		List<Integer> rest = list.subList(1, list.size());

		List<List<Integer>> subans = subsets(rest);
		List<List<Integer>> subans2 = insertAll(first, subans);
		
		return concat(subans, subans2);

	}

	public static List<List<Integer>> insertAll(Integer first, List<List<Integer>> lists) {
		List<List<Integer>> result = new ArrayList<>();
		for (List<Integer> l : lists) {
			List<Integer> copyList = new ArrayList<>();
			copyList.add(first);
			copyList.addAll(l);
			result.add(copyList);
		}
		return result;
	}

	public static List<List<Integer>> concat(List<List<Integer>> a, List<List<Integer>> b) {
		List<List<Integer>> r = new ArrayList<>(a);
		r.addAll(b);
		return r;
	}
}
