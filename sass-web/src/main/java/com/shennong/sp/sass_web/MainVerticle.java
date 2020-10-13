package com.shennong.sp.sass_web;

import com.hazelcast.config.Config;
import com.shennong.sp.sass_web.handler.auth.Interceptor;
import com.shennong.sp.sass_web.handler.resource.ResourceHandler;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.spi.cluster.ClusterManager;
import io.vertx.ext.web.Route;
import io.vertx.ext.web.Router;
import io.vertx.spi.cluster.hazelcast.ConfigUtil;
import io.vertx.spi.cluster.hazelcast.HazelcastClusterManager;

public class MainVerticle extends AbstractVerticle {

  @Override
  public void start(Promise<Void> startPromise) throws Exception {
    //获取一个集群实例
    Config hazelcastConfig = ConfigUtil.loadConfig();
    hazelcastConfig.getGroupConfig()
            .setName("dev")
            .setPassword("dev-pass");
    ClusterManager mgr = new HazelcastClusterManager(hazelcastConfig);
    mgr.setVertx(vertx);
    ResourceHandler resourceHandler = new ResourceHandler();
    HttpServer server = vertx.createHttpServer();
    Router router = Router.router(vertx);
    router.route().handler(new Interceptor(mgr));
    resourceHandler.init(router,vertx);
    server.requestHandler(router).listen(8080);
  }
}
