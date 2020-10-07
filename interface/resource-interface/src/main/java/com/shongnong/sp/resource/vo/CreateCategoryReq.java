package com.shongnong.sp.resource.vo;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;
import lombok.Data;

@Data
@DataObject
public class CreateCategoryReq extends OperationJson {
    public  CreateCategoryReq(JsonObject jsonObject){
        this.jsonObject = jsonObject;
    }
    private String name;
    private Long pid;
    private Long enterpriseId;
}
