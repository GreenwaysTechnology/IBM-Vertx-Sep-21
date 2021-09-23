package com.ibm.microservice.rest.communication;

import com.ibm.microservices.Address;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.client.HttpResponse;
import io.vertx.ext.web.client.WebClient;

public class ProductGateWayVerticle extends AbstractVerticle {
  private WebClient webClient;

  @Override
  public void start(Promise<Void> startPromise) throws Exception {
    super.start(startPromise);
    webClient = WebClient.create(vertx);
    Router router = Router.router(vertx);
    router.get("/api/products").handler(rc -> {
      //make request
      webClient.get(8080, "localhost", "/api/products")
        .send(ar -> {
          if (ar.succeeded()) {
            HttpResponse<Buffer> response = ar.result();
            rc.response().
              setStatusCode(200).
              putHeader("content-type", "application/json")
              .end(response.bodyAsString());
          } else {
            rc.response().setStatusCode(500).end(ar.cause().getMessage());
          }
        });

    });
    //broadcast to InventoryVerticle,BillingVerticle
    router.get("/api/products/:id").handler(rc -> {
      String param = rc.request().getParam("id");
      //pub-sub
      vertx.eventBus().publish(Address.PRODUCT_INFO, param);
      rc.response().end(param);
    });
    //send
    router.get("/api/products/billing/:amount").handler(rc -> {
      String param = rc.request().getParam("amount");
      //point to point
      JsonObject jsonObject = new JsonObject().put("amount", param);
      vertx.eventBus().send(Address.BILLING_INFO, jsonObject);
      rc.response().end(param);
    });
    //request-reply.
    router.get("/api/products/stock/:name").handler(rc -> {
      String param = rc.request().getParam("name");
      //point to point
      JsonObject jsonObject = new JsonObject().put("name", param);
      vertx.eventBus().request(Address.IS_STOCK_AVAILABLE, jsonObject, ar -> {
        if (ar.succeeded()) {
          String ack = ar.result().body().toString();
          rc.response().setStatusCode(200).end(ack);
        }
      });

    });

    vertx.createHttpServer().requestHandler(router).listen(3000, server -> {
      int port = server.result().actualPort();
      System.out.println("APIGateWay is Running in ---" + port);
    });
  }
}
