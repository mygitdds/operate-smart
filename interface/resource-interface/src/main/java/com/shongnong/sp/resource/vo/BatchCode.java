package com.shongnong.sp.resource.vo;
import com.alibaba.fastjson.JSONObject;
import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;


@DataObject(generateConverter = true)
public class BatchCode {

    public BatchCode(){

    }
    public BatchCode(JsonObject obj){

        BatchCodeConverter.fromJson(obj, this);
    }
    public JsonObject toJson(){
        JsonObject json = new JsonObject();
        BatchCodeConverter.toJson(this, json);
        return json;
    }

    /**
     * 自增id
     */
    private Long id;

    /**
     * 主资源id
     */
    private Long resourceId;

    /**
     * 核销类型 consumer,terminal
     */
    private String verifyType;

    /**
     * 规则-采用json
     */
    private JSONObject  claimRules;

    /**
     * 卷码数量
     */
    private Integer codeNumber;

    /**
     * 发放方式 api,export
     */
    private String grantType;

    /**
     * 防刷次数
     */
    private Integer brushCount;
    /**
     * 锁定时间
     */
    private Integer lockTime;

    private Integer grantNum;
    private Integer invalidNum;
    private Integer verifyNum;

    private Long enterpriseId;

    private String claimRulesString;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getResourceId() {
        return resourceId;
    }

    public void setResourceId(Long resourceId) {
        this.resourceId = resourceId;
    }

    public String getVerifyType() {
        return verifyType;
    }

    public void setVerifyType(String verifyType) {
        this.verifyType = verifyType;
    }

    public JSONObject getClaimRules() {
        return claimRules;
    }

    public void setClaimRules(JSONObject claimRules) {
        this.claimRules = claimRules;
    }

    public Integer getCodeNumber() {
        return codeNumber;
    }

    public void setCodeNumber(Integer codeNumber) {
        this.codeNumber = codeNumber;
    }

    public String getGrantType() {
        return grantType;
    }

    public void setGrantType(String grantType) {
        this.grantType = grantType;
    }

    public Integer getBrushCount() {
        return brushCount;
    }

    public void setBrushCount(Integer brushCount) {
        this.brushCount = brushCount;
    }

    public Integer getLockTime() {
        return lockTime;
    }

    public void setLockTime(Integer lockTime) {
        this.lockTime = lockTime;
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

    public Long getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Long enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public String getClaimRulesString() {
        return claimRulesString;
    }

    public void setClaimRulesString(String claimRulesString) {
        this.claimRulesString = claimRulesString;
    }
}
