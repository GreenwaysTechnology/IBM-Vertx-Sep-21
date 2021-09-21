package com.ibm.rx;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Observable;

public class BackPressureOperator {
    public static void main(String[] args) {
        createFlowable();
    }

    private static void createFlowable() {
        //push + pull - Reactive Stream
        Flowable.just(1).subscribe(System.out::println);
    }

}
