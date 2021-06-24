package com.socurites.modern.flow.temperature;

import java.util.concurrent.Flow.Publisher;

public class MainWithProcessor {
	public static void main(String[] args) {
		String town = "New York";
		
		Publisher<TempInfo> tempPublisher = tempCelsiusPublisher(town);
		tempPublisher.subscribe(new TempSubscriber());
	}
	
	private static Publisher<TempInfo> tempCelsiusPublisher(String town) {
		return (subscriber) -> {
			TempProcessor processor = new TempProcessor();
			processor.subscribe(subscriber);
			processor.onSubscribe(new TempSubscription(processor, town));
		};
	}
}
