package com.shongnong.sp.resource.service;
import com.shongnong.sp.resource.vo.CreateCategoryReq;
import com.shongnong.sp.resource.vo.SelectGoodsCategoryRsp;
import com.shongnong.sp.resource.vo.UpdateCategoryReq;
import io.vertx.codegen.annotations.ProxyGen;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;

/**
 * 商品类目
 */
@ProxyGen
@VertxGen
public interface GoodsCategoryService {

    static GoodsCategoryService createProxy(Vertx vertx, String address) {
        return new GoodsCategoryServiceVertxEBProxy(vertx, address);
    }
    //创建类目
    void insertGoodsCategory(CreateCategoryReq document, Handler<AsyncResult<Void>> resultHandler);
    //修改类目
    void updateGoodsCategory(UpdateCategoryReq document, Handler<AsyncResult<Void>> resultHandler);
    //删除类目
    void deleteGoodsCategory(String id,Handler<AsyncResult<Void>> resultHandler);
    //查询类目
    void selectGoodsCategory(String enterpriseId,Handler<AsyncResult<SelectGoodsCategoryRsp>> resultHandler);
}
