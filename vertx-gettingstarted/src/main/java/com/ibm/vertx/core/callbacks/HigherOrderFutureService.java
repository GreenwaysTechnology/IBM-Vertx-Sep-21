package com.ibm.vertx.core.callbacks;

import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;

public class HigherOrderFutureService {

  //higher order function
  public void startServer(Handler<AsyncResult<String>> aHandler) {
    aHandler.handle(Future.succeededFuture("callback"));
  }
}
