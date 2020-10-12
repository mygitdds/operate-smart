package com.shennong.sp.middleware.config.client;

import com.shennong.sp.commom.cache.VertxCache;
import com.shennong.sp.middleware.config.client.impl.ConsulConfigImpl;
import com.shennong.sp.middleware.config.service.ConsulConfigClient;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.eventbus.MessageConsumer;
import io.vertx.core.json.JsonObject;
import io.vertx.serviceproxy.ServiceBinder;

public class MainVerticle extends AbstractVerticle {

  @Override
  public void start(Promise<Void> startPromise) throws Exception {
    VertxCache.getInstance().setVertx(vertx);
    ServiceBinder binder = new ServiceBinder(vertx);
    ConsulConfigClient consulConfigClient = new ConsulConfigImpl();
    MessageConsumer<JsonObject> consumer = binder
            .setAddress("consul-config-service-address")
            .register(ConsulConfigClient.class, consulConfigClient);
    binder.unregister(consumer);
    startPromise.complete();
  }
}