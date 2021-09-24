package com.ibm.microservice.discovery;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.client.WebClient;
import io.vertx.servicediscovery.ServiceDiscovery;
import io.vertx.servicediscovery.ServiceDiscoveryOptions;
import io.vertx.servicediscovery.types.HttpEndpoint;

public class ConsumerVerticle extends AbstractVerticle {
  @Override
  public void start(Promise<Void> startPromise) throws Exception {
    super.start(startPromise);
    //Service Communication via Service Discovery
    ServiceDiscoveryOptions discoveryOptions = new ServiceDiscoveryOptions();
    //enable discovery server : apache zoo keeper
    discoveryOptions.setBackendConfiguration(new JsonObject()
      .put("connection", "127.0.0.1:2181")
      .put("ephemeral", true)
      .put("guaranteed", true)
      .put("basePath", "/services/my-backend")
    );
    ServiceDiscovery discovery = ServiceDiscovery.create(vertx, discoveryOptions);

    Router router = Router.router(vertx);

    router.get("/api/greet").handler(rc -> {
      //Communicate Service Registry and get Record,Get Reference.
      HttpEndpoint.getWebClient(discovery, new JsonObject().put("name", "greeterApiRecord"), ar -> {
        //Get Reference from the Record
        WebClient webClient = ar.result();
        //do your job with Resource
        webClient.get("/api/hello").send(result -> {
          System.out.println("Response is ready!");
          rc.response().end(result.result().bodyAsString());
        });
        rc.response().endHandler(ar1 -> {
          //remove /release discovery record
          ServiceDiscovery.releaseServiceObject(discovery, webClient);
        });
      });

    });
    vertx.createHttpServer().requestHandler(router).listen(8080);


  }
}
