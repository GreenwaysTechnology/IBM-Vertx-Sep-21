package com.ibm.microservice.blocking;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.ext.web.Router;

public class ExecuteBlockingVerticle extends AbstractVerticle {
  private GreeterService greeterService = new GreeterService();

  private void runBlocking() {
    vertx.executeBlocking(blockingHandler -> {
      System.out.println("Blocking Code Runs in -->" + Thread.currentThread().getName());
      //calls blocking api
      try {
        String result = greeterService.getMessage();
        blockingHandler.complete(result);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }, ar -> {
      System.out.println("Nonblocking Code Runs in -->" + Thread.currentThread().getName());
      if (ar.succeeded()) {
        System.out.println(ar.result());
      }
    });
  }

  @Override
  public void start(Promise<Void> startPromise) throws Exception {
    super.start(startPromise);
    //Rest service talks to blocking api , then how to handle it
    Router router = Router.router(vertx);

    //hanlder method is only for non blocking code
    //router.get("/api/block").handler();
    router.get("/api/block").blockingHandler(rc -> {
      try {
        String result = greeterService.getMessage();
        rc.response().setStatusCode(200).end(result);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    });

    vertx.createHttpServer().requestHandler(router).listen(3000, server -> {
      System.out.println("Server is running " + server.result().actualPort());
    });

  }
}
