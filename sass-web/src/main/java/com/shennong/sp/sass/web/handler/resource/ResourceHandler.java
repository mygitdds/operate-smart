package com.shennong.sp.sass.web.handler.resource;

import com.shennong.sp.commom.http.HttpResponseEntity;
import com.shongnong.sp.resource.service.ResourceService;
import com.shongnong.sp.resource.vo.BatchCode;
import com.shongnong.sp.resource.vo.CreateResourceReq;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServerResponse;
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
    //创建主资源
    public void createResource() {
        logger.info("添加了handler_createResource");
        router.route(HttpMethod.POST, "/resource/createResource").handler(routingContext -> {
            //拿到rsp
            HttpServerResponse response = routingContext.response();
            response.putHeader("content-type", "application/json");
            CreateResourceReq resource = new CreateResourceReq(routingContext.getBodyAsJson());
            logger.info("转化后的参数"+resource.toJson().toString());
            resourceService.createResource(resource, result -> {
                if (result.succeeded()) {
                    // response.write()
                    logger.info("resourceService.createResource执行成功");
                    response.end(HttpResponseEntity.suss());
                } else {
                    logger.info("resourceService.createResource执行失败"+result.cause().getMessage());
                    response.end(HttpResponseEntity.fail(result.cause().getMessage()));
                }
            });
        });
    }

    //创建批次
    public void createBatchCode(){
        logger.info("添加了handler_createBatchCode");
        router.route(HttpMethod.POST, "/resource/createBatchCode").handler(routingContext -> {
            HttpServerResponse response = routingContext.response();
            response.putHeader("content-type", "application/json");
            BatchCode batch = new BatchCode(routingContext.getBodyAsJson());
            logger.info("转化后的参数"+batch.toJson().toString());
            resourceService.createBatchCode(batch,result->{
                if (result.succeeded()) {
                    response.end(HttpResponseEntity.suss("卷码生成成功请在5分钟后使用"));
                }else {
                    logger.info("resourceService.createBatchCode执行失败"+result.cause().getMessage());
                    response.end(HttpResponseEntity.fail(result.cause().getMessage()));
                }
            });
        });
    }
}
