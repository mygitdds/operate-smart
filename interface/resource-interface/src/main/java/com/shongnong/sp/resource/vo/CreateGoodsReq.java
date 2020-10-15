package com.shongnong.sp.resource.vo;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;


@DataObject(generateConverter = true)
public class CreateGoodsReq {
    public CreateGoodsReq(JsonObject obj){

        CreateGoodsReqConverter.fromJson(obj, this);
    }
    public JsonObject toJson(){
        JsonObject json = new JsonObject();
        CreateGoodsReqConverter.toJson(this, json);
        return json;
    }
    private String goodName;
    private Double goodPrice;
    private String img;
    private Long enterpriseId;
    private Long categoryId;

    public String getGoodName() {
        return goodName;
    }

    public void setGoodName(String goodName) {
        this.goodName = goodName;
    }

    public Double getGoodPrice() {
        return goodPrice;
    }

    public void setGoodPrice(Double goodPrice) {
        this.goodPrice = goodPrice;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Long getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Long enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }
}
