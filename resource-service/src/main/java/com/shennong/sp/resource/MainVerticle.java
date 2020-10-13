package com.shennong.sp.resource;
import com.hazelcast.config.Config;
import com.shennong.sp.middleware.mysql.service.SqlService;
import com.shennong.sp.resource.impl.ResourceServiceImpl;
import com.shongnong.sp.resource.service.ResourceService;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.eventbus.MessageConsumer;
import io.vertx.core.json.JsonObject;
import io.vertx.core.spi.cluster.ClusterManager;
import io.vertx.serviceproxy.ServiceBinder;
import io.vertx.spi.cluster.hazelcast.ConfigUtil;
import io.vertx.spi.cluster.hazelcast.HazelcastClusterManager;

public class MainVerticle extends AbstractVerticle {

  @Override
  public void start(Promise<Void> startPromise) throws Exception {
    //获取配置中心的配置
    Config hazelcastConfig = ConfigUtil.loadConfig();

    hazelcastConfig.getGroupConfig()
            .setName("dev")
            .setPassword("dev-pass");
    ClusterManager mgr = new HazelcastClusterManager(hazelcastConfig);
    mgr.setVertx(vertx);
    ResourceService resourceService = new ResourceServiceImpl(vertx,mgr);
    //发布服务
    ServiceBinder binder = new ServiceBinder(vertx);
    MessageConsumer<JsonObject> consumer = binder
            .setAddress("operate-smart-resource")
            .register(ResourceService.class, resourceService);
    binder.unregister(consumer);

  }
}
