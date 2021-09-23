package com.ibm.microservice.blocking;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;

public class BlockingCodeVerticle extends AbstractVerticle {
  @Override
  public void start(Promise<Void> startPromise) throws Exception {
    super.start(startPromise);
    System.out.println(BlockingCodeVerticle.class.getName() + " is running on -> " + Thread.currentThread().getName());
    System.out.println("start");
    blockMe();
    System.out.println("After blocking code");
  }

  private void blockMe() {
    try {
      Thread.sleep(10000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

}
