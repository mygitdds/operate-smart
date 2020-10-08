package com.shongnong.sp.resource.vo;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;
import lombok.Data;

@Data
@DataObject(generateConverter = true)
public class CreateCategoryReq  {
    public CreateCategoryReq(JsonObject obj){
        CreateCategoryReqConverter.fromJson(obj, this);
    }
    public JsonObject toJson(){
        JsonObject json = new JsonObject();
        CreateCategoryReqConverter.toJson(this, json);
        return json;
    }
    private String name;
    private Long pid;
    private Long enterpriseId;
}
