package com.socurites.modern.stream.parrallel;

import java.util.stream.LongStream;
import java.util.stream.Stream;

public class SummingIntEx {
	// 과거 방식
	public void summing1(long n) {
		long result = 0;
		for(long i = 1L; i <= n; i++) {
			result += i;
		}
	}
	
	// single stream
	public void summing2(long n) {
		long result = Stream.iterate(1L, i -> i + 1)
			.limit(n)
			.reduce(0L, Long::sum);
	}
	
	// parrell stream
	public void summing3(long n) {
		// 잘못된 예시
		long result = Stream.iterate(1L, i -> i + 1)	// <- reducing 단계에 병렬처리되도록 청크 분할 불가능 
			.limit(n)
			.parallel()		// <- parallel stream
			.reduce(0L, Long::sum);
		
		long result2 = LongStream.rangeClosed(1, n)		// <- 기본형을 사용. boxing, unboxing 미발생, 청크분할 가능 
			.parallel()
			.reduce(0L, Long::sum);
	}
}
