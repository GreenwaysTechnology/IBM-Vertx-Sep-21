package com.ibm.microservice.cb;

import io.vertx.core.AbstractVerticle;

public class GreeterService extends AbstractVerticle {
  @Override
  public void start() throws Exception {
    super.start();
    vertx.createHttpServer().requestHandler(r -> {
      //to simulate slow calls : The response will be returned after 5secs
      vertx.setTimer(5000, ar -> {
        r.response().end("I am fine but delayed!");
      });
    }).listen(3000);
  }
}
