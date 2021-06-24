package com.socurites.modern.functional;

import java.util.function.DoubleUnaryOperator;

// currying
// f(x, y) = (g(x))(y)
// (x, y)를 인수로 받는 함수 f -> (y)를 인수로 받는 함수 g(x)로 변환
public class CurryingEx {
	// 고차원 함수(higher-order function)
	// 1. 하나 이상의 함수를 인수로 받음
	// 2. 함수를 결과로 반환
	
	
	public static double f(double x, double y, double z) {
		return x * y + z;
	}
	
	public static DoubleUnaryOperator f2(double y, double z) {
		return (double x) -> x * y + z;
	}
}