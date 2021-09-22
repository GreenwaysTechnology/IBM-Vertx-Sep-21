package com.ibm.vertx.core.callbacks;

import io.vertx.core.Future;

public class UserService {

  public Future<String> getUser() {
    System.out.println("----Get User is called----");
    // write biz logic
    String user = "admin";
    if (user != null) {
      return Future.succeededFuture(user);
    }
    return Future.failedFuture(new RuntimeException("User Not Found"));
  }

  public Future<String> login(String user) {
    System.out.println("----login  is called----");

    // write biz logic
    if (user.equals("admin")) {
      return Future.succeededFuture("login success");
    }
    return Future.failedFuture(new RuntimeException("login Failed"));
  }

  public Future<String> showDashBoard(String status) {
    System.out.println("----showDashBoard is called----");

    // write biz logic
    if (status.equals("login success")) {
      return Future.succeededFuture("You are admin");
    }
    return Future.failedFuture("You are guest");
  }

}
