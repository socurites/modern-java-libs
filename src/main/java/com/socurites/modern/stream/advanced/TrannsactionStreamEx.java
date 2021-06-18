package com.socurites.modern.stream.advanced;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.socurites.modern.stream.model.Trader;
import com.socurites.modern.stream.model.Transaction;import ch.qos.logback.core.net.SyslogOutputStream;

public class TrannsactionStreamEx {
	public static void main(String[] args) {
		Trader raoul = new Trader("Raoul", "Cambridge");
	    Trader mario = new Trader("Mario", "Milan");
	    Trader alan = new Trader("Alan", "Cambridge");
	    Trader brian = new Trader("Brian", "Cambridge");

	    List<Transaction> transactions = Arrays.asList(
	        new Transaction(brian, 2011, 300),
	        new Transaction(raoul, 2012, 1000),
	        new Transaction(raoul, 2011, 400),
	        new Transaction(mario, 2012, 710),
	        new Transaction(mario, 2012, 700),
	        new Transaction(alan, 2012, 950)
	    );
	    
	    
	    // Q1
	    // 2011년에 일어난 모든 트랜잭션을 찾아
	    // 값을 오름차순 정렬
	    List<Transaction> a1 = transactions.stream()
	    	.filter(t -> 2021 == t.getYear())
	    	.sorted(Comparator.comparing(Transaction::getValue))
	    	.collect(Collectors.toList());
	    
	    
	    // Q2
	    // 거래자가 근무하는 모든 도시를
	    // 중복없이 나열
	    List<String> a2 = transactions.stream()
	    	.map(Transaction::getTrader)
	    	.map(Trader::getCity)
	    	.distinct()
	    	.collect(Collectors.toList());
	    
	    List<String> a2_2 = transactions.stream()
	    	.map(t -> t.getTrader().getCity())
	    	.distinct()
	    	.collect(Collectors.toList());
	    
	    
	    // Q3
	    // 케임브리지(Cambridge)에서 근무하는 모든 거래자를 찾아서
	    // 이름순으로 정렬
	    List<String> a3 = transactions.stream()
	    	.filter(t -> "Cambridge".equals(t.getTrader().getCity()))
	    	.map(t -> t.getTrader().getName())
	    	.distinct()
	    	.sorted()
	    	.collect(Collectors.toList());
	    
	    
	    List<Trader> a3_2 = transactions.stream()
	    	.map(Transaction::getTrader)
	    	.filter(t -> "Cambridge".equals(t.getCity()))
	    	.distinct()
	    	.sorted(Comparator.comparing(Trader::getName))
	    	.collect(Collectors.toList());
	    
	    
	    // Q4
	    // 모든 거래자의 이름을 알파벳순으로 정렬해서 반환
	    String a4 = transactions.stream()
	    	.map(t -> t.getTrader().getName())
	    	.distinct()
	    	.sorted()
	    	.reduce("", (n1, n2) -> n1 + n2);	// <-- 매번 문자열을 새로 생성
	    
	    
	    String a4_2 = transactions.stream()
	    	.map(t -> t.getTrader().getName())
	    	.distinct()
	    	.sorted()
	    	.collect(Collectors.joining());		// <-- 내부적으로 StringBuilder 사용
	    
	    
	    // Q5
	    // 밀라노(Milan)에 거래자가 있는가?
	    boolean a5 = transactions.stream()
	    	.map(Transaction::getTrader)
	    	.anyMatch(t -> "Milan".equals(t.getCity()));
	    
	    
	    // Q6
	    // 케임브리지(Cambridge)에 거주하는 거래자의 모든 트랜잭션 값을 출력
	    transactions.stream()
	    	.filter(t -> "Cambridge".equals(t.getTrader().getCity()))
	    	.map(Transaction::getValue)
	    	.forEach(System.out::println);
	    
	    
	    // Q7
	    // 전체 트랜잭션 중 최댓값
	    Optional<Integer> a7 = transactions.stream()
	    	.map(Transaction::getValue)
	    	.reduce(Integer::max);
	    
	    
	    // Q8
	    // 전체 트랜잭션 중 최소값
	    Optional<Integer> a8 = transactions.stream()
		    	.map(Transaction::getValue)
		    	.reduce(Integer::min);
	}
}
