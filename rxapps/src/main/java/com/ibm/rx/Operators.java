package com.ibm.rx;

import io.reactivex.rxjava3.core.Observable;

public class Operators {
    public static void main(String[] args) {
        //transform();
        //  filter();
        createPipeLine();
    }

    private static void createPipeLine() {
        Observable
                .range(1, 20)
                .map(Operators::doubleIt)
                .filter(Operators::isEven)
                .take(3)
                .subscribe(System.out::println, System.out::println, () -> System.out.println("done"));
    }

    private static void filter() {
        Observable
                .range(1, 20)
                .filter(Operators::isEven)
                .subscribe(System.out::println, System.out::println, () -> System.out.println("done"));

    }

    private static boolean isEven(int i) {
        return i % 2 == 0;
    }

    public static void transform() {
        //Fluent pattern/builder pattern
        Observable
                .range(1, 20)
                .map(Operators::doubleIt).subscribe(System.out::println, System.out::println, () -> System.out.println("done"));
    }

    public static int doubleIt(int i) {
        System.out.println("called ->" + i);
        return i * 2;
    }
}
