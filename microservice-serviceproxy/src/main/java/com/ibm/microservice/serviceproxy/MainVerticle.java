package com.ibm.microservice.serviceproxy;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.json.JsonObject;

public class MainVerticle extends AbstractVerticle {

  @Override
  public void start(Promise<Void> startPromise) throws Exception {

//    GreeterService greeterService = GreeterService.createProxy(vertx, "greeter.service");
//    JsonObject document = new JsonObject().put("name", "vertx");
//
//    greeterService.sayHello("hello", document, asyncResult -> {
//      System.out.println("inside service proxy : " +asyncResult.result());
//    });


    vertx.createHttpServer().requestHandler(req -> {

      req.response()
        .putHeader("content-type", "text/plain")
        .end("hello");
    }).listen(8888, http -> {
      if (http.succeeded()) {
        startPromise.complete();
        System.out.println("HTTP server started on port 8888");
      } else {
        startPromise.fail(http.cause());
      }
    });
  }
}
