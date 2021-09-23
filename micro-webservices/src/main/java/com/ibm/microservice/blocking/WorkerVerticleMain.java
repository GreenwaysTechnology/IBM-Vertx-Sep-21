package com.ibm.microservice.blocking;

import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;

public class WorkerVerticleMain {
  public static void main(String[] args) {
    VertxOptions vertxOptions = new VertxOptions().setWorkerPoolSize(20);
    Vertx vertx = Vertx.vertx(vertxOptions);
    DeploymentOptions options = new DeploymentOptions().setWorker(true);
    for (int i = 0; i < 50; i++)
      vertx.deployVerticle(new BlockingCodeVerticle(), options);

  }
}
