package com.shongnong.sp.resource.vo;
import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;
import lombok.Data;

@Data
@DataObject
public class Goods extends OperationJson {
    public  Goods(JsonObject jsonObject){
        this.jsonObject = jsonObject;
    }
    private Long id;
    private String goodName;
    private Double goodPrice;
    private String img;
    private Long enterpriseId;
    private Long categoryId;
    private String insertDate;
    private String updateDate;
}
