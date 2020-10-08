package com.shongnong.sp.resource.vo;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;
import lombok.Data;

@Data
@DataObject(generateConverter = true)
public class UpdateGoodsReq {
    public UpdateGoodsReq(JsonObject obj){
        UpdateGoodsReqConverter.fromJson(obj, this);
    }
    public JsonObject toJson(){
        JsonObject json = new JsonObject();
        UpdateGoodsReqConverter.toJson(this, json);
        return json;
    }
    private String goodName;
    private Double goodPrice;
    private String img;
    private Long enterpriseId;
}
