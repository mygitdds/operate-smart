package com.shennong.sp.sass.web;
import com.alibaba.fastjson.JSON;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.shennong.sp.sass.web.handler.resource.ResourceHandler;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.http.HttpServer;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.core.spi.cluster.ClusterManager;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;
import io.vertx.spi.cluster.hazelcast.HazelcastClusterManager;

import java.util.List;
import java.util.Set;

public class MainVerticle extends AbstractVerticle {
    private Logger logger = LoggerFactory.getLogger(MainVerticle.class);

    @Override
    public void start(Promise<Void> startPromise) throws Exception {
        //获取一个集群实例
        Set<HazelcastInstance> instances = Hazelcast.getAllHazelcastInstances();
        ClusterManager mgr = new HazelcastClusterManager(instances.stream().findFirst().get());
        List<String> node =mgr.getNodes();
        logger.info("集群是可以用的"+ JSON.toJSONString(node));
        ResourceHandler resourceHandler = new ResourceHandler();
        HttpServer server = vertx.createHttpServer();
        Router router = Router.router(vertx);
        router.route().handler(BodyHandler.create());
        resourceHandler.init(router, vertx);
        server.requestHandler(router).listen(8080);
        startPromise.complete();
    }
}
