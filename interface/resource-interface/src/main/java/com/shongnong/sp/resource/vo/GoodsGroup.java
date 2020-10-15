package com.shongnong.sp.resource.vo;

import java.util.List;

public class GoodsGroup {
    private Long id;
    private Long enterpriseId;
    private List<Long> listGroup;
    private Double price;
    private String insertDate;
    private String updateDate;

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

    public List<Long> getListGroup() {
        return listGroup;
    }

    public void setListGroup(List<Long> listGroup) {
        this.listGroup = listGroup;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
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
