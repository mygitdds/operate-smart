package com.shennong.sp.middleware.config.client.impl;
import com.shennong.sp.middleware.config.client.assembly.ConsulClientBuilder;
import com.shennong.sp.middleware.config.service.ConsulConfigClient;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.serviceproxy.ServiceException;

public class ConsulConfigImpl implements ConsulConfigClient {

    private ConsulClientBuilder consulClientBuilder;
    private Vertx vertx;
    public ConsulConfigImpl(Vertx vertx){
        this.consulClientBuilder = new ConsulClientBuilder();
        consulClientBuilder.createConsulClient(vertx);
        this.vertx = vertx;
    }
    @Override
    public void get(String interfaceName, Handler<AsyncResult<String>> resultHandler) {
        //获取配置信息
        consulClientBuilder.getConsulClient().getValue(interfaceName, res -> {
            if (res.succeeded()) {
                resultHandler.handle(Future.succeededFuture(res.result().getValue()));
            } else {
                ServiceException.fail(1002, res.cause().getMessage());
                res.cause().printStackTrace();
            }
        });
    }
}
