package com.ibm.fp.lambdas;

@FunctionalInterface
public interface Function0 {
    //only one abstract method
    void doStuff();
    //implementation method
    default void  doSomething0( ){
        System.out.println("do something -0 ");
    }
    default void  doSomething1( ){
        System.out.println("do something -1");
    }
    //static methods
    static void doProcess(){
        System.out.println("Do Process");
    }
}
