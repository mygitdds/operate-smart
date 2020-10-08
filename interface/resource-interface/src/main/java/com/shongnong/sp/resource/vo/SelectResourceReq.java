package com.shongnong.sp.resource.vo;
import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;
import lombok.Data;

@Data
@DataObject(generateConverter = true)
public class SelectResourceReq  {
    public SelectResourceReq(JsonObject obj){

        SelectResourceReqConverter.fromJson(obj, this);
    }
    public JsonObject toJson(){
        JsonObject json = new JsonObject();
        SelectResourceReqConverter.toJson(this, json);
        return json;
    }
    private int page;
    private int rows;
    private Long enterpriseId;
    //可根据resourceName进行模糊搜索
    private String resourceName;

}
