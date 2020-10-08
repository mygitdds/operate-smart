package com.shongnong.sp.resource.vo;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;
import lombok.Data;

@Data
@DataObject(generateConverter = true)
public class SelectGoodsReq {
    public SelectGoodsReq(JsonObject obj){

        SelectGoodsReqConverter.fromJson(obj, this);
    }
    public JsonObject toJson(){
        JsonObject json = new JsonObject();
        SelectGoodsReqConverter.toJson(this, json);
        return json;
    }
    private String goodName;
    private Long enterpriseId;
    private int page;
    private int rows;
}
