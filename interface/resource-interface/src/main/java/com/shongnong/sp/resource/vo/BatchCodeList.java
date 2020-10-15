package com.shongnong.sp.resource.vo;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;


import java.util.List;
@DataObject(generateConverter = true)
public class BatchCodeList {
    public BatchCodeList(JsonObject obj){

        BatchCodeListConverter.fromJson(obj, this);
    }
    public JsonObject toJson(){
        JsonObject json = new JsonObject();
        BatchCodeListConverter.toJson(this, json);
        return json;
    }
    private List<BatchCode> batchCodeList;

    public List<BatchCode> getBatchCodeList() {
        return batchCodeList;
    }

    public void setBatchCodeList(List<BatchCode> batchCodeList) {
        this.batchCodeList = batchCodeList;
    }
}
