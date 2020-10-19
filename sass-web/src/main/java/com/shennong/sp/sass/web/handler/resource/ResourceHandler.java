package com.shennong.sp.sass.web.handler.resource;

import com.alibaba.fastjson.JSON;
import com.shennong.sp.commom.http.HttpResponseEntity;
import com.shongnong.sp.resource.service.ResourceService;
import com.shongnong.sp.resource.vo.CreateResourceReq;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.ext.web.Router;

public class ResourceHandler {
    private Router router;
    private Vertx vertx;
    private ResourceService resourceService;
    private Logger logger = LoggerFactory.getLogger(ResourceHandler.class);

    //获取一个代理
    public void init(Router router, Vertx vertx) {
        this.router = router;
        this.vertx = vertx;
        this.resourceService = ResourceService.createProxy(vertx, "operate-smart-resource");
        createResource();
    }

    public void createResource() {
        logger.info("启动执行了create");
        router.route(HttpMethod.POST, "/resource/createResource").handler(routingContext -> {
            //拿到rsp
            HttpServerResponse response = routingContext.response();
            response.putHeader("content-type", "application/json");
            CreateResourceReq resource = new CreateResourceReq(routingContext.getBodyAsJson());
            logger.info("转化后的参数"+resource.toJson().toString());
            resourceService.createResource(resource, result -> {
                if (result.succeeded()) {
                    // response.write()
                    logger.info("执行成功");
                    response.end(HttpResponseEntity.suss());
                } else {
                    logger.info("执行失败"+result.cause().getMessage());
                    response.end(HttpResponseEntity.fail(result.cause().getMessage()));
                }
            });
        });
    }
}
