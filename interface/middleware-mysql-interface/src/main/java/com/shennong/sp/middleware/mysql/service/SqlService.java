package com.shennong.sp.middleware.mysql.service;

import io.vertx.codegen.annotations.ProxyGen;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;

import java.util.List;

@ProxyGen
@VertxGen
public interface SqlService {
    static SqlService createProxy(Vertx vertx, String address) {
        return new SqlServiceVertxEBProxy(vertx, address);
    }
    //查询sql 实现选择器模式。
    void selectList(JsonArray params, String sql, String database,Handler<AsyncResult<JsonArray>> resultHandler);
    //删除sql
    void delete(JsonArray params,String sql,String database,Handler<AsyncResult<Void>> resultHandler);
    //更新sql
    void update(JsonArray params,String sql,String database,Handler<AsyncResult<Void>> resultHandler);
    //新增sql
    void insert(JsonArray params,String sql,String database,Handler<AsyncResult<Void>> resultHandler);
    //批量新增或者批量修改
    void batchOperation(List<JsonArray> params,String sql,String database,Handler<AsyncResult<Void>> resultHandler);
}
