package com.socurites.modern.flow.temperature;

import java.util.concurrent.Flow.Processor;
import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.Flow.Subscription;

// Processor
// Publisher를 구독한 다음, 수시한 데이터를 가공해 다시 제공하는 것
public class TempProcessor implements Processor<TempInfo, TempInfo> {
	private Subscriber<? super TempInfo> subscriber;
	
	@Override
	public void subscribe(Subscriber<? super TempInfo> subscriber) {
		this.subscriber = subscriber;
		
	}
	
	@Override
	public void onNext(TempInfo item) {
		subscriber.onNext(new TempInfo(item.getTown(), 
				(item.getTemp() - 32) * 5 / 9));	// <-- 섭씨로 변환
	}

	@Override
	public void onSubscribe(Subscription subscription) {
		subscriber.onSubscribe(subscription);
	}

	@Override
	public void onError(Throwable throwable) {
		subscriber.onError(throwable);
	}

	@Override
	public void onComplete() {
		subscriber.onComplete();
	}

}
