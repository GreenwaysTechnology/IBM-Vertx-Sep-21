package com.ibm.vertx.core.callbacks;

import io.vertx.core.Future;

public class AppInitService {

  //start db server
  public Future<String> startDbServer() {
    System.out.println("Db Server Started");
    return Future.succeededFuture("Db server is up");
  }

  //start http server
  public Future<String> startWebServer() {
    System.out.println("WebServer Server Started");
    //return Future.succeededFuture("Web server is up");
    return Future.failedFuture("Port is already In Use");
  }

  //start config server
  public Future<String> startConfigServer() {
    System.out.println("Config Server Started");
    return Future.succeededFuture("Config Server is up");
  }
}
