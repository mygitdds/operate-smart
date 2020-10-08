package com.shongnong.sp.resource.vo;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;
import lombok.Data;

import java.util.List;

@DataObject(generateConverter = true)
@Data
public class ResourceList {
    public ResourceList(JsonObject obj){

        ResourceListConverter.fromJson(obj, this);
    }
    public JsonObject toJson(){
        JsonObject json = new JsonObject();
        ResourceListConverter.toJson(this, json);
        return json;
    }
    private List<Resource> resourceList;
}
