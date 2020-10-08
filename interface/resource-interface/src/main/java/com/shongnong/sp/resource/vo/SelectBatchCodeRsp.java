package com.shongnong.sp.resource.vo;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;
import lombok.Data;

import java.util.List;
@Data
@DataObject(generateConverter = true)
public class SelectBatchCodeRsp {
    public SelectBatchCodeRsp(JsonObject obj){

        SelectBatchCodeRspConverter.fromJson(obj, this);
    }
    public JsonObject toJson(){
        JsonObject json = new JsonObject();
        SelectBatchCodeRspConverter.toJson(this, json);
        return json;
    }
    private List<BatchCode> batchCodeList;
}
