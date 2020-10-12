package com.shennong.sp.middleware.redis.client.impl;

import com.shennong.sp.middleware.redis.service.RedisService;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.json.JsonObject;

/**
 * redis实现类
 */
public class RedisServiceImpl implements RedisService {

    @Override
    public void getValue(String key, Handler<AsyncResult<JsonObject>> resultHandler) {

    }

    @Override
    public void delete(String key, Handler<AsyncResult<Void>> resultHandler) {

    }

    @Override
    public void insert(JsonObject keyValue, Handler<AsyncResult<Void>> resultHandler) {

    }

    @Override
    public void batchInsert(JsonObject keyValue, Handler<AsyncResult<Void>> resultHandler) {

    }

    @Override
    public void batchDelete(JsonObject keyValue, Handler<AsyncResult<Void>> resultHandler) {

    }

    @Override
    public void batchSelect(JsonObject keyValue, Handler<AsyncResult<Void>> resultHandler) {

    }
}
