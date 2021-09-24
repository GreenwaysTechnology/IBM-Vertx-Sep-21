package com.ibm.microservice.serviceproxy;

import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;

public class GreeterServiceImpl implements GreeterService {
  public GreeterServiceImpl(Vertx vertx) {
  }
  @Override
  public Future<String> sayHello(String message, JsonObject document, Handler<AsyncResult<String>> resultHandler) {
    System.out.println("sayHello is called inside proxy implemenation");
    if (message.equals("hello")) {
      return Future.succeededFuture(message.toLowerCase() + "!!!");
    }
    return Future.succeededFuture("It is not hello");
  }
}
