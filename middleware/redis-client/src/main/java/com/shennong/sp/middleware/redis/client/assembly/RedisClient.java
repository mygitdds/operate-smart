package com.shennong.sp.middleware.redis.client.assembly;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import io.vertx.core.Vertx;
import io.vertx.redis.client.RedisOptions;

public class RedisClient {
    public void builderRedisClient(JSONArray dataSource, Vertx vertx){
        for(int i=0;i<dataSource.size();i++){

            JSONObject jsonObject =dataSource.getJSONObject(i);
            RedisOptions redisOptions = new RedisOptions();
        }

    }
}
