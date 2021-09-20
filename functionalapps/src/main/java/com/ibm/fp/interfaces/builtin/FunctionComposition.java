package com.ibm.fp.interfaces.builtin;

import java.util.function.Predicate;

public class FunctionComposition {
    public static void main(String[] args) {
        Predicate<String> startsWith = text -> text.startsWith("A");
        Predicate<String> endsWith = text -> text.endsWith("x");
        //coimbine startsAndendsWith
        //coimbine with && Operator : which is not readable,looks like imperative
        // Predicate<String> startsWithAndEndsWith = text -> startsWith.test(text) && endsWith.test(text);
        //declarative using and mehtod
        Predicate<String> startsWithAndEndsWith = startsWith.and(endsWith);
        boolean result = startsWithAndEndsWith.test("A hardworking person must relax");
        if (result) {
            System.out.println("Text Starts With A and Ends with e");
        } else {
            System.out.println("No Match found");

        }
        //
        Predicate<String> composedOR = startsWith.or(startsWithAndEndsWith);
        String matchFound = composedOR.test("Hello how are you") ? "Match found" : "Match Not Found";
        System.out.println(matchFound);
    }
}
