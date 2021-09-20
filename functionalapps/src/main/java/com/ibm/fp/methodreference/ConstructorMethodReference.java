package com.ibm.fp.methodreference;

public class ConstructorMethodReference {
    public static void main(String[] args) {
        Greeter hello = Hello::new;
        System.out.println(hello.sayHello("Hello"));
    }
}
