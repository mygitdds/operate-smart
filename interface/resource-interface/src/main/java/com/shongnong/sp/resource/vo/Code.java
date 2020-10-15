package com.shongnong.sp.resource.vo;

import lombok.ToString;

import java.util.Date;

@ToString
public class Code {

    /**
     * 自增id
     */
    private Long id;

    /**
     * 批次表id
     */
    private Long batchCodeId;

    /**
     * 企业id
     */
    private Long enterpriseId;

    /**
     * 被扫的卷码
     */
    private String code;

    /**
     * 卷码状态 1,已发放，2已核销，3无效
     */
    private String codeStatus;

    /**
     * 核销时间
     */
    private String verifyTime;

    /**
     * 插入时间
     */
    private Date insertTime;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 版本号
     */
    private int edition;
    //操作者
    private String executor;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBatchCodeId() {
        return batchCodeId;
    }

    public void setBatchCodeId(Long batchCodeId) {
        this.batchCodeId = batchCodeId;
    }

    public Long getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Long enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCodeStatus() {
        return codeStatus;
    }

    public void setCodeStatus(String codeStatus) {
        this.codeStatus = codeStatus;
    }

    public String getVerifyTime() {
        return verifyTime;
    }

    public void setVerifyTime(String verifyTime) {
        this.verifyTime = verifyTime;
    }

    public Date getInsertTime() {
        return insertTime;
    }

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public int getEdition() {
        return edition;
    }

    public void setEdition(int edition) {
        this.edition = edition;
    }

    public String getExecutor() {
        return executor;
    }

    public void setExecutor(String executor) {
        this.executor = executor;
    }
}
