package com.shennong.sp.middleware.config.service;
import io.vertx.codegen.annotations.ProxyGen;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;


@ProxyGen
@VertxGen
public interface ConsulConfigClient {
    static ConsulConfigClient createProxy(Vertx vertx, String address) {
        return new ConsulConfigClientVertxEBProxy(vertx, address);
    }
    //获取consul中的配置
    void get(String interfaceName, Handler<AsyncResult<String>> resultHandler);
}
