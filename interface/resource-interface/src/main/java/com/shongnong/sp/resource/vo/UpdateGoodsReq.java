package com.shongnong.sp.resource.vo;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;
import lombok.Data;

@Data
@DataObject
public class UpdateGoodsReq extends OperationJson {
    public  UpdateGoodsReq(JsonObject jsonObject){
        this.jsonObject = jsonObject;
    }
    private String goodName;
    private Double goodPrice;
    private String img;
    private Long enterpriseId;
}
