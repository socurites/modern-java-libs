package com.socurites.modern.flow.temperature;

import java.util.concurrent.Flow.Publisher;

public class Main {
	public static void main(String[] args) {
		String town = "New York";
				
		Publisher<TempInfo> tempPublisher = tempPublisher(town);
		tempPublisher.subscribe(new TempSubscriber());
	}
	
	// Publisher
	// Subscriber<T> -> void
	private static Publisher<TempInfo> tempPublisher(String town) {
		return (subscriber) -> subscriber.onSubscribe(
				new TempSubscription(subscriber, town)); 
	}
	
//	private static Publisher<TempInfo> tempPublisher2(String town) {
//		return new Publisher<TempInfo>() {
//			
//			@Override
//			public void subscribe(Subscriber<? super TempInfo> subscriber) {
//				subscriber.onSubscribe(
//						new TempSubscription(subscriber, town));
//			}
//		};
//	}
}
