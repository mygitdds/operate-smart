package com.shongnong.sp.resource.vo;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;
import lombok.Data;

import java.util.List;
@Data
@DataObject
public class SelectBatchCodeRsp extends OperationJson {
    public  SelectBatchCodeRsp(JsonObject jsonObject){
        this.jsonObject = jsonObject;
    }
    private List<BatchCode> batchCodeList;
}
