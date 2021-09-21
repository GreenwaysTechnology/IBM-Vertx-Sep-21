package com.ibm.vertx.core;

import io.vertx.core.AbstractVerticle;

public class GreeterVerticle extends AbstractVerticle {
  //life cycle methods

  @Override
  public void start() throws Exception {
    super.start();
    System.out.println("Greeter verticle is deployed");
  }

  @Override
  public void stop() throws Exception {
    super.stop();
    System.out.println("Greeter verticle is Undeployed");

  }
}
