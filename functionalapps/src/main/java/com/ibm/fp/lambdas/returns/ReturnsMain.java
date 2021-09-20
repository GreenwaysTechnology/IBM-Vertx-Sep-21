package com.ibm.fp.lambdas.returns;

public class ReturnsMain {
    public static void main(String[] args) {
        Adder adder = null;
        adder = (a, b) -> {
            return a + b;
        };
        System.out.println(adder.add(10, 10));
        //if there is only return statemment, we can remove {} and return statement.
        adder = (a, b) -> a + b;
        System.out.println(adder.add(10, 10));
    }
}
