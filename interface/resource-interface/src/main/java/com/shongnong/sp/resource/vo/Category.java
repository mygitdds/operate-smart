package com.shongnong.sp.resource.vo;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;
import lombok.Data;

@Data
@DataObject(generateConverter = true)
public class Category {
    public Category(JsonObject obj){
        CategoryConverter.fromJson(obj, this);
    }
    public JsonObject toJson(){
        JsonObject json = new JsonObject();
        CategoryConverter.toJson(this, json);
        return json;
    }
    private Long id;
    private String name;
    private Long pid;
    private Long enterpriseId;
    private String insertDate;
    private String updateDate;
}
