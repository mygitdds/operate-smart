package com.shennong.sp.resource.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.shennong.sp.commom.cache.VertxCache;
import com.shennong.sp.commom.util.SqlUtil;
import com.shennong.sp.middleware.mysql.service.SqlService;
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
    public void selectResource(SelectResourceReq selectResourceReq, Handler<AsyncResult<ResourceList>> resultHandler) {
        //构造查询sql
        StringBuilder sql = new StringBuilder("select id,enterprise_id as enterpriseId ,img,resource_id as resourceId,name,type,stock_manager as stockManager," +
                    "operator,total_quantity as totalQuantity,available_quantity AS availableQuantity,foreign_resource_id AS foreignResourceId" +
                ",grant_num as grantNum,invalid_num as invalidNum,verify_num verifyNum,total from ");
        String table = "t_smart_resource";
        JsonArray params = new JsonArray();
        params.add(selectResourceReq.getEnterpriseId());
        sql.append(table);
        sql.append(" where enterprise_id = ?  limit ?,?");
        if(StringUtils.isNotEmpty(selectResourceReq.getResourceName())){
            sql.append("and resource_name =? ");
            params.add(selectResourceReq.getResourceName());
        }
        sql.append("limit ?,?");
        params.add(selectResourceReq.getPage());
        params.add(selectResourceReq.getRows());
        sqlService.selectList(params,sql.toString(),"resource",result->{
            if (result.succeeded()) {
                List<JsonObject> list = result.result();
                ResourceList resourceList = new ResourceList();
                String resourceListString = JSON.toJSONString(list);
                List<Resource> resources = JSON.parseArray(resourceListString,Resource.class);
                resourceList.setResourceList(resources);
                resultHandler.handle(Future.succeededFuture(resourceList));
            }else {
                resultHandler.handle(ServiceException.fail(1002, result.cause().getMessage()));
            }
        });
    }

    @Override
    public void createBatchCode(BatchCode batch, Handler<AsyncResult<Void>> resultHandler) {
        //构造sql-不需要幂等性判断
        Map<String,String> codePara = new HashMap<>();
        codePara.put("resource_id","resourceId");
        codePara.put("verify_type","verifyType");
        codePara.put("claim_rules","claimRules");
        codePara.put("code_number","codeNumber");
        codePara.put("type","type");
        codePara.put("grant_type","grantType");
        codePara.put("brush_count","brushCount");
        batch.setClaimRulesString(batch.getClaimRules().toString());
        String codeJson = JSONObject.toJSONString(batch);
        JSONObject jsonObject = JSON.parseObject(codeJson);
        JsonArray params = new JsonArray();
        String table = "t_smart_resource";
        String sql =SqlUtil.createSql(table,codePara,jsonObject,params);
        sqlService.insert(params, sql,"resource", result -> {
            if (result.succeeded()) {
                resultHandler.handle(Future.succeededFuture());
                //新增code
            }else {
                resultHandler.handle(ServiceException.fail(1002, result.cause().getMessage()));
            }

        });
    }

    @Override
    public void selectBatchCode(SelectBatchCodeReq selectBatchCodeReq, Handler<AsyncResult<SelectBatchCodeRsp>> resultHandler) {
        //构建sql
        StringBuilder sql = new StringBuilder();
        sql.append("select id,resource_id as resourceId,verify_type as verifyType,claim_rules as claimRulesString,code_number as codeNumber ,type,grant_type as grantType,brush_count as brushCount from t_smart_batch_code ");
        sql.append("where enterprise_id=? and resource_id = ? limit ?,?");
        JsonArray params = new JsonArray();
        params.add(selectBatchCodeReq.getEnterpriseId());
        params.add(selectBatchCodeReq.getResourceId());
        params.add(selectBatchCodeReq.getPage());
        params.add(selectBatchCodeReq.getRows());
        sqlService.selectList(params, sql.toString(),"resource", result -> {
            if (result.succeeded()) {
                SelectBatchCodeRsp selectBatchCodeRsp = new SelectBatchCodeRsp();
                List<JsonObject> list = result.result();
                if(!CollectionUtils.isEmpty(list)){
                    String batchCodeList = JSON.toJSONString(list);
                    List<BatchCode> batchCodes = JSON.parseArray(batchCodeList,BatchCode.class);
                    selectBatchCodeRsp.setBatchCodeList(batchCodes);
                    resultHandler.handle(Future.succeededFuture(selectBatchCodeRsp));
                }else {
                    resultHandler.handle(Future.succeededFuture());
                }
            }else {
                resultHandler.handle(ServiceException.fail(1002, result.cause().getMessage()));
            }
        });
    }

    @Override
    public void grantCode(GrantCodeRecord grantCodeRecord, Handler<AsyncResult<String>> resultHandler) {
        //构建sql 发放卷码
        StringBuilder sql = new StringBuilder();
        //拿出来10个
        sql.append("select  ");
    }

    @Override
    public void verifyCode(String code, Handler<AsyncResult<Boolean>> resultHandler) {

    }
}
