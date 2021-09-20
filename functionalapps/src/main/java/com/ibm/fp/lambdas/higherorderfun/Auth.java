package com.ibm.fp.lambdas.higherorderfun;

public class Auth {
    //higher order
    void login(String userName, String password, Resolve success, Reject fail) {
        //biz logic
        if (userName.equals("admin") && password.equals("admin")) {
            success.resolve("Login Success");
        } else {
            fail.reject(new RuntimeException("Login Failed"));
        }
    }
}
