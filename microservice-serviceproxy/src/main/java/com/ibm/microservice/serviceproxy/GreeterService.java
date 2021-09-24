package com.ibm.microservice.serviceproxy;

import io.vertx.codegen.annotations.ProxyGen;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.serviceproxy.ServiceProxyBuilder;

@VertxGen  // proxy for java clients
@ProxyGen //non java clients
public interface GreeterService {
  // A couple of factory methods to create an instance and a proxy
  static GreeterService create(Vertx vertx) {
    return new GreeterServiceImpl(vertx);
  }

  static GreeterService createProxy(Vertx vertx,
                                    String address) {
    //return new ServiceProxyBuilder(vertx).setAddress(address).build(GreeterService.class);
     return new GreeterServiceVertxEBProxy(vertx,address);
  }

  //biz method
  Future<String> sayHello(String collection, JsonObject document, Handler<AsyncResult<String>> resultHandler);

}
