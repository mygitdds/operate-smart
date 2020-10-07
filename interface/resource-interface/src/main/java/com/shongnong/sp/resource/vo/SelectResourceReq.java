package com.shongnong.sp.resource.vo;
import io.vertx.core.json.JsonObject;
import lombok.Data;

@Data
public class SelectResourceReq extends OperationJson {
    public  SelectResourceReq(JsonObject jsonObject){
        this.jsonObject = jsonObject;
    }
    private int page;
    private int rows;
    private Long enterprise;
    //可根据resourceName进行模糊搜索
    private String resourceName;

}
