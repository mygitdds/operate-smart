package com.shennong.sp.middleware.config.client.assembly;
import com.shennong.sp.commom.cache.VertxCache;
import io.vertx.core.Vertx;
import io.vertx.ext.consul.ConsulClient;
import io.vertx.ext.consul.ConsulClientOptions;

public class ConsulClientBuilder {
    private static ConsulClientBuilder consulClientBuilder;
    private ConsulClient consulClient;
    private ConsulClientBuilder(){

    }

    public ConsulClient getConsulClient() {
        return consulClient;
    }

    public static ConsulClientBuilder getInstance(){
        if(consulClientBuilder ==null){
            consulClientBuilder = new ConsulClientBuilder();
        }
        return consulClientBuilder;
    }
    public void createConsulClient(){
        ConsulClientOptions options = new ConsulClientOptions()
                .setHost("consul.example.com")
                .setPort(8500)
                .setTimeout(5000)
                .setDc("dc1");
        //获取到vert.x-基于不是vert.x的组件，拿到他。
        Vertx vertx = VertxCache.getInstance().getVertx();
        consulClient = ConsulClient.create(vertx, options);
    }
}
