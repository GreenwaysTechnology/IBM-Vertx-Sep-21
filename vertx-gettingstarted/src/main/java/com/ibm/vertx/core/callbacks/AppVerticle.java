package com.ibm.vertx.core.callbacks;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.CompositeFuture;
import io.vertx.core.Future;

public class AppVerticle extends AbstractVerticle {
  private AppInitService initService = new AppInitService();

  @Override
  public void start() throws Exception {
    super.start();
    //coordinate all futures
    Future<String> dbServer = initService.startDbServer();
    Future<String> webServer = initService.startWebServer();
    Future<String> configServer = initService.startConfigServer();

    CompositeFuture.all(dbServer, webServer, configServer).onComplete(ready -> {
      if (ready.succeeded()) {
        System.out.println(ready.result());
        System.out.println("All servers are up! App is being inialized");
      } else {
        System.out.println(ready.cause().getMessage());
      }
    });
    System.out.println("CompositeFuture.any");
    CompositeFuture.any(dbServer, webServer, configServer).onComplete(ready -> {
      if (ready.succeeded()) {
        System.out.println(ready.result());
        System.out.println("All servers are up! App is being inialized");
      } else {
        System.out.println(ready.cause().getMessage());
      }
    });
  }
}
