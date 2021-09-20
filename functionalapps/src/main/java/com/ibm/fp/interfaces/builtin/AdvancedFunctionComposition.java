package com.ibm.fp.interfaces.builtin;

import java.util.function.Function;

public class AdvancedFunctionComposition {
    public static void main(String[] args) {
        Function<Integer, Integer> multiplyByTwo = value -> value * 2;

        Function<Integer, Integer> addByThree = value -> value + 3;

        //compose execute the methods right to left // 10+3 =13 = 13 *2 =26
        Function<Integer, Integer> addThenMultiply = multiplyByTwo.compose(addByThree);
        System.out.println(addThenMultiply.apply(10));

        //andThen execute the methods left to right
        Function<Integer, Integer> multiplyAndAdd = multiplyByTwo.andThen(addByThree);
        System.out.println(multiplyAndAdd.apply(10));
    }
}
