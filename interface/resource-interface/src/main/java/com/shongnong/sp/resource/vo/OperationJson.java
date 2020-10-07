package com.shongnong.sp.resource.vo;

import io.vertx.core.json.JsonObject;

public class OperationJson {
    public JsonObject jsonObject;
    public JsonObject toJson(){
        return jsonObject;
    }

}
