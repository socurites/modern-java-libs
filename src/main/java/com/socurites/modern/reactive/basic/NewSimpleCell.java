package com.socurites.modern.reactive.basic;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.Flow.Subscription;
import java.util.function.Consumer;

public class NewSimpleCell implements Subscriber<Integer> {
	private int value = 0;
	private String name;
	private List<Subscriber<? super Integer>> subscribers = new ArrayList<>();

	public NewSimpleCell(String name) {
		this.name = name;
	}

	// Publisher
//	  @Override
//	  public void subscribe(Subscriber<? super Integer> subscriber) {
//	    subscribers.add(subscriber);
//	  }

	public void subscribe(Consumer<? super Integer> onNext) {
		subscribers.add(new Subscriber<Integer>() {

			@Override
			public void onComplete() {
			}

			@Override
			public void onError(Throwable t) {
				t.printStackTrace();
			}

			@Override
			public void onNext(Integer val) {
				onNext.accept(val);
			}

			@Override
			public void onSubscribe(Subscription s) {
			}

		});
	}

	private void notifyAllSubscribers() {
		subscribers.forEach(subscriber -> subscriber.onNext(value));
	}

	// Subscriber
	@Override
	public void onNext(Integer newValue) {
		value = newValue;
		System.out.println(name + ":" + value);
		notifyAllSubscribers();
	}

	// Subscriber
	@Override
	public void onComplete() {
	}

	// Subscriber
	@Override
	public void onError(Throwable t) {
		t.printStackTrace();
	}

	// Subscriber
	@Override
	public void onSubscribe(Subscription s) {
	}
}
