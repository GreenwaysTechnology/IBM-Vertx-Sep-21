package com.ibm.vertx.core;

import io.vertx.core.Vertx;

public class HelloWorldApp {
  public static void main(String[] args) {
    System.out.println("Vertx Starts");
    //Get the Reference of Vertx Engine
    Vertx myVertx = Vertx.vertx();
    //create some http server
    myVertx.createHttpServer()
      .requestHandler(httpServerRequest -> httpServerRequest.response().end("Hello")).listen(8080);
  }
}
