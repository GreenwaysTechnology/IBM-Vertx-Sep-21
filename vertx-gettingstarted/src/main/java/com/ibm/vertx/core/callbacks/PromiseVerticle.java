package com.ibm.vertx.core.callbacks;

import io.vertx.core.AbstractVerticle;

public class PromiseVerticle extends AbstractVerticle {
  private PromiseService promiseService = new PromiseService();
  @Override
  public void start() throws Exception {
    super.start();
    //convert promise into future
    promiseService.login().future().onSuccess(System.out::println);
  }
}
