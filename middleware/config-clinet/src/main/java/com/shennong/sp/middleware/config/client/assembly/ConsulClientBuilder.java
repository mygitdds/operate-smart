package com.shennong.sp.middleware.config.client.assembly;
import com.shennong.sp.commom.cache.VertxCache;
import io.vertx.core.Vertx;
import io.vertx.ext.consul.ConsulClient;
import io.vertx.ext.consul.ConsulClientOptions;

public class ConsulClientBuilder {
    private ConsulClient consulClient;
    public ConsulClientBuilder(){

    }
    public ConsulClient getConsulClient() {
        return consulClient;
    }
    public void createConsulClient(Vertx vertx){
        ConsulClientOptions options = new ConsulClientOptions()
                .setHost("139.129.116.204")
                .setPort(8500)
                .setTimeout(5000)
                .setDc("dc1");
        //获取到vert.x-基于不是vert.x的组件，拿到他。

        consulClient = ConsulClient.create(vertx, options);
    }
}
