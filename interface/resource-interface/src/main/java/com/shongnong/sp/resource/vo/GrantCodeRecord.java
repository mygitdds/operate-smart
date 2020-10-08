package com.shongnong.sp.resource.vo;
import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;
import lombok.Data;
import lombok.ToString;


@Data
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
}
