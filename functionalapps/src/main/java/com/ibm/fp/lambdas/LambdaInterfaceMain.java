package com.ibm.fp.lambdas;

import java.util.function.Function;

public class LambdaInterfaceMain {
    public static void main(String[] args) {
       //
        Util util;
        util = new Util() {
            @Override
            public String doStuff() {
                return "dostuff";
            }

            @Override
            public String doSomething() {
                return "doSomething";
            }
        };
        //lambda - invalid : only one abstract method allowed
//        util = ()->{
//            return "ddd";
//        };
        Function0 function0 = null;
        function0 = ()-> {
            System.out.println("Function 0");
        };
        function0.doStuff();
        function0.doSomething0();
        function0.doSomething1();
        Function0.doProcess();

    }
}
