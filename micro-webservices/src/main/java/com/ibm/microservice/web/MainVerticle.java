package com.ibm.microservice.web;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;

public class MainVerticle extends AbstractVerticle {

  private void createServer() {
    //create server instance
    HttpServer httpServer = vertx.createHttpServer();
    //request Handle
    httpServer.requestHandler(httpServerRequest -> {
      HttpServerResponse response = httpServerRequest.response();
      response.end("Hello");
    });
    //start server
    httpServer.listen(8080, server -> {
      System.out.println("Http Server is running on --> " + server.result().actualPort());
    });
  }

  public void createFluentServer() {
    vertx.createHttpServer()
      .requestHandler(httpServerRequest -> {
        httpServerRequest.response().end("Hello");
      }).listen(8080, server -> {
        System.out.println("Http Server is running on --> " + server.result().actualPort());
      });
  }

  public void createFluentServerWithProps() {
    //Response properties
    vertx.createHttpServer()
      .requestHandler(httpServerRequest -> {
        httpServerRequest.response()
          .setStatusCode(200)
          .putHeader("content-type", "text/plain")
          .putHeader("message", "HelloHeader")
          .end("Hello");
      }).listen(8080, server -> {
        System.out.println("Http Server is running on --> " + server.result().actualPort());
      });
  }

  public void sendJson() {
    //JSON object which represents json string
//    JsonObject greeting = new JsonObject();
//    greeting.put("name", "Subramanian");
//    greeting.put("message", "Hello");
    JsonObject greeting = new JsonObject()
      .put("name", "Subramanian")
      .put("message", "Hello");

    JsonArray messages = new JsonArray()
      .add(greeting)
      .add(new JsonObject()
        .put("name", "Geetha")
        .put("message", "welcome"))
      .add(new JsonObject()
        .put("name", "Ram")
        .put("message", "How are you?")
        .put("location", "Coimbatore")
      );

    vertx.createHttpServer()
      .requestHandler(httpServerRequest -> {
        httpServerRequest.response()
          .setStatusCode(200)
          .putHeader("content-type", "application/json")
          .end(messages.encodePrettily());
      }).listen(8080, server -> {
        System.out.println("Http Server is running on --> " + server.result().actualPort());
      });
  }

  @Override
  public void start(Promise<Void> startPromise) throws Exception {

    vertx.createHttpServer()
      .requestHandler(httpServerRequest -> {
        //in order to handle client payload
        httpServerRequest.bodyHandler(buffer -> {
          httpServerRequest
            .response()
            .end(buffer.toJsonObject().getString("message"));
        }).endHandler(handler -> {
          System.out.println("Request has been processed");
        });
      }).listen(8080, server -> {
        System.out.println("Http Server is running on --> " + server.result().actualPort());
      });
  }
}
