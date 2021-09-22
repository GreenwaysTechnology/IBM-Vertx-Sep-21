package com.ibm.vertx.core.callbacks;

import io.vertx.core.Future;

public class GreeterService {

  //apis
  public Future<String> sayHello() {
    return Future.future(asyncHandler -> {
      //encapsulate data
      asyncHandler.complete("Hello");
    });
  }

  //returning future with Error
  public Future<String> sayError() {
    return Future.future(asyncHandler -> {
      //encapsulate data
      asyncHandler.fail(new RuntimeException("Something went wrong!!!"));
    });
  }

  //biz logic
  public Future<String> sayGreet(String name) {
    return Future.future(asyncHandler -> {
      //biz logic
      if (name.equals("Subramanian")) {
        asyncHandler.complete("Greet " + name);
      } else {
        asyncHandler.fail(new RuntimeException("Name Match Not found "));
      }
    });
  }


}
