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
import io.vertx.core.shareddata.AsyncMap;
import io.vertx.core.shareddata.Lock;
import io.vertx.core.spi.cluster.ClusterManager;
import io.vertx.serviceproxy.ServiceException;
import org.apache.commons.lang3.StringUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

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
        JsonArray params = new JsonArray();
        String sql = resourceSqlBuild.createResourceSql(resource, params);
        logger.info("createResource request{},sql{},params{}", resource.getRequestId(),sql,params.toString());
        resultHandler.handle(Future.succeededFuture());
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
        logger.info("{} [selectResource] recv req:{}", selectResourceReq.getRequestId(),selectResourceReq.toJson().toString());
        JsonArray params = new JsonArray();
        String sql = resourceSqlBuild.selectResourceSql(selectResourceReq, params);
        sqlService.selectList(params, sql, Constant.RESOURCE_DB, result -> {
            if (result.succeeded()) {
                JsonObject resultJson =new JsonObject();
                JsonArray jsonArray = result.result();
                if(!jsonArray.isEmpty()){
                    //循环把Resource添加到list
                    resultJson.put("resourceList",jsonArray);
                    ResourceList resourceList = new ResourceList(resultJson);
                    resultHandler.handle(Future.succeededFuture(resourceList));
                }else {
                    resultHandler.handle(Future.succeededFuture());
                }

            } else {
                resultHandler.handle(ServiceException.fail(1002, result.cause().getMessage()));
            }
        });
    }

    @Override
    public void createBatchCode(BatchCode batch, Handler<AsyncResult<Void>> resultHandler) {
        logger.info("{} [createBatchCode] recv  req:{}",batch.getRequestId(), batch.toJson().toString());
        //构造sql-不需要幂等性判断
        JsonArray params = new JsonArray();
        String batchCode = UUID.randomUUID().toString();
        batch.setBatchCode(batchCode);
        String sql = resourceSqlBuild.createBatchCodeSql(batch,params);
        sqlService.insert(params, sql, Constant.RESOURCE_DB, result -> {
            if (result.succeeded()) {
                //批量新增code
                List<JsonArray> jsonArrayList = new ArrayList<>(batch.getCodeNumber());
                String code = resourceSqlBuild.createBatchCode(batch,jsonArrayList);
                sqlService.batchOperation(jsonArrayList,code,Constant.RESOURCE_DB,codeResult->{
                    if(codeResult.succeeded()){
                        resultHandler.handle(Future.succeededFuture());
                    }else {
                        resultHandler.handle(ServiceException.fail(1002, result.cause().getMessage()));
                    }
                });
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
                JsonArray jsonArray = result.result();
                if (!jsonArray.isEmpty()) {
                    JsonObject resultJson =new JsonObject();
                    resultJson.put("batchCodeList",jsonArray);
                    SelectBatchCodeRsp selectBatchCodeRsp = new SelectBatchCodeRsp(resultJson);
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
                JsonArray jsonArray = result.result();
                if(!jsonArray.isEmpty()){
                    JsonObject jsonObject = jsonArray.getJsonObject(0);
                    Long resourceId =jsonObject.getLong("id");
                    //获取sql
                    if(resourceId !=null){
                        JsonArray getCodeParams = new JsonArray();
                        String getCodeSql = resourceSqlBuild.getCode(resourceId,grantCodeRecord.getEnterpriseId(),getCodeParams);
                        sqlService.selectList(getCodeParams,getCodeSql,"resource",codeResult->{
                            //获取sql
                            if(codeResult.succeeded()){
                                JsonArray codeList = result.result();
                                if(!codeList.isEmpty()){
                                    //，然后，执行发放卷码流程
                                    Random random =new Random();
                                    int num = random.nextInt(codeList.size());
                                    JsonObject codeJson = codeList.getJsonObject(num);
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
        //去缓存拿code，如果能拿得到，就继续走核销，
        //如果拿不到，就返回true
        String codeCatch = "codeCatch";
        mgr.getAsyncMap(codeCatch,result->{
            AsyncMap<Object, Object> asyncMap= result.result();
            asyncMap.get(code,codeResult->{
                if(codeResult.succeeded()){
                    //获取成功
                    String codeValues = (String) codeResult.result();
                    if(StringUtils.isNotEmpty(codeValues)){
                        //缓存中删除该key
                        asyncMap.remove(code,removeResult->{
                            if (removeResult.succeeded()){
                                //说明是可以被核销的
                                resultHandler.handle(Future.succeededFuture(true));
                            }
                        });
                        //核销后的卷码假如另外一个缓存，方便定时任务，同步到数据库
                        mgr.getAsyncMap(Constant.VERIFY_CATCH,verifyResult->{
                            AsyncMap<Object, Object> asyncVerifyMap= verifyResult.result();
                            asyncVerifyMap.put(code,code,verifyCatchResult->{
                                if(verifyCatchResult.succeeded()){
                                    logger.info("{} [verifyCode] 核销后，卷码写入缓存，成功"+code);
                                }else {
                                    logger.info("{} [verifyCode] 核销后，卷码写入缓存，失败"+code);
                                }
                            });
                        });
                    }else {
                        resultHandler.handle(Future.succeededFuture(false));
                    }
                }else {
                    resultHandler.handle(ServiceException.fail(1002, "校验卷码失败"));
                }
            });
        });
    }
}
