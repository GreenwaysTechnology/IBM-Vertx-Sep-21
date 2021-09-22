package com.ibm.microservice.web;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.ext.web.Router;


public class GreeterRestApiVerticle extends AbstractVerticle {
  @Override
  public void start() throws Exception {
    super.start();
    Router router = Router.router(vertx);

    //HTTP Method mapping
    //HTTP - GET Mapping
    router.get("/api/hello").handler(rc -> {
      //send response
      rc.response().setStatusCode(200).end("Hello");
    });
    router.get("/api/hai").handler(rc -> {
      //send response
      rc.response().setStatusCode(200).end("Hi");
    });
    router.get("/api/greet").handler(rc -> {
      //send response
      rc.response().setStatusCode(200).end("greet");
    });

    vertx.createHttpServer()
      .requestHandler(router)
      .listen(8080, server -> {
        System.out.println("Rest API Server is Running -->" + server.result().actualPort());
      });
  }
}
