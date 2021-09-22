package com.ibm.vertx.core.callbacks;

import io.vertx.core.AbstractVerticle;

public class HigherOrderVerticle extends AbstractVerticle {
  private HigherOrderFutureService higherOrderFutureService = new HigherOrderFutureService();

  @Override
  public void start() throws Exception {
    super.start();
    //passing function as parameter
    higherOrderFutureService.startServer(asyncResult -> {
      if (asyncResult.succeeded()) {
        System.out.println(asyncResult.result());
      }
    });
  }
}
