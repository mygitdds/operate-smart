package com.shongnong.sp.resource.vo;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;
import lombok.Data;

@Data
@DataObject(generateConverter = true)
public class UpdateCategoryReq  {
    public UpdateCategoryReq(JsonObject obj){

        UpdateCategoryReqConverter.fromJson(obj, this);
    }
    public JsonObject toJson(){
        JsonObject json = new JsonObject();
        UpdateCategoryReqConverter.toJson(this, json);
        return json;
    }
    private String name;
    private Long id;

}
