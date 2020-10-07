package com.shongnong.sp.resource.service;

import com.shongnong.sp.resource.vo.CreateGoodsReq;
import com.shongnong.sp.resource.vo.SelectGoodsReq;
import com.shongnong.sp.resource.vo.SelectGoodsRsp;
import com.shongnong.sp.resource.vo.UpdateGoodsReq;
import io.vertx.codegen.annotations.ProxyGen;
import io.vertx.codegen.annotations.VertxGen;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import java.util.List;

/**
 * 商品接口
 */
@ProxyGen
@VertxGen
public interface GoodsService {
    static GoodsService createProxy(Vertx vertx, String address) {
        return new GoodsServiceVertxEBProxy(vertx, address);
    }
    //新建商品
    void insertGoods(CreateGoodsReq document, Handler<AsyncResult<Void>> resultHandler);
    //修改商品
    void updateGoods(UpdateGoodsReq document, Handler<AsyncResult<Void>> resultHandler);
    //删除商品
    void deleteGoods(String document,Handler<AsyncResult<Void>> resultHandler);
    //查询商品
    void selectGoodsList(SelectGoodsReq document, Handler<AsyncResult<SelectGoodsRsp>> resultHandler);

}
