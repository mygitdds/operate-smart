package com.shennong.sp.resource.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.shennong.sp.commom.cache.VertxCache;
import com.shennong.sp.commom.util.SqlUtil;
import com.shennong.sp.middleware.mysql.service.SqlService;
import com.shennong.sp.resource.build.ResourceSqlBuild;
import com.shongnong.sp.resource.service.ResourceService;
import com.shongnong.sp.resource.vo.*;
import io.netty.util.internal.StringUtil;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.serviceproxy.ServiceException;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import sun.security.provider.certpath.Vertex;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResourceServiceImpl implements ResourceService {
    private Vertx vertx;
    private SqlService sqlService;
    private ResourceSqlBuild resourceSqlBuild;

    public ResourceServiceImpl(Vertx vertx) {
        this.vertx = vertx;
        this.sqlService = SqlService.createProxy(this.vertx, "database-service-address");
        resourceSqlBuild = new ResourceSqlBuild();
    }

    @Override
    public void createResource(CreateResourceReq resource, Handler<AsyncResult<Void>> resultHandler) {
        //主资源，不需要做幂等性判断
        //构建sql与参数通过ResourceSqlBuild
        JsonArray params = new JsonArray();
        String sql = resourceSqlBuild.createResourceSql(resource, params);
        sqlService.insert(params, sql, "resource", result -> {
            if (result.succeeded()) {
                resultHandler.handle(Future.succeededFuture());
            } else {
                resultHandler.handle(ServiceException.fail(1002, result.cause().getMessage()));
            }
        });
    }

    @Override
    public void selectResource(SelectResourceReq selectResourceReq, Handler<AsyncResult<ResourceList>> resultHandler) {
        //构造查询sql
        JsonArray params = new JsonArray();
        String sql = resourceSqlBuild.selectResourceSql(selectResourceReq, params);
        sqlService.selectList(params, sql.toString(), "resource", result -> {
            if (result.succeeded()) {
                List<JsonObject> list = result.result();
                ResourceList resourceList = new ResourceList();
                String resourceListString = JSON.toJSONString(list);
                List<Resource> resources = JSON.parseArray(resourceListString, Resource.class);
                resourceList.setResourceList(resources);
                resultHandler.handle(Future.succeededFuture(resourceList));
            } else {
                resultHandler.handle(ServiceException.fail(1002, result.cause().getMessage()));
            }
        });
    }

    @Override
    public void createBatchCode(BatchCode batch, Handler<AsyncResult<Void>> resultHandler) {
        //构造sql-不需要幂等性判断
        JsonArray params = new JsonArray();
        String sql = resourceSqlBuild.createBatchCodeSql(batch,params);
        sqlService.insert(params, sql, "resource", result -> {
            if (result.succeeded()) {
                resultHandler.handle(Future.succeededFuture());
                //批量新增code
            } else {
                resultHandler.handle(ServiceException.fail(1002, result.cause().getMessage()));
            }
        });
    }

    @Override
    public void selectBatchCode(SelectBatchCodeReq selectBatchCodeReq, Handler<AsyncResult<SelectBatchCodeRsp>> resultHandler) {
        //构建sql
        JsonArray params = new JsonArray();
        String sql = resourceSqlBuild.selectBatchCode(selectBatchCodeReq,params);
        sqlService.selectList(params, sql, "resource", result -> {
            if (result.succeeded()) {
                SelectBatchCodeRsp selectBatchCodeRsp = new SelectBatchCodeRsp();
                List<JsonObject> list = result.result();
                if (!CollectionUtils.isEmpty(list)) {
                    String batchCodeList = JSON.toJSONString(list);
                    List<BatchCode> batchCodes = JSON.parseArray(batchCodeList, BatchCode.class);
                    selectBatchCodeRsp.setBatchCodeList(batchCodes);
                    resultHandler.handle(Future.succeededFuture(selectBatchCodeRsp));
                } else {
                    resultHandler.handle(Future.succeededFuture());
                }
            } else {
                resultHandler.handle(ServiceException.fail(1002, result.cause().getMessage()));
            }
        });
    }

    @Override
    public void grantCode(GrantCodeRecord grantCodeRecord, Handler<AsyncResult<String>> resultHandler) {
        //构建外部资源id去拿到对应的批次id
        JsonArray params = new JsonArray();
        String getResourceSql = resourceSqlBuild.getResourceId(grantCodeRecord,params);
        sqlService.selectList(params,getResourceSql,"resource", result ->{
            if (result.succeeded()) {
                List<JsonObject> jsonObjects = result.result();
                if(!CollectionUtils.isEmpty(jsonObjects)){
                    JsonObject jsonObject = jsonObjects.get(0);
                    String resourceId =jsonObject.getString("id");
                    //获取拿到code的sql
                    if(StringUtils.isNotEmpty(resourceId)){
                        JsonArray getCodeParams = new JsonArray();
                        String getCodeSql = resourceSqlBuild.getCode(Long.parseLong(resourceId),grantCodeRecord.getEnterpriseId(),getCodeParams);
                        sqlService.selectList(getCodeParams,getCodeSql,"resource",codeResult->{
                            //获取sql
                            List<JsonObject> codeList = codeResult.result();
                            if(!CollectionUtils.isEmpty(codeList)){
                                //，然后，执行发放卷码流程
                            }
                        });
                    }

                }


            }
        });

        //根据批次id拿到code
        //然后使用乐观锁
        //构建sql 发放卷码
        StringBuilder sql = new StringBuilder();
        //拿出来10个
        sql.append("select  ");
    }

    @Override
    public void verifyCode(String code, Handler<AsyncResult<Boolean>> resultHandler) {

    }
}
