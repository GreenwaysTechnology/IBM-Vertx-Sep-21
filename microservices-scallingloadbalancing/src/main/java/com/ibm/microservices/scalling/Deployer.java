package com.ibm.microservices.scalling;

import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;

public class Deployer {
  public static void main(String[] args) {
    Vertx vertx = Vertx.vertx();
    DeploymentOptions options = new DeploymentOptions().setInstances(3);
    vertx.deployVerticle(MainVerticle.class.getName(), options, asyncResult -> {
      System.out.println(asyncResult.result());
    });

  }
}
