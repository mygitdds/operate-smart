package com.shongnong.sp.resource.vo;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;
import lombok.Data;

@Data
@DataObject
public class CreateGoodsReq extends OperationJson {
    public  CreateGoodsReq(JsonObject jsonObject){
        this.jsonObject = jsonObject;
    }
    private String goodName;
    private Double goodPrice;
    private String img;
    private Long enterpriseId;
    private Long categoryId;
}
