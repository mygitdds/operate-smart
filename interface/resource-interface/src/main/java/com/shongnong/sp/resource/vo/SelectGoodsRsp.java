package com.shongnong.sp.resource.vo;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;
import lombok.Data;

import java.util.List;
@Data
@DataObject
public class SelectGoodsRsp extends OperationJson {
    public  SelectGoodsRsp(JsonObject jsonObject){
        super.jsonObject = jsonObject;
    }
    private List<Goods> goodsList;
}
