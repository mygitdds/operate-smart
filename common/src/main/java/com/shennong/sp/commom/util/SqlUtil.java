package com.shennong.sp.commom.util;

import com.alibaba.fastjson.JSONObject;
import io.vertx.core.json.JsonArray;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
/**
 * SqlUtil
 * 类作用：生成sql
 * @author dds-Swallow_Birds_000001
 * @date 2020/10/7
 */
public class SqlUtil {
    //新增
    public static String createSql(String table ,Map<String,String> map, JSONObject jSONObject, JsonArray params){
        //遍历map
        StringBuilder sql = new StringBuilder("insert into ")
                .append(table)
                .append(" ( ");
        Map<String,String> newMap = map;
        JSONObject newJSONObject = jSONObject;
        List<String> list = new ArrayList<>(newMap.keySet());
        int count =0;
        for(int i=0;i<list.size();i++){
            String k = list.get(i);
            Object object = newJSONObject.get(newMap.get(k));
            if(object != null){
                count++;
                sql.append(k);
                params.add(object);
                if(i == list.size()-1){
                    sql.append(", ");
                }else {
                    sql.append(") ");
                }
            }
        }
        sql.append("values (");
        for(int i=1;i<=count;i++){
            sql.append("?");
            if(i==count){
                sql.append(")");
            }else {
                sql.append(", ");
            }
        }
        return sql.toString();
    }

}
