package com.shennong.sp.resource.impl;

import com.shongnong.sp.resource.service.GoodsCategoryService;
import com.shongnong.sp.resource.vo.CreateCategoryReq;
import com.shongnong.sp.resource.vo.SelectGoodsCategoryRsp;
import com.shongnong.sp.resource.vo.UpdateCategoryReq;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;

public class GoodsCategoryServiceImpl implements GoodsCategoryService {
    @Override
    public void insertGoodsCategory(CreateCategoryReq document, Handler<AsyncResult<Void>> resultHandler) {

    }

    @Override
    public void updateGoodsCategory(UpdateCategoryReq document, Handler<AsyncResult<Void>> resultHandler) {

    }

    @Override
    public void deleteGoodsCategory(String id, Handler<AsyncResult<Void>> resultHandler) {

    }

    @Override
    public void selectGoodsCategory(String enterpriseId, Handler<AsyncResult<SelectGoodsCategoryRsp>> resultHandler) {

    }
}
