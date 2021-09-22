package com.ibm.vertx.core.callbacks;

import io.vertx.core.Promise;

public class PromiseService {

  public Promise<String> login(){
    String username = "admin";
    String password = "admin";
    Promise promise = Promise.promise();
    if (username.equals("admin") && password.equals("admin")) {
      promise.complete("login success");
    } else {
      promise.fail("Login failed");
    }
    return promise;
  }
}
