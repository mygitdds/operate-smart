package com.shongnong.sp.resource.vo;

import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;

@DataObject(generateConverter = true)
public class CreateResourceReq {
    public CreateResourceReq(JsonObject obj){
        CreateResourceReqConverter.fromJson(obj, this);
    }
    public JsonObject toJson(){
        JsonObject json = new JsonObject();
        CreateResourceReqConverter.toJson(this, json);
        return json;
    }
    private String requestId;
    /**
     * 企业id
     */
    private Long enterpriseId;

    /**
     * 外部资源图片
     */
    private String img;

    /**
     * 外部资源id
     */
    private String resourceId;

    /**
     * 资源名称
     */
    private String name;

    /**
     * 资源类型
     */
    private String type;

    /**
     * 是否开启库存管理
     */
    private String stockManager;

    /**
     * 操作者
     */
    private String operator;

    public Long getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Long enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStockManager() {
        return stockManager;
    }

    public void setStockManager(String stockManager) {
        this.stockManager = stockManager;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }
}
