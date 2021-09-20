package com.ibm.fp.interfaces;

public class MainApp {
    public static void main(String[] args) {
        Greeter greeter = null;
        //hello
        greeter = new HelloImpl();
        System.out.println(greeter.sayGreet());
        //hai
        greeter = new HaiImpl();
        System.out.println(greeter.sayGreet());
        //Using anonymous inner class

        greeter = new Greeter() {
            @Override
            public String sayGreet() {
                return "Hello anonymous";
            }
        };
        System.out.println(greeter.sayGreet());


        greeter = new Greeter() {
            @Override
            public String sayGreet() {
                return "Hai Again";
            }
        };
        System.out.println(greeter.sayGreet());
    }
}
