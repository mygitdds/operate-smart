package com.shongnong.sp.resource.vo;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;
import lombok.Data;

@Data
@DataObject
public class SelectGoodsReq extends OperationJson{
    public  SelectGoodsReq(JsonObject jsonObject){
        this.jsonObject = jsonObject;
    }
    private String goodName;
    private Long enterpriseId;
    private int page;
    private int rows;
}
