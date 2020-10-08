package com.shongnong.sp.resource.vo;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;
import lombok.Data;

import java.util.List;
@Data
@DataObject(generateConverter = true)
public class SelectGoodsCategoryRsp {
    public SelectGoodsCategoryRsp(JsonObject obj){

        SelectGoodsCategoryRspConverter.fromJson(obj, this);
    }
    public JsonObject toJson(){
        JsonObject json = new JsonObject();
        SelectGoodsCategoryRspConverter.toJson(this, json);
        return json;
    }
    private List<Category> categoryList;
}
