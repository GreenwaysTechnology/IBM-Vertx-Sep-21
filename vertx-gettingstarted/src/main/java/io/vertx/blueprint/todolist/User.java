package io.vertx.blueprint.todolist;

import io.vertx.codegen.annotations.DataObject;

@DataObject(generateConverter = true)
public class User {
  private  int userId;
  private String name;
}
