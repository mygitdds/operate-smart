package com.shongnong.sp.resource.vo;

import lombok.Data;

@Data
public class CheckCodeModel {
    private String code;
    private String updateTime;
    private String claimRules;
}
