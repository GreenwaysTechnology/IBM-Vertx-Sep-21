package com.ibm.microservice.rest;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.ext.web.Router;

public class GreeterRestVerticle extends AbstractVerticle {
  @Override
  public void start(Promise<Void> startPromise) throws Exception {
    super.start(startPromise);
    Router router = Router.router(vertx);

    router.get("/api/hello").handler(rc -> {
      rc.response().end("Hello,Service Discovery and Registry");
    });
    vertx.createHttpServer().requestHandler(router).listen(3000);
  }
}
