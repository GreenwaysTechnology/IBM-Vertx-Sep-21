package com.ibm.rx;

import io.reactivex.rxjava3.core.Observable;

public class FirstStream {
    public static void main(String[] args) {
        //Create Stream // Producer/Publisher
        Observable<String> stream = Observable.create(observer -> {
            //push data - which emits event - onData
            observer.onNext("Hello-1");
            observer.onNext("Hello-2");
            observer.onNext("Hello-3");
            observer.onNext("Hello-4");
            observer.onNext("Hello-5");
            observer.onNext("Hello-6");

            observer.onComplete();
        });

        //Subscriber
        stream.subscribe(data -> {
            System.out.println(data);
        }, error -> {
            System.out.println(error);
        }, () -> {
            System.out.println("Stream Closed");
        });

    }

}
