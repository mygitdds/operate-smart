package com.shennong.sp.commom.config;

import com.alibaba.fastjson.JSONObject;

public class ConsulConfig {
    private  JSONObject jsonObject;

    private static ConsulConfig consulConfig;

    private ConsulConfig(){

    }

    public static ConsulConfig getConsulConfig(){
        if(consulConfig == null){
            consulConfig = new ConsulConfig();
        }
        return consulConfig;
    }


    public  void setJsonObject(JSONObject jsonObject){
        this.jsonObject = jsonObject;
    }

    public  JSONObject getJsonObject(){
        return jsonObject;
    }
}
