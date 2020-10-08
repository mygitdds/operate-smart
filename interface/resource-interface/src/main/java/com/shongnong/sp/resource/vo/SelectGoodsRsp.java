package com.shongnong.sp.resource.vo;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;
import lombok.Data;

import java.util.List;
@Data
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
}
