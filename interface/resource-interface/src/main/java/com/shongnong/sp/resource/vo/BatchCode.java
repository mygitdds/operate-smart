package com.shongnong.sp.resource.vo;
import com.alibaba.fastjson.JSONObject;
import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;
import lombok.Data;


@Data
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

}
