package com.shennong.sp.sass.web.handler.auth;

import io.vertx.core.Handler;
import io.vertx.ext.web.RoutingContext;

public class TestHandler implements Handler<RoutingContext> {
    @Override
    public void handle(RoutingContext routingContext) {
        //打印一些访问内容
        System.out.println("成功访问handler");
        routingContext.next();
    }
}
