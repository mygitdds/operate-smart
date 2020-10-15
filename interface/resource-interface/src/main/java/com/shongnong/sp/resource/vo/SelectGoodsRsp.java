package com.shongnong.sp.resource.vo;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;


import java.util.List;

@DataObject(generateConverter = true)
public class SelectGoodsRsp  {
    public SelectGoodsRsp(JsonObject obj){

        SelectGoodsRspConverter.fromJson(obj, this);
    }
    public JsonObject toJson(){
        JsonObject json = new JsonObject();
        SelectGoodsRspConverter.toJson(this, json);
        return json;
    }
    private List<Goods> goodsList;

    public List<Goods> getGoodsList() {
        return goodsList;
    }

    public void setGoodsList(List<Goods> goodsList) {
        this.goodsList = goodsList;
    }
}
