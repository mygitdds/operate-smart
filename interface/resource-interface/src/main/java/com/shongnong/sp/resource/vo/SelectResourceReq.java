package com.shongnong.sp.resource.vo;
import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;



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
    private String requestId;
    private int page;
    private int rows;
    private Long enterpriseId;
    //可根据resourceName进行模糊搜索
    private String resourceName;

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public Long getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Long enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }
}
