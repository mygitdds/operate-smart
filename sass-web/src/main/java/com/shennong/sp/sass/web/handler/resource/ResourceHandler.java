package com.shennong.sp.sass.web.handler.resource;

import com.alibaba.fastjson.JSON;
import com.shennong.sp.commom.http.HttpResponseEntity;
import com.shongnong.sp.resource.service.ResourceService;
import com.shongnong.sp.resource.vo.CreateResourceReq;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;

public class ResourceHandler {
    private Router router;
    private Vertx vertx;
    private ResourceService resourceService;
    //获取一个代理
    public void init(Router router,Vertx vertx){
        this.router = router;
        this.vertx = vertx;
        this.resourceService = ResourceService.createProxy(vertx,"operate-smart-resource");
        createResource();
    }
    public void createResource(){
        router.route(HttpMethod.POST,"/resource/createResource").handler(routingContext -> {
            //拿到rsp
            HttpServerResponse response = routingContext.response();
           //获取到参数，转成
           JsonObject jsonObject = routingContext.getBodyAsJson();
            CreateResourceReq resource =JSON.parseObject(jsonObject.toString(),CreateResourceReq.class);
            resourceService.createResource(resource,result->{
                if(result.succeeded()){
                   // response.write()
                    response.write(HttpResponseEntity.suss());
                }else {
                    response.write(HttpResponseEntity.fail(result.cause().getLocalizedMessage()));
                }
            });
        });
    }
}
