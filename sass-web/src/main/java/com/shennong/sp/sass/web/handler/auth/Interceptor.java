package com.shennong.sp.sass.web.handler.auth;
import io.vertx.core.Handler;
import io.vertx.core.http.Cookie;
import io.vertx.core.shareddata.AsyncMap;
import io.vertx.core.spi.cluster.ClusterManager;
import io.vertx.ext.web.RoutingContext;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

public class Interceptor implements Handler<RoutingContext> {

    private ClusterManager mgr;

    public Interceptor(ClusterManager mgr){
        this.mgr = mgr;
    }

    /**
     * 校验是否是登录过的。实现一个过滤器，
     * @param routingContext
     */
    @Override
    public void handle(RoutingContext routingContext) {
        //拿到cookie
        RoutingContext context = routingContext;
        Map<String, Cookie> cookie = context.cookieMap();
        Cookie cookie1 = cookie.get("www.shennonggouwu.com");
        String sessionKey = cookie1.getValue();
        //在Hazelcast中去寻找相应的值
        mgr.getAsyncMap("session",map->{
            if(map.succeeded()){
                AsyncMap<Object,Object> sessionMap = map.result();
                sessionMap.get(sessionKey,result->{
                    String session =(String) result.result();
                    if(StringUtils.isNotEmpty(session)){
                        //校验该用户是否有访问，该接口的权限目前跳过
                        String addr = routingContext.request().absoluteURI();
                        //判断该接口是否有权限使用
                    }
                    System.out.println("来获取map了");
                });

            }
        });
    }
}
