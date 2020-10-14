package com.shennong.sp.middleware.mysql.client;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

import com.shennong.sp.commom.cache.VertxCache;
import com.shennong.sp.commom.config.ConsulConfig;
import com.shennong.sp.middleware.config.service.ConsulConfigClient;
import com.shennong.sp.middleware.mysql.client.assembly.SqlClient;

import com.shennong.sp.middleware.mysql.client.impl.SqlServiceImpl;
import com.shennong.sp.middleware.mysql.service.SqlService;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.eventbus.MessageConsumer;
import io.vertx.core.json.JsonObject;
import io.vertx.serviceproxy.ServiceBinder;

public class MainVerticle extends AbstractVerticle {

  @Override
  public void start(Promise<Void> startPromise) throws Exception {

    //缓存vertx便于其他地方使用
    VertxCache.getInstance().setVertx(vertx);
    //获取配置
    ConsulConfigClient consulConfigClient = ConsulConfigClient.createProxy(vertx,"config-service-address");
    consulConfigClient.get("sqlList", res2 -> {
      if (res2.succeeded()) {
        //缓存配置
        ConsulConfig.getConsulConfig().setJsonObject(JSON.parseObject(res2.result()));
        System.out.println("拿到的数据是="+JSON.parseObject(res2.result()).toString());
        //构建数据源
        JSONArray jsonArray = ConsulConfig.getConsulConfig().getJsonObject().getJSONArray("sqlList");
        ServiceBinder binder = new ServiceBinder(vertx);
        // Create an instance of your service implementation
        SqlService service = new SqlServiceImpl(vertx,jsonArray);
        //发布对外的接口
        // Register the handler
        MessageConsumer<JsonObject> consumer = binder
                .setAddress("database-service-address")
                .register(SqlService.class, service);
        System.out.println("suss-start");
        startPromise.complete();
      }else {
        startPromise.fail(res2.cause());
      }
    });

  }
}
