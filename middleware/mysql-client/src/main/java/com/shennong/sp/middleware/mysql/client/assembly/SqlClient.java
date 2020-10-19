package com.shennong.sp.middleware.mysql.client.assembly;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.asyncsql.MySQLClient;
import io.vertx.ext.sql.SQLClient;
import java.util.HashMap;
import java.util.Map;

public class SqlClient {
    private  Map<String,SQLClient> sqlClientMap = new HashMap<>();
    public SqlClient(Vertx vertx, JSONArray dataSource) {
        builderSqlClient(dataSource,vertx);
    }

    //装client的map
    public void builderSqlClient(JSONArray dataSource,Vertx vertx){
        //拿到vert.x
        //解析出一个对象循环创建
        for(int i=0;i<dataSource.size();i++){
            JSONObject jsonObject =dataSource.getJSONObject(i);
            JsonObject mySQLClientConfig = new JsonObject().put("host", jsonObject.get("host"))
                    .put("port",jsonObject.get("port"))
                    .put("host",jsonObject.get("host"))
                    .put("username",jsonObject.get("username"))
                    .put("password",jsonObject.get("password"))
                    .put("database",jsonObject.get("database"))
                    .put("maxPoolSize",jsonObject.get("maxPoolSize"))
                    .put("connectTimeout",jsonObject.get("connectTimeout"))
                    .put("queryTimeout",jsonObject.get("queryTimeout"));
            SQLClient mySQLClient = MySQLClient.createShared(vertx, mySQLClientConfig);
            sqlClientMap.put(jsonObject.getString("database"),mySQLClient);
        }
    }


    public  SQLClient getSqlClient(String database){
        SQLClient client =sqlClientMap.get(database);
        if(client != null){
            System.out.println("不是空的");
        }else {
            System.out.println("是空的");
        }

        return sqlClientMap.get(database);
    }
}
