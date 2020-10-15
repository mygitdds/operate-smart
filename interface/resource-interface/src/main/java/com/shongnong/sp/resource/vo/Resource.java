package com.shongnong.sp.resource.vo;
import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;


/***
 *
 */
@DataObject(generateConverter = true)
public class Resource {

    public Resource(){
    }

    public Resource(JsonObject obj){

        ResourceConverter.fromJson(obj, this);
    }
    public JsonObject toJson(){
        JsonObject json = new JsonObject();
        ResourceConverter.toJson(this, json);
        return json;
    }
    /**
     * 自增id
     */
    private Long id;

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

    /**
     * 总数量
     */
    private int totalQuantity;

    /**
     * 剩余数量
     */
    private int availableQuantity;

    private String foreignResourceId;
    //插入时间
    private String insertTime;

    private Integer grantNum;
    private Integer invalidNum;
    private Integer verifyNum;
    private Integer total;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public int getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(int totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public int getAvailableQuantity() {
        return availableQuantity;
    }

    public void setAvailableQuantity(int availableQuantity) {
        this.availableQuantity = availableQuantity;
    }

    public String getForeignResourceId() {
        return foreignResourceId;
    }

    public void setForeignResourceId(String foreignResourceId) {
        this.foreignResourceId = foreignResourceId;
    }

    public String getInsertTime() {
        return insertTime;
    }

    public void setInsertTime(String insertTime) {
        this.insertTime = insertTime;
    }

    public Integer getGrantNum() {
        return grantNum;
    }

    public void setGrantNum(Integer grantNum) {
        this.grantNum = grantNum;
    }

    public Integer getInvalidNum() {
        return invalidNum;
    }

    public void setInvalidNum(Integer invalidNum) {
        this.invalidNum = invalidNum;
    }

    public Integer getVerifyNum() {
        return verifyNum;
    }

    public void setVerifyNum(Integer verifyNum) {
        this.verifyNum = verifyNum;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }
}
