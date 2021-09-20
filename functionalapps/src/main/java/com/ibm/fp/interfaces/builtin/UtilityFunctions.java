package com.ibm.fp.interfaces.builtin;

import java.util.function.*;

public class UtilityFunctions {
    public static void main(String[] args) {
        //Supplier
        Supplier<String> stringSupplier = () -> "Hello";
        System.out.println(stringSupplier.get());
        //return only int
        IntSupplier intSupplier = () -> 100;
        System.out.println(intSupplier.getAsInt());

        Predicate<Integer> predicate = number -> number > 10;
        System.out.println(predicate.test(100));
        System.out.println(predicate.test(1));

        Function<String, String> function = input -> input;
        System.out.println(function.apply("Hello"));


        //Bi -two
        BiPredicate<Integer, Integer> biPredicate = (a, b) -> a > b;
        System.out.println(biPredicate.test(10,20));
    }
}
