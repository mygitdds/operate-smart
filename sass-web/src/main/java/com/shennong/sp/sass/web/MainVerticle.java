package com.shennong.sp.sass.web;
import com.hazelcast.config.Config;
import com.shennong.sp.sass.web.handler.auth.Interceptor;
import com.shennong.sp.sass.web.handler.resource.ResourceHandler;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.http.HttpServer;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.core.spi.cluster.ClusterManager;
import io.vertx.ext.web.Route;
import io.vertx.ext.web.Router;
import io.vertx.spi.cluster.hazelcast.ConfigUtil;
import io.vertx.spi.cluster.hazelcast.HazelcastClusterManager;

import java.util.List;

public class MainVerticle extends AbstractVerticle {
  private Logger logger = LoggerFactory.getLogger(MainVerticle.class);
  @Override
  public void start(Promise<Void> startPromise) throws Exception {
    //获取一个集群实例
    /*Config hazelcastConfig = ConfigUtil.loadConfig();
    hazelcastConfig.getGroupConfig()
            .setName("dev")
            .setPassword("dev-pass");
    ClusterManager mgr = new HazelcastClusterManager(hazelcastConfig);
    mgr.setVertx(vertx);
    String node =mgr.getNodeID();
    logger.info("node节点是="+node);*/
   ResourceHandler resourceHandler = new ResourceHandler();
    HttpServer server = vertx.createHttpServer();
    Router router = Router.router(vertx);
    //router.route().handler(new Interceptor(mgr));
    resourceHandler.init(router,vertx);
    List<Route> list =router.getRoutes();
    if(list != null){
        logger.info("route的数量是="+list.get(0).getPath());
    }
    server.requestHandler(router).listen(8080);
  }
}
