package com.ibm.microservice.backend.communication;

import com.ibm.microservices.Address;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.eventbus.MessageConsumer;
import io.vertx.core.json.JsonObject;


public class BillingVerticle extends AbstractVerticle {

  private void getProductInfo() {
    EventBus eventBus = vertx.eventBus();
    MessageConsumer<String> consumer = eventBus.consumer(Address.PRODUCT_INFO);
    //list for message
    consumer.handler(message -> {
      System.out.println(BillingVerticle.class.getName() + "  -  " + message.body());
    });
  }

  private void getBillingInfo() {
    EventBus eventBus = vertx.eventBus();
    MessageConsumer<JsonObject> consumer = eventBus.consumer(Address.BILLING_INFO);
    //list for message
    consumer.handler(message -> {
      System.out.println(BillingVerticle.class.getName() + "  -  " + message.body());
      //System.out.println(message.body().getInteger("amount"));
    });
  }

  @Override
  public void start(Promise<Void> startPromise) throws Exception {
    super.start(startPromise);
    getBillingInfo();
  }
}
