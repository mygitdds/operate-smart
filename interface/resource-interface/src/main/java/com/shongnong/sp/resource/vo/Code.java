package com.shongnong.sp.resource.vo;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
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

}
