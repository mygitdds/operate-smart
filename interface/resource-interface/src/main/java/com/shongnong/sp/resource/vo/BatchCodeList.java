package com.shongnong.sp.resource.vo;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;
import lombok.Data;

import java.util.List;
@DataObject
@Data
public class BatchCodeList extends OperationJson {
    public  BatchCodeList(JsonObject jsonObject){
        this.jsonObject = jsonObject;
    }
    private List<BatchCode> batchCodeList;
}
