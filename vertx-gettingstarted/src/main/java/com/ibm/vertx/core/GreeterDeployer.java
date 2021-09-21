package com.ibm.vertx.core;

import io.vertx.core.Vertx;
import io.vertx.example.util.Runner;

public class GreeterDeployer {
  public static void main(String[] args) {
    Vertx vertx = Vertx.vertx();
    vertx.deployVerticle(new GreeterVerticle());
    vertx.deployVerticle(GreeterVerticle.class.getName());
    vertx.deployVerticle("com.ibm.vertx.core.GreeterVerticle");
    Runner.runExample(GreeterVerticle.class);

  }
}
