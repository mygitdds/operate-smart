package com.shennong.sp.middleware.redis.service;

import io.vertx.codegen.annotations.ProxyGen;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;

@ProxyGen
@VertxGen
public interface RedisService {
    static RedisService createProxy(Vertx vertx, String address) {
        return new RedisServiceVertxEBProxy(vertx, address);
    }
    //获取key
    void getValue(String key, Handler<AsyncResult<JsonObject>> resultHandler);
    //删除key
    void delete(String key, Handler<AsyncResult<Void>> resultHandler);
    //保存key
    void insert(JsonObject keyValue, Handler<AsyncResult<Void>> resultHandler);
    //批量新增
    void batchInsert(JsonObject keyValue, Handler<AsyncResult<Void>> resultHandler);
    //批量删除
    void batchDelete(JsonObject keyValue, Handler<AsyncResult<Void>> resultHandler);
    //批量获取key
    void batchSelect(JsonObject keyValue, Handler<AsyncResult<Void>> resultHandler);
}
