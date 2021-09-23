package com.ibm.microservice.blocking;

import io.vertx.core.Vertx;

public class Deployer {
  public static void main(String[] args) {
    Vertx vertx = Vertx.vertx();
    for (int i = 1; i <= 50; i++) {
      vertx.deployVerticle("com.ibm.microservice.blocking.NonBlockingThread");
    }
  }
}
