package com.socurites.modern.stream.collect;

import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.context.index.CandidateComponentsIndex;

public class InsideCollectorEx {
	// 숫자를 소수와 비소수로 분할하기
	
	// 소수 or 비소수 판단 Predicate
	public static boolean isPrime(int candidate) {
		int candidateRoot = (int)Math.sqrt((double)candidate);
		
		return IntStream.rangeClosed(2, candidateRoot)
				.noneMatch(i -> 0 == candidate % i);
	}
	
	// 파티셔닝
	// 소수, 비소수
	public void partitionling() {
		int n = 1000;
		Map<Boolean, List<Integer>> numberMap = IntStream.rangeClosed(2, n).boxed()
			.collect(Collectors.partitioningBy(candidate -> isPrime(candidate)));
	}
	
	
	
}
