package com.ibm.microservice.web.jdbc;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;

public class ProductRestVerticle extends AbstractVerticle {
  private ProductService productService;

  @Override
  public void start(Promise<Void> startPromise) throws Exception {
    super.start();
    productService = new ProductService(vertx);
    //1-connection-success-create tables-start web server
    productService.createTable().compose(v -> {
      //After inserting data : start web part
      Router router = Router.router(vertx);

      //Middlewares (filters) : Which are executed before handling requests

      //this middleware called for any type of request and any type of url
      router.route().handler(rc -> {
        System.out.println(rc.request().method() + " " + rc.request().path());
        rc.next();
      });
      //i want middleware sepecific to that url
      router.route(HttpMethod.GET, "/api/products").handler(rc -> {
        System.out.println("Products api called");
        rc.next();
      });
      //Builtin middlewares: BodyHandler is json seralizer which serialize the incoming json
      //payload into JsonObject
      router.route().handler(BodyHandler.create());

      //Rest apis
      //Get all the products from the database
      router.get("/api/products").handler(this::handleGetProducts);
      //create new product
      router.post("/api/products").handler(this::handleAddProducts);

      return vertx.createHttpServer().requestHandler(router).listen(8080);
    }).<Void>mapEmpty().onComplete(startPromise);
  }

//  <--Todo - ->
  private void handleAddProducts(RoutingContext routingContext) {

  }

  private void handleGetProducts(RoutingContext routingContext) {
    productService.findAllProducts(asyncResult -> {
      if (asyncResult.succeeded()) {
        routingContext.response()
          .setStatusCode(200)
          .putHeader("content-type", "application/json")
          .end(asyncResult.result());
      }
    });
  }
}
