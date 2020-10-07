package com.shennong.sp.commom.cache;

import io.vertx.core.Vertx;

//做vert.x的缓存
public class VertxCache {
    private  Vertx vertx;
    private static VertxCache vertxCache;

    private VertxCache(){}

    public static VertxCache getInstance(){
        if(vertxCache == null){
            vertxCache = new VertxCache();
        }
        return vertxCache;
    }

    public  void setVertx(Vertx vertx){
        vertx = vertx;
    }

    public  Vertx getVertx(){
        return vertx;
    }
}
