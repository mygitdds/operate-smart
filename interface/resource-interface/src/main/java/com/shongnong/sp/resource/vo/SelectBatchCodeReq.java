package com.shongnong.sp.resource.vo;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;
import lombok.Data;

@DataObject(generateConverter = true)
@Data
public class SelectBatchCodeReq  {
    public SelectBatchCodeReq(JsonObject obj){

        SelectBatchCodeReqConverter.fromJson(obj, this);
    }
    public JsonObject toJson(){
        JsonObject json = new JsonObject();
        SelectBatchCodeReqConverter.toJson(this, json);
        return json;
    }
    private String requestId;
    private Long enterpriseId;
    private String resourceId;
    private int page;
    private int rows;
}
