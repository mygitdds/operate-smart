package com.shongnong.sp.resource.vo;
import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;


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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public String getInsertDate() {
        return insertDate;
    }

    public void setInsertDate(String insertDate) {
        this.insertDate = insertDate;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }
}
