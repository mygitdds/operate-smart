package com.shongnong.sp.resource.vo;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;



@DataObject(generateConverter = true)
public class SelectGoodsReq {
    public SelectGoodsReq(JsonObject obj){

        SelectGoodsReqConverter.fromJson(obj, this);
    }
    public JsonObject toJson(){
        JsonObject json = new JsonObject();
        SelectGoodsReqConverter.toJson(this, json);
        return json;
    }
    private String goodName;
    private Long enterpriseId;
    private int page;
    private int rows;

    public String getGoodName() {
        return goodName;
    }

    public void setGoodName(String goodName) {
        this.goodName = goodName;
    }

    public Long getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Long enterpriseId) {
        this.enterpriseId = enterpriseId;
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
