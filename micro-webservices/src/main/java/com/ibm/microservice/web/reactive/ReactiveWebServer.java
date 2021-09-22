package com.ibm.microservice.web.reactive;

import io.vertx.reactivex.core.AbstractVerticle;
import io.vertx.reactivex.ext.web.Router;

public class ReactiveWebServer extends AbstractVerticle {

  private void simpleReactive() {

    //web
    vertx.createHttpServer().requestHandler(rc -> {
//      HttpServerResponse response = rc.response();
      rc.response().end("Hello");
    }).rxListen(3000).subscribe(server -> {
      System.out.println("Reactive WebServer is running --->" + server.actualPort());
    }, err -> {
      System.out.println(err.getMessage());
    });
  }

  @Override
  public void start() throws Exception {
    super.start();
    Router router = Router.router(vertx);
    router.get("/").handler(routingContext -> {
      routingContext.response().end("Reactive Router");
    });
    vertx.createHttpServer().requestHandler(router).rxListen(3000).subscribe(server -> {
      System.out.println("Reactive WebServer is running --->" + server.actualPort());
    }, err -> {
      System.out.println(err.getMessage());
    });

  }
}
