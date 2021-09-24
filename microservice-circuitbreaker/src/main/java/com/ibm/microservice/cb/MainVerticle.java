package com.ibm.microservice.cb;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;

public class MainVerticle extends AbstractVerticle {

  @Override
  public void start(Promise<Void> startPromise) throws Exception {
    vertx.deployVerticle(new HelloService());
    vertx.deployVerticle(new GreeterService());
  }
}
