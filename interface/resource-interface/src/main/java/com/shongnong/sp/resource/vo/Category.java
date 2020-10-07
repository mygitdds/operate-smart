package com.shongnong.sp.resource.vo;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;
import lombok.Data;

@Data
@DataObject
public class Category extends OperationJson {
    public  Category(JsonObject jsonObject){
        this.jsonObject = jsonObject;
    }
    private Long id;
    private String name;
    private Long pid;
    private Long enterpriseId;
    private String insertDate;
    private String updateDate;
}
