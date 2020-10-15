package com.shongnong.sp.resource.vo;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;


@DataObject(generateConverter = true)
public class SelectBatchCodeReq  {
    public SelectBatchCodeReq(JsonObject obj){

        SelectBatchCodeReqConverter.fromJson(obj, this);
    }
    public JsonObject toJson(){
        JsonObject json = new JsonObject();
        SelectBatchCodeReqConverter.toJson(this, json);
        return json;
    }
    private String requestId;
    private Long enterpriseId;
    private String resourceId;
    private int page;
    private int rows;

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public Long getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Long enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
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
}
