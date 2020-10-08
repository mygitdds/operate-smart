package com.shennong.sp.resource.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.shennong.sp.commom.cache.VertxCache;
import com.shennong.sp.commom.util.SqlUtil;
import com.shennong.sp.middleware.mysql.service.SqlService;
import com.shongnong.sp.resource.service.ResourceService;
import com.shongnong.sp.resource.vo.*;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonArray;
import io.vertx.serviceproxy.ServiceException;
import sun.security.provider.certpath.Vertex;

import java.util.HashMap;
import java.util.Map;

public class ResourceServiceImpl implements ResourceService {

    private Vertx vertx;
    private SqlService sqlService;

    public ResourceServiceImpl(Vertx vertx){
        this.vertx = vertx;
        this.sqlService = SqlService.createProxy(this.vertx,"database-service-address");
    }

    @Override
    public void createResource(CreateResourceReq resource, Handler<AsyncResult<Void>> resultHandler) {
        //主资源，不需要做幂等性判断
        //把对象转化成map
        String resourceJson = JSONObject.toJSONString(resource);
        JSONObject jsonObject = JSON.parseObject(resourceJson);
        Map<String,String> para = new HashMap<>();
        para.put("enterprise_id","enterpriseId");
        para.put("img","img");
        para.put("resource_id","resourceId");
        para.put("resource_name","resourceName");
        para.put("type","type");
        para.put("stock_manager","stockManager");
        para.put("operator","operator");
        createParaMap(para);
        JsonArray params = new JsonArray();
        String table = "t_smart_resource";
        String sql =SqlUtil.createSql(table,para,jsonObject,params);
        sqlService.insert(params, sql,"resource", result -> {
            if (result.succeeded()) {
                resultHandler.handle(Future.succeededFuture());
            }else {
                resultHandler.handle(ServiceException.fail(1002, result.cause().getMessage()));
            }

        });
    }

    private void createParaMap(Map<String,String> para){
        para.put("enterprise_id","enterpriseId");
        para.put("img","img");
        para.put("resource_id","resourceId");
        para.put("resource_name","resourceName");
        para.put("type","type");
        para.put("stock_manager","stockManager");
        para.put("operator","operator");
    }


    @Override
    public void selectResource(SelectBatchCodeReq selectResourceReq, Handler<AsyncResult<ResourceList>> resultHandler) {

    }

    @Override
    public void createBatchCode(BatchCode batch, Handler<AsyncResult<Void>> resultHandler) {

    }

    @Override
    public void selectBatchCode(SelectBatchCodeReq selectBatchCodeReq, Handler<AsyncResult<SelectBatchCodeRsp>> resultHandler) {

    }

    @Override
    public void grantCode(GrantCodeRecord grantCodeRecord, Handler<AsyncResult<String>> resultHandler) {

    }

    @Override
    public void verifyCode(String code, Handler<AsyncResult<Boolean>> resultHandler) {

    }
}
