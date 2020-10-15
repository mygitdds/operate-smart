package com.shongnong.sp.resource.vo;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;


@DataObject(generateConverter = true)
public class CreateCategoryReq  {
    public CreateCategoryReq(JsonObject obj){
        CreateCategoryReqConverter.fromJson(obj, this);
    }
    public JsonObject toJson(){
        JsonObject json = new JsonObject();
        CreateCategoryReqConverter.toJson(this, json);
        return json;
    }
    private String name;
    private Long pid;
    private Long enterpriseId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public Long getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Long enterpriseId) {
        this.enterpriseId = enterpriseId;
    }
}
