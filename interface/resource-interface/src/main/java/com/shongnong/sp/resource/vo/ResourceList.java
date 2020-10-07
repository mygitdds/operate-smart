package com.shongnong.sp.resource.vo;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;
import lombok.Data;

import java.util.List;

@DataObject
@Data
public class ResourceList extends OperationJson {
    public ResourceList(JsonObject jsonObject){
        this.jsonObject = jsonObject;
    }
    private List<Resource> resourceList;
}
