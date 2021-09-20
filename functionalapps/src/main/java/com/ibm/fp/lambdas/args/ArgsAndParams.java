package com.ibm.fp.lambdas.args;

public class ArgsAndParams {
    public static void main(String[] args) {
        Name name = null;
        //myname is  args
        name = (String myname) -> {
            System.out.println(myname);
        };
        //"Subramanian" is param
        name.setName("Subramanian");
        //code refactoring : remove {}
        name = (String myname) -> System.out.println(myname);
        name.setName("Subramanian");

        //using type inference
        name = (myname) -> System.out.println(myname);
        name.setName("Subramanian");
        // We can remove () , if there is no type and if there is only one arg
        name = myname -> System.out.println(myname);
        //"Subramanian" - params
        name.setName("Subramanian");
        //more than one arg
        Adder adder = null;
        adder = (int a, int b) -> {
            int c = a + b;
            System.out.println(c);
        };
        adder.add(10,10);

        adder = (a,  b) -> {
            int c = a + b;
            System.out.println(c);
        };
        adder.add(10,10);
    }
}
