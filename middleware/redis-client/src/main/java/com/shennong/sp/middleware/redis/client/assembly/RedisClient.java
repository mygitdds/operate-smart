package com.shennong.sp.middleware.redis.client.assembly;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.vertx.core.Vertx;
import io.vertx.redis.client.*;

import java.util.List;

public class RedisClient {
    private RedisAPI redisApi;
    public void builderRedisClient(JSONObject dataSource, Vertx vertx){
        RedisOptions redisOptions = new RedisOptions();
        String redisList =dataSource.getString("redis");
        List<String> redis = JSON.parseArray(redisList,String.class);
        redisOptions.setEndpoints(redis);
        redisOptions.setType(RedisClientType.STANDALONE);
        Redis.createClient(
                vertx,
                redisOptions)
                .connect(onConnect -> {
                    if (onConnect.succeeded()) {
                        RedisConnection client = onConnect.result();
                        redisApi = RedisAPI.api(client);
                    }
                });
    }

    public RedisAPI getRedisAPI(){
        return redisApi;
    }
}
