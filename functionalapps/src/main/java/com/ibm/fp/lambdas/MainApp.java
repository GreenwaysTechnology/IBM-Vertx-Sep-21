package com.ibm.fp.lambdas;

public class MainApp {
    public static void main(String[] args) {
        Greeter greeter = null;
        //anonymous inner class style
        greeter = new Greeter() {
            @Override
            public String sayGreet() {
                return "Anonymous function";
            }
        };
        System.out.println(greeter.sayGreet());
        //lambda syntax
        greeter = () -> {
            return "Lambda function";
        };
        System.out.println(greeter.sayGreet());


    }
}
