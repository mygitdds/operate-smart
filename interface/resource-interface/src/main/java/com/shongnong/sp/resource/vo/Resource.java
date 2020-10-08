package com.shongnong.sp.resource.vo;
import io.vertx.codegen.annotations.DataObject;
import io.vertx.core.json.JsonObject;
import lombok.Data;

/***
 *
 */
@DataObject(generateConverter = true)
@Data
public class Resource {

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
}
