package com.ibm.fp.methodreference;

public class MethodReferenceBasic {
    public static void main(String[] args) {
        Welcome welcome = null;
        //get input and print it
        welcome = name -> System.out.println(name);
        welcome.sayWelcome("Subramanian");
        //using method reference
        welcome = System.out::println;
        welcome.sayWelcome("Subramanian");
        //Upper case
        UpperCase upperCase = null;
        upperCase = name -> name.toUpperCase();
        System.out.println(upperCase.convertToUpper("subramanian"));
        upperCase = String::toUpperCase;
        System.out.println(upperCase.convertToUpper("subramanian"));
    }
}
