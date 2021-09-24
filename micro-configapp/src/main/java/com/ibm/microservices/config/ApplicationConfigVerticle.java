package com.ibm.microservices.config;

import io.vertx.config.ConfigRetriever;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;

public class ApplicationConfigVerticle extends AbstractVerticle {
  @Override
  public void start(Promise<Void> startPromise) throws Exception {
    super.start(startPromise);
    JsonObject conf = config();
    Router router = Router.router(vertx);
    router.get("/api/config").handler(rc -> {
      rc.response().setStatusCode(200).putHeader("content-type", "application/json").end(conf.encodePrettily());
    });
    vertx.createHttpServer().requestHandler(router).listen(conf.getInteger("http.port",3000));
  }
}
