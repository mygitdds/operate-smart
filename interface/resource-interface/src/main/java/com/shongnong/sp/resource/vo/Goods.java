package com.shongnong.sp.resource.vo;
import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;
import lombok.Data;

@Data
@DataObject(generateConverter = true)
public class Goods  {
    public Goods(JsonObject obj){

        GoodsConverter.fromJson(obj, this);
    }
    public JsonObject toJson(){
        JsonObject json = new JsonObject();
        GoodsConverter.toJson(this, json);
        return json;
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
