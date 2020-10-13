package com.shennong.sp.commom.http;

import com.alibaba.fastjson.JSON;

/**
 * 相应结果的封装
 */

public class HttpResponseEntity {
    private int code;
    private String msg;
    private Object object;

    public static String suss(Object object){
        HttpResponseEntity httpResponseEntity = new HttpResponseEntity();
        httpResponseEntity.setCode(1001);
        httpResponseEntity.setMsg("访问成功");
        httpResponseEntity.setObject(object);
        return JSON.toJSONString(httpResponseEntity);
    }

    public static String suss(){
        HttpResponseEntity httpResponseEntity = new HttpResponseEntity();
        httpResponseEntity.setCode(1001);
        httpResponseEntity.setMsg("访问成功");
        return JSON.toJSONString(httpResponseEntity);
    }

    public static String fail(String msg){
        HttpResponseEntity httpResponseEntity = new HttpResponseEntity();
        httpResponseEntity.setCode(1002);
        httpResponseEntity.setMsg(msg);
        return JSON.toJSONString(httpResponseEntity);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
}
