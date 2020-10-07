package com.shennong.sp.resource.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.shennong.sp.commom.cache.VertxCache;
import com.shennong.sp.middleware.mysql.service.SqlService;
import com.shongnong.sp.resource.service.ResourceService;
import com.shongnong.sp.resource.vo.*;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.json.JsonArray;
import io.vertx.serviceproxy.ServiceException;

import java.util.Map;

public class ResourceServiceImpl implements ResourceService {
    @Override
    public void createResource(CreateResourceReq resource, Handler<AsyncResult<Void>> resultHandler) {

        SqlService sqlService = SqlService.createProxy(VertxCache.getInstance().getVertx(),"database-service-address");
        //主资源，不需要做幂等性判断
        StringBuilder splic = new StringBuilder("");
        splic.append("insert into t_smart_resource (");
        //把对象转化成map
        String resourceJson = JSONObject.toJSONString(resource);
        Map<String,Object> map = JSON.parseObject(resourceJson,Map.class);

        String sql = "insert into t_smart_resource (enterprise_id,img,resource_id,resource_name,type" +
                ",stock_manager,operator" +
                ")values(?,?,?,?,?,?,?)";
        JsonArray params = new JsonArray().
                add(resource.getEnterpriseId()).add(resource.getImg()).add(resource.getResourceId())
                .add(resource.getName()).add(resource.getType()).add(resource.getStockManager())
                .add(resource.getOperator());

        sqlService.insert(params, sql,"resource", result -> {
            if (result.succeeded()) {
                resultHandler.handle(Future.succeededFuture());
            }else {
                resultHandler.handle(ServiceException.fail(1002, result.cause().getMessage()));
            }
        });
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
