package com.ibm.rx;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;

import java.util.HashMap;

public class StreoTypes {
    public static void main(String[] args) {
     //   createSingle();
        //createMayBe();
        //createCompleteable();
        createMap();
    }

    private static void createMap() {
        HashMap<String,String> hashMap= new HashMap<>();
        hashMap.put("1","one");
        Observable.fromIterable(hashMap.values()).subscribe(System.out::println);
    }


    private static void createCompleteable() {
        Completable.complete().subscribe(() -> System.out.println("Completeable"));
    }

    private static void createMayBe() {
        //only item
        Maybe.just(1).subscribe(System.out::println);
        //only error
        Maybe.error(new RuntimeException("error")).subscribe(System.out::println, System.out::println);
        //only complete
        Maybe.empty().subscribe(System.out::println, System.out::println, () -> System.out.println("onComplete"));
    }

    private static void createSingle() {
        Single.create(emitter -> {
            emitter.onSuccess("Hello");
            emitter.onSuccess("Hi");
        }).subscribe(System.out::println);
        Single.create(emitter -> {
            emitter.onError(new RuntimeException("error"));
        }).subscribe(System.out::println, System.out::println);
        Single.just(1).subscribe(System.out::println);

        //  Single.just(1,2,3).subscribe(System.out::println);

    }

}
