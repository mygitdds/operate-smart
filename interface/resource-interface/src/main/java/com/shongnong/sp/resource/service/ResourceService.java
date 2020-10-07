package com.shongnong.sp.resource.service;
import com.shongnong.sp.resource.vo.*;
import io.vertx.codegen.annotations.ProxyGen;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;

@ProxyGen
@VertxGen
public interface ResourceService {

    static ResourceService createProxy(Vertx vertx, String address) {
        return new ResourceServiceVertxEBProxy(vertx, address);
    }
    //生成主资源
    void createResource(CreateResourceReq resource, Handler<AsyncResult<Void>> resultHandler);
    //查询主资源
    void selectResource(SelectBatchCodeReq selectResourceReq, Handler<AsyncResult<ResourceList>> resultHandler);
    //生成批次
    void createBatchCode(BatchCode batch, Handler<AsyncResult<Void>> resultHandler);
    //查询批次
    void selectBatchCode(SelectBatchCodeReq selectBatchCodeReq,Handler<AsyncResult<SelectBatchCodeRsp>> resultHandler);
    //发放卷码
    void grantCode(GrantCodeRecord grantCodeRecord, Handler<AsyncResult<String>> resultHandler);
    //核销卷码
    void verifyCode(String code,Handler<AsyncResult<Boolean>> resultHandler);
}
