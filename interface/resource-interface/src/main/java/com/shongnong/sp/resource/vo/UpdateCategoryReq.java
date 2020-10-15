package com.shongnong.sp.resource.vo;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;



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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
