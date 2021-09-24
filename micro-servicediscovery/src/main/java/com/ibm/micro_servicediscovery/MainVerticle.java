package com.ibm.micro_servicediscovery;

import com.ibm.microservice.discovery.ConsumerVerticle;
import com.ibm.microservice.discovery.PublisherVerticle;
import com.ibm.microservice.rest.GreeterRestVerticle;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;

public class MainVerticle extends AbstractVerticle {

  @Override
  public void start(Promise<Void> startPromise) throws Exception {
      vertx.deployVerticle(new GreeterRestVerticle());
      vertx.deployVerticle(new PublisherVerticle());
      vertx.deployVerticle(new ConsumerVerticle());
  }
}
