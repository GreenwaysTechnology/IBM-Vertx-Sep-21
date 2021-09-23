package com.ibm.microservice.web.jdbc;

import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.jdbcclient.JDBCPool;
import io.vertx.sqlclient.RowSet;
import io.vertx.sqlclient.SqlResult;
import io.vertx.sqlclient.templates.SqlTemplate;
import io.vertx.sqlclient.templates.TupleMapper;

import java.util.Arrays;
import java.util.Map;

public class ProductService {
  //SQL Statements
  //CREATE TABLE
  String CRATE_TABLE = "CREATE TABLE IF NOT EXISTS products(id int IDENTITY, name VARCHAR(255), price FLOAT, weight INT)";
  //insert
  String insertQuery = "INSERT INTO  products(name,price,weight) VALUES (#{name},#{price},#{weight})";
  private Vertx vertx;
  //jdbc connection
  private JDBCPool client;
  //SqlTemplate api references
  private SqlTemplate<Map<String, Object>, RowSet<JsonObject>> getProductTemplate;
  private SqlTemplate<JsonObject, SqlResult<Void>> addProductTemplate;

  public ProductService(Vertx vertx) {
    this.vertx = vertx;
    init();
  }

  private void init() {
    //initlaize the connection
    client = JDBCPool.pool(this.vertx, new JsonObject()
      .put("url", "jdbc:hsqldb:mem:test?shutdown=true")
      .put("dirver_class", "org.hsqldb.jdbcDriver"));
    //Test Connection
    client.getConnection().onSuccess(System.out::println).onFailure(System.out::println);
    addProductTemplate = SqlTemplate.forUpdate(client, insertQuery).mapFrom(TupleMapper.jsonObject());

  }

  public Future createTable() {
    return client.query(CRATE_TABLE)
      .execute()
      .compose(res -> addProductTemplate.executeBatch(
        Arrays.asList(
          new JsonObject().put("name", "Tv").put("price", 10000).put("weight", 2),
          new JsonObject().put("name", "watch").put("price", 234).put("weight", 2),
          new JsonObject().put("name", "radio").put("price", 1000).put("weight", 2),
          new JsonObject().put("name", "computer").put("price", 345).put("weight", 2),
          new JsonObject().put("name", "tableandchair").put("price", 8999).put("weight", 2))));
  }

  public void findAllProducts(Handler<AsyncResult<String>> aHandler) {
    String selectProducts = "SELECT * From products";
    client.query(selectProducts)
      .execute(rowSet -> {
        if (rowSet.failed()) {
          //rc.fail(500);
          aHandler.handle(Future.failedFuture("500"));
        } else {
          JsonArray arr = new JsonArray();
          rowSet.result().forEach(row -> {
            arr.add(row.toJson());
          });
          aHandler.handle(Future.succeededFuture(arr.encodePrettily()));
        }
      });

  }


}
