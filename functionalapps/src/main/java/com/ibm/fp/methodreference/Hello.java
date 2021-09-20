package com.ibm.fp.methodreference;

class Hello {
    private String message;

    //no arg constructor
    public Hello() {

    }
    //single arg
    public Hello(String message) {
        this.message = message;
        System.out.println(this.message);
    }

    public String getMessage() {
        return message;
    }
}