package com.socurites.modern.stream.parrallel;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.LongStream;

public class ForkJoinSumCalculatorRunnerEx {
	public static void main(String[] args) {
		long[] numbers = LongStream.rangeClosed(1, 100000).toArray();
		
		ForkJoinTask<Long> task = new ForkJoinSumCalculator(numbers);
		Long result = new ForkJoinPool().invoke(task);
	}
}
