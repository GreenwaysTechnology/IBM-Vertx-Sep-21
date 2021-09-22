package com.ibm.vertx.core.callbacks;

import io.vertx.core.AbstractVerticle;

public class UserVerticle extends AbstractVerticle {
  private UserService userService = new UserService();

  private void callbackChaining() {
    userService.getUser().onComplete(asyncResult -> {
      if (asyncResult.succeeded()) {
        //call another api incase if user found
        userService.login(asyncResult.result().toString()).onComplete(loginasyncResult -> {
          if (loginasyncResult.succeeded()) {
            //call another api in case login is success
            userService.showDashBoard(loginasyncResult.result()).onComplete(dashboardRes -> {
              if (dashboardRes.succeeded()) {
                System.out.println(dashboardRes.result());
              } else {
                System.out.println(dashboardRes.cause());
              }
            });
          } else {
            System.out.println(loginasyncResult.cause());
          }
        });
      } else {
        System.out.println(asyncResult.cause());
      }
    });
  }

  private void callbacksUsingCompose() {
    //compose ; based function composition
    userService.getUser()
      .compose(user -> {
        return userService.login(user);
      })
      .compose(status -> {
        return userService.showDashBoard(status);
      })
      .onSuccess(System.out::println)
      .onFailure(System.out::println);
    //simplifed version
    userService.getUser()
      .compose(user -> userService.login(user))
      .compose(status -> userService.showDashBoard(status))
      .onSuccess(System.out::println)
      .onFailure(System.out::println);

    //method reference
    userService.getUser()
      .compose(userService::login)
      .compose(userService::showDashBoard)
      .onSuccess(System.out::println)
      .onFailure(System.out::println);
  }

  @Override
  public void start() throws Exception {
    super.start();
    callbacksUsingCompose();
  }
}
