package com.ibm.microservice.backend.communication;

import com.ibm.microservices.Address;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.eventbus.MessageConsumer;
import io.vertx.core.json.JsonObject;

public class InventoryVerticle extends AbstractVerticle {
  private void productInfo() {
    EventBus eventBus = vertx.eventBus();
    MessageConsumer<String> consumer = eventBus.consumer(Address.PRODUCT_INFO);
    //list for message
    consumer.handler(message -> {
      System.out.println(InventoryVerticle.class.getName() + "  -  " + message.body());
    });
  }

  @Override
  public void start(Promise<Void> startPromise) throws Exception {
    super.start(startPromise);

    EventBus eventBus = vertx.eventBus();
    MessageConsumer<JsonObject> consumer = eventBus.consumer(Address.IS_STOCK_AVAILABLE);
    //list for message
    consumer.handler(message -> {
      System.out.println(InventoryVerticle.class.getName() + "  -  " + message.body());
      String productName = message.body().getString("name");
      if (productName.equals("tv")) {
        message.reply("Stock Available");
      } else {
        message.reply("Out of Stock");
      }
    });

  }
}
