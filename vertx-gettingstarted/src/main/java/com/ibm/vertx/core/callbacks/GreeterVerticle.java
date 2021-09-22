package com.ibm.vertx.core.callbacks;

import io.vertx.core.AbstractVerticle;

public class GreeterVerticle extends AbstractVerticle {
  private GreeterService greeterService = new GreeterService();

  private void sayHello() {
    //onComplete
    greeterService.sayHello().onComplete(asyncResult -> {
      if (asyncResult.succeeded()) {
        System.out.println(asyncResult.result());
      } else {
        System.out.println(asyncResult.cause());
      }
    });
    //onSuccess
//        greeterService.sayHello().onSuccess(result-> System.out.println(result));
    //fluent pattern/builder pattern
    greeterService.sayHello().onSuccess(System.out::println).onFailure(System.out::println);
  }

  private void sayError() {
    greeterService.sayError().onComplete(asyncResult -> {
      if (asyncResult.succeeded()) {
        System.out.println(asyncResult.result());
      } else {
        System.out.println(asyncResult.cause());
      }
    });
    greeterService.sayError().onSuccess(System.out::println).onFailure(System.out::println);

  }

  private void sayGreet() {
    greeterService.sayGreet("Subramanian")
      .onSuccess(System.out::println)
      .onFailure(System.out::println);
    greeterService.sayGreet("foo")
      .onSuccess(System.out::println)
      .onFailure(System.out::println);
  }

  @Override
  public void start() throws Exception {
    super.start();
    //this.sayHello();
    //  this.sayError();
    this.sayGreet();

  }
}
