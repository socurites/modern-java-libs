package com.socurites.modern.stream.parrallel;

import java.util.concurrent.RecursiveTask;

public class ForkJoinSumCalculator extends RecursiveTask<Long> {

	/**
	 * if (태스크가 충분히 작거나 더 이상 분할할 수 없다면) {
	 * 		순차적으로 태스크 계산
	 * } else {
	 * 		태스크를 2 서브태스크로 분할
	 *		태스크가 다시 서브태스크로 분할되도록 이 메서드를 재귀적으로 호출 
	 *
	 *		모든 서버태스크의 연산이 완료될 때까지 기다림
	 *		각 서브태스크의 결과를 합침
	 * }
	 * 
	 * 
	 */
	@Override
	protected Long compute() {
		int length = (end - start);
		if (length <= THRESHOLD) {
			return computeSequentially();
		} 
		
		ForkJoinSumCalculator leftTask = new ForkJoinSumCalculator(numbers, start, start + length/2);
		leftTask.fork();
		
		ForkJoinSumCalculator rightTask = new ForkJoinSumCalculator(numbers, start + length/2, end);
		Long rightResult = rightTask.compute();
		Long leftResult = leftTask.join();
		
		return leftResult + rightResult;
	}
	
	private long computeSequentially() {
		long sum = 0L;
		
		for(int i = start; i < end; i++) {
			sum += numbers[i];
		}
		
		return sum;
	}
	
	private final long[] numbers;
	private final int start;
	private final int end;
	public static final long THRESHOLD = 10_000;	// <- 분할 불가능한 단위
	
	public ForkJoinSumCalculator(long[] numbers) {
		this(numbers, 0, numbers.length);
	}
	
	public ForkJoinSumCalculator(long[] numbers, int start, int end) {
		super();
		this.numbers = numbers;
		this.start = start;
		this.end = end;
	}
	
	

}
