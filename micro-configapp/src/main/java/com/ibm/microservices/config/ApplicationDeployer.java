package com.ibm.microservices.config;

import io.vertx.config.ConfigRetriever;
import io.vertx.config.ConfigRetrieverOptions;
import io.vertx.config.ConfigStoreOptions;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;

public class ApplicationDeployer {
  private static void inMemoryConfig() {
    Vertx vertx = Vertx.vertx();
    //serverconfig
    JsonObject serverConfig = new JsonObject().put("http.port", 3001);
    //application data configuration
    JsonObject config = new JsonObject().put("message", "Config Data").mergeIn(serverConfig);

    DeploymentOptions options = new DeploymentOptions().setConfig(config);
    vertx.deployVerticle(ApplicationConfigVerticle.class.getName(), options);
  }

  public static void main(String[] args) {

    Vertx vertx = Vertx.vertx();

    //storage configuration
    ConfigStoreOptions options = new ConfigStoreOptions();
    options.setType("file");
    options.setFormat("json");
    options.setConfig(new JsonObject().put("path", "conf/config.json"));

    ConfigRetriever configRetriever = ConfigRetriever.create(vertx,
      new ConfigRetrieverOptions().addStore(options));

    configRetriever.getConfig(config -> {
      if (config.succeeded()) {
        System.out.println("Config is Ready");
        JsonObject configRes = config.result();
        System.out.println(configRes.encodePrettily());
        DeploymentOptions deploymentOptions = new DeploymentOptions().setConfig(configRes);
        vertx.deployVerticle(new ApplicationConfigVerticle(), deploymentOptions);
      } else {
        System.out.println("Config Error : " + config.cause());
      }
    });
  }
}
