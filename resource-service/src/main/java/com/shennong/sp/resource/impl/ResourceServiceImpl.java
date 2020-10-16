package com.shennong.sp.resource.impl;

import com.alibaba.fastjson.JSON;
import com.shennong.sp.middleware.mysql.service.SqlService;
import com.shennong.sp.resource.build.ResourceSqlBuild;
import com.shennong.sp.resource.common.Constant;
import com.shongnong.sp.resource.service.ResourceService;
import com.shongnong.sp.resource.vo.*;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.core.shareddata.Lock;
import io.vertx.core.spi.cluster.ClusterManager;
import io.vertx.serviceproxy.ServiceException;
import org.apache.commons.collections4.CollectionUtils;


import java.util.List;
import java.util.Random;

public class ResourceServiceImpl implements ResourceService {
    private Vertx vertx;
    private SqlService sqlService;
    private ResourceSqlBuild resourceSqlBuild;
    private ClusterManager mgr;
    private Logger logger = LoggerFactory.getLogger(ResourceServiceImpl.class);

    public ResourceServiceImpl(Vertx vertx, ClusterManager mgr) {
        this.vertx = vertx;
        this.mgr = mgr;
        this.sqlService = SqlService.createProxy(this.vertx, Constant.ADDRESS_DB);
        resourceSqlBuild = new ResourceSqlBuild();
    }

    @Override
    public void createResource(CreateResourceReq resource, Handler<AsyncResult<Void>> resultHandler) {
        //主资源，不需要做幂等性判断
        //构建sql与参数通过ResourceSqlBuild
        logger.info("{} [createResource] recv reqId:{} req:{}",resource.getRequestId(), JSON.toJSONString(resource));
        JsonArray params = new JsonArray();
        String sql = resourceSqlBuild.createResourceSql(resource, params);
        sqlService.insert(params, sql, Constant.RESOURCE_DB, result -> {
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
        logger.info("{} [selectResource] recv reqId:{} req:{}", selectResourceReq.getRequestId(),JSON.toJSONString(selectResourceReq));
        JsonArray params = new JsonArray();
        String sql = resourceSqlBuild.selectResourceSql(selectResourceReq, params);
        sqlService.selectList(params, sql.toString(), Constant.RESOURCE_DB, result -> {
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
        logger.info("{} [createBatchCode] recv  reqId:{} req:{}",batch.getRequestId(), JSON.toJSONString(batch));
        //构造sql-不需要幂等性判断
        JsonArray params = new JsonArray();
        String sql = resourceSqlBuild.createBatchCodeSql(batch,params);
        sqlService.insert(params, sql, Constant.RESOURCE_DB, result -> {
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
        logger.info("{} [selectBatchCode] recv reqId:{} req:{}",selectBatchCodeReq.getRequestId(), JSON.toJSONString(selectBatchCodeReq));

        //构建sql
        JsonArray params = new JsonArray();
        String sql = resourceSqlBuild.selectBatchCode(selectBatchCodeReq,params);
        sqlService.selectList(params, sql, Constant.RESOURCE_DB, result -> {
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
        logger.info("{} [grantCode] recv req:{}",grantCodeRecord.getRequestId(), JSON.toJSONString(grantCodeRecord));
        //构建外部资源id去拿到对应的批次id
        JsonArray params = new JsonArray();
        String getResourceSql = resourceSqlBuild.getResourceId(grantCodeRecord,params);
        sqlService.selectList(params,getResourceSql,Constant.RESOURCE_DB, result ->{
            if (result.succeeded()) {
                List<JsonObject> jsonObjects = result.result();
                if(!CollectionUtils.isEmpty(jsonObjects)){
                    JsonObject jsonObject = jsonObjects.get(0);
                    Long resourceId =jsonObject.getLong("id");
                    //获取sql
                    if(resourceId !=null){
                        JsonArray getCodeParams = new JsonArray();
                        String getCodeSql = resourceSqlBuild.getCode(resourceId,grantCodeRecord.getEnterpriseId(),getCodeParams);
                        sqlService.selectList(getCodeParams,getCodeSql,"resource",codeResult->{
                            //获取sql
                            if(codeResult.succeeded()){
                                List<JsonObject> codeList = codeResult.result();
                                if(!CollectionUtils.isEmpty(codeList)){
                                    //，然后，执行发放卷码流程
                                    Random random =new Random();
                                    int num = random.nextInt(codeList.size());
                                    JsonObject codeJson = codeList.get(num);
                                    String code = codeJson.getString("code");
                                    long batchCodeId = codeJson.getLong("batch_code_id");
                                    mgr.getLockWithTimeout(code,2000L,lock ->{
                                        if(lock.succeeded()){
                                            //获取到锁
                                            Lock lock1 = lock.result();
                                            //修改该卷码的状态
                                            JsonArray updateCodePara = new JsonArray();
                                            String updateCodeStatusSql = resourceSqlBuild.updateCodeStatus(code,updateCodePara);
                                            sqlService.update(updateCodePara,updateCodeStatusSql,"resource",updateCodeResult->{
                                                if(updateCodeResult.succeeded()){
                                                    resultHandler.handle(Future.succeededFuture(code));
                                                    //修改住资源
                                                    JsonArray updateResourcePara = new JsonArray();
                                                    String updateResourceSql = resourceSqlBuild.resourceCount(resourceId,1,"grantNum",updateResourcePara);
                                                    sqlService.update(updateResourcePara,updateResourceSql,"resource",updateResourceNumResult->{
                                                        if (updateResourceNumResult.succeeded()){
                                                            //修改批次
                                                            JsonArray updateBatchCodePara = new JsonArray();
                                                            String updateBatchCodeSql = resourceSqlBuild.batchCodeCount(batchCodeId,1,"grantNum",updateBatchCodePara);
                                                            sqlService.update(updateBatchCodePara,updateBatchCodeSql,"resource",updateBatchCodeNumResult->{
                                                                if(updateBatchCodeNumResult.succeeded()){
                                                                    Future.succeededFuture();
                                                                }
                                                            });
                                                        }
                                                    });
                                                }
                                            });
                                        }else {
                                            resultHandler.handle(ServiceException.fail(1002, "请重试"));
                                        }
                                    });
                                }else {
                                    resultHandler.handle(ServiceException.fail(1002, "没有可使用的卷码"));
                                }
                            }
                        });
                    }else {
                        resultHandler.handle(ServiceException.fail(1002, "没有可使用的卷码"));
                    }
                }
            }
        });
    }

    @Override
    public void verifyCode(String code, Handler<AsyncResult<Boolean>> resultHandler) {
        //核销卷码


    }
}
