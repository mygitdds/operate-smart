package com.shongnong.sp.resource.vo;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;
import lombok.Data;

@DataObject
@Data
public class SelectBatchCodeReq extends OperationJson {
    public  SelectBatchCodeReq(JsonObject jsonObject){
        this.jsonObject = jsonObject;
    }
    private Long enterpriseId;
    private String resourceName;
    private int page;
    private int rows;
}
