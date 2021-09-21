package com.ibm.rx.service;

import io.reactivex.rxjava3.core.Observable;

public class MessageStream {

    public Observable<String> streamMessage() {
        return Observable.create(observer -> {
            //push data - which emits event - onData
            observer.onNext("Hello-1");
            observer.onNext("Hello-2");
            observer.onNext("Hello-3");
            //observer.onError(new RuntimeException("something went wrong"));
            observer.onNext("Hello-4");
            observer.onNext("Hello-5");
            observer.onNext("Hello-6");
            observer.onComplete();
        });

    }
}
