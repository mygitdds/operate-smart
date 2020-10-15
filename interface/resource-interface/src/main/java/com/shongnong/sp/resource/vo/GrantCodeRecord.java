package com.shongnong.sp.resource.vo;
import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;


@DataObject(generateConverter = true)
public class GrantCodeRecord {
    public GrantCodeRecord(JsonObject obj){

        GrantCodeRecordConverter.fromJson(obj, this);
    }
    public JsonObject toJson(){
        JsonObject json = new JsonObject();
        GrantCodeRecordConverter.toJson(this, json);
        return json;
    }
    /**
     * 自增id
     */
    private Long id;

    /**
     * 卷码id
     */
    private Long codeId;

    /**
     * 中奖标识
     */
    private String winningNumber;

    /**
     * 奖品id
     */
    private String prizeId;

    /**
     * 中奖C端用户的openid
     */
    private String openId;

    /**
     * 中奖C端用户的手机号码
     */
    private String phonenumber;

    /**
     * 兑奖类型
     */
    private String prizeType;

    /**
     * 活动id
     */
    private String activityId;

    /**
     * 业务方：例如uma
     */
    private String businessParty;

    /**
     * 发放原因
     */
    private String reason;

    /**
     * 核销时间
     */
    private String verifyTime;

    /**
     * 核销资源名称
     */
    private String resourceName;

    /**
     * 被扫的卷码
     */
    private String code;

    /**
     * 核销员的名称
     */
    private String examineName;

    /**
     * 核销员的手机号码
     */
    private String examinePhone;

    /**
     * 领取人手机号码
     */
    private String cashprizePhone;

    private Long enterpriseId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCodeId() {
        return codeId;
    }

    public void setCodeId(Long codeId) {
        this.codeId = codeId;
    }

    public String getWinningNumber() {
        return winningNumber;
    }

    public void setWinningNumber(String winningNumber) {
        this.winningNumber = winningNumber;
    }

    public String getPrizeId() {
        return prizeId;
    }

    public void setPrizeId(String prizeId) {
        this.prizeId = prizeId;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getPrizeType() {
        return prizeType;
    }

    public void setPrizeType(String prizeType) {
        this.prizeType = prizeType;
    }

    public String getActivityId() {
        return activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

    public String getBusinessParty() {
        return businessParty;
    }

    public void setBusinessParty(String businessParty) {
        this.businessParty = businessParty;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getVerifyTime() {
        return verifyTime;
    }

    public void setVerifyTime(String verifyTime) {
        this.verifyTime = verifyTime;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getExamineName() {
        return examineName;
    }

    public void setExamineName(String examineName) {
        this.examineName = examineName;
    }

    public String getExaminePhone() {
        return examinePhone;
    }

    public void setExaminePhone(String examinePhone) {
        this.examinePhone = examinePhone;
    }

    public String getCashprizePhone() {
        return cashprizePhone;
    }

    public void setCashprizePhone(String cashprizePhone) {
        this.cashprizePhone = cashprizePhone;
    }

    public Long getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Long enterpriseId) {
        this.enterpriseId = enterpriseId;
    }
}
