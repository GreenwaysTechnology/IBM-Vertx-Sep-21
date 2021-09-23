package com.ibm.microservices;

import io.vertx.core.Promise;
import io.vertx.reactivex.core.AbstractVerticle;

public class MainAppVerticle extends AbstractVerticle {
  @Override
  public void start(Promise<Void> startPromise) throws Exception {
    super.start(startPromise);
    vertx.rxDeployVerticle("com.ibm.microservice.web.jdbc.ProductRestVerticle").subscribe(r -> {
      System.out.println("ProductRestVerticle " + r + "deployed");
    }, err -> {
      System.out.println("ProductRestVerticle not deployed" + err.getMessage());
    });
    vertx.rxDeployVerticle("com.ibm.microservice.rest.communication.ProductGateWayVerticle").subscribe(r -> {
      System.out.println("ProductGateWayVerticle " + r + "deployed");
    }, err -> {
      System.out.println("ProductGateWayVerticle not deployed" + err.getMessage());
    });
    vertx.rxDeployVerticle("com.ibm.microservice.backend.communication.BillingVerticle").subscribe(r -> {
      System.out.println("BillingVerticle " + r + " deployed");
    }, err -> {
      System.out.println("BillingVerticle not deployed" + err.getMessage());
    });
    vertx.rxDeployVerticle("com.ibm.microservice.backend.communication.InventoryVerticle").subscribe(r -> {
      System.out.println("InventoryVerticle " + r + " deployed");
    }, err -> {
      System.out.println("InventoryVerticle not deployed" + err.getMessage());
    });
  }

}
