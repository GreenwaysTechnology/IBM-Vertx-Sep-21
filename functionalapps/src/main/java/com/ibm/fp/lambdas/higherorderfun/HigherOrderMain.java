package com.ibm.fp.lambdas.higherorderfun;

public class HigherOrderMain {
    public static void main(String[] args) {
        Hello hello = new Hello();
        //passing function as parameter
        hello.sayHello(new Greeter() {
            @Override
            public void sayHello() {
                System.out.println("Hello");
            }
        });
        //lambda; function as parameter
        hello.sayHello(() -> System.out.println("Hello Lambda"));

    }
}
