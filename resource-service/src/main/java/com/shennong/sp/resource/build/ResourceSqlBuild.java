package com.shennong.sp.resource.build;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.shennong.sp.commom.util.SqlUtil;
import com.shongnong.sp.resource.vo.*;
import io.vertx.core.json.JsonArray;
import org.apache.commons.lang3.StringUtils;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * resource的sql构造
 */
public class ResourceSqlBuild {
    public String createResourceSql(CreateResourceReq resource, JsonArray params){
        String resourceJson = JSONObject.toJSONString(resource);
        JSONObject jsonObject = JSON.parseObject(resourceJson);
        Map<String,String> para = new HashMap<>();
        createParaMap(para);
        String table = "t_smart_resource";
        String sql = SqlUtil.createSql(table,para,jsonObject,params);
        return sql;
    }
    private void createParaMap(Map<String,String> para){
        para.put("enterprise_id","enterpriseId");
        para.put("img","img");
        para.put("resource_id","resourceId");
        para.put("resource_name","resourceName");
        para.put("type","type");
        para.put("stock_manager","stockManager");
        para.put("operator","operator");
    }
    public String selectResourceSql(SelectResourceReq selectResourceReq,JsonArray params){
        StringBuilder sql = new StringBuilder("select id,enterprise_id as enterpriseId ,img,resource_id as resourceId,name,type,stock_manager as stockManager," +
                "operator,total_quantity as totalQuantity,available_quantity AS availableQuantity,foreign_resource_id AS foreignResourceId" +
                ",grant_num as grantNum,invalid_num as invalidNum,verify_num verifyNum,total from ");
        String table = "t_smart_resource";
        params.add(selectResourceReq.getEnterpriseId());
        sql.append(table);
        sql.append(" where enterprise_id = ?  limit ?,?");
        if(StringUtils.isNotEmpty(selectResourceReq.getResourceName())){
            sql.append("and resource_name =? ");
            params.add(selectResourceReq.getResourceName());
        }
        sql.append("limit ?,?");
        params.add(selectResourceReq.getPage());
        params.add(selectResourceReq.getRows());
        return sql.toString();
    }
    public String createBatchCodeSql(BatchCode batch,JsonArray params){
        Map<String, String> codePara = new HashMap<>();
        codePara.put("resource_id", "resourceId");
        codePara.put("verify_type", "verifyType");
        codePara.put("claim_rules", "claimRules");
        codePara.put("code_number", "codeNumber");
        codePara.put("type", "type");
        codePara.put("grant_type", "grantType");
        codePara.put("brush_count", "brushCount");
        batch.setClaimRulesString(batch.getClaimRules().toString());
        String codeJson = JSONObject.toJSONString(batch);
        JSONObject jsonObject = JSON.parseObject(codeJson);
        String table = "t_smart_resource";
        return SqlUtil.createSql(table, codePara, jsonObject, params);
    }

    public String selectBatchCode(SelectBatchCodeReq selectBatchCodeReq,JsonArray params){
        StringBuilder sql = new StringBuilder();
        sql.append("select id,resource_id as resourceId,verify_type as verifyType,claim_rules as claimRulesString,code_number as codeNumber ,type,grant_type as grantType,brush_count as brushCount from t_smart_batch_code ");
        sql.append("where enterprise_id=? and resource_id = ? limit ?,?");
        params.add(selectBatchCodeReq.getEnterpriseId());
        params.add(selectBatchCodeReq.getResourceId());
        params.add(selectBatchCodeReq.getPage());
        params.add(selectBatchCodeReq.getRows());
        return sql.toString();
    }
    //获取主资源id
    public String getResourceId(GrantCodeRecord grantCodeRecord,JsonArray params){
        StringBuilder sql = new StringBuilder();
        sql.append("select id from t_smart_resource where resource_id = ? limit 0,1");
        params.add(grantCodeRecord.getPrizeId());
        return sql.toString();
    }

    //根据主资源id获取卷码
    public String getCode(Long id,Long enterpriseId,JsonArray params){
        StringBuilder sql = new StringBuilder();
        sql.append("select code from t_smart_code where enterprise_id =? and resource_id = ? order by insert_time limit 0,100");
        return sql.toString();
    }

    //t_smart_resource表计数
    public String resourceCount(Long id,int number,String type,JsonArray params){
        StringBuilder sql = new StringBuilder();
        sql.append("update t_smart_resource set ");
        switch (type){
            case "grantNum":
                sql.append("grant_num = grant_num +"+number);
                break;
            case "invalidNum":
                sql.append("invalid_num = invalid_num +"+number);
                break;
            case "verifyNum":
                sql.append("verify_num = verify_num +"+number);
                break;
            case "total":
                sql.append("total = total +"+number);
        }
        sql.append(" where id =?");
        params.add(id);
        return sql.toString();
    }
    //t_smart_batch_code表计数
    public String batchCodeCount(Long id,int number,String type,JsonArray params){
        StringBuilder sql = new StringBuilder();
        sql.append("update t_smart_batch_code set ");
        switch (type){
            case "grantNum":
                sql.append("grant_num = grant_num +"+number);
                break;
            case "invalidNum":
                sql.append("invalid_num = invalid_num +"+number);
                break;
            case "verifyNum":
                sql.append("verify_num = verify_num +"+number);
                break;
        }
        sql.append(" where id =?");
        params.add(id);
        return sql.toString();
    }
    //批量新增code
    public String createBatchCode(BatchCode batch, List<JsonArray> params){
        List<JsonArray> paramsList = params;
        StringBuilder sql = new StringBuilder();
        sql.append("insert into t_smart_code (batch_code_id,enterprise_id,resource_id,code,code_status) values(?,?,?,?,?)");
        int number = batch.getCodeNumber();
        for(int i=0;i<number;i++){
            JsonArray jsonArray = new JsonArray();
            jsonArray.add(batch.getId());
            String uuid = UUID.randomUUID().toString().trim().replaceAll("-", "");
            jsonArray.add(batch.getEnterpriseId());
            jsonArray.add(batch.getResourceId());
            jsonArray.add(uuid);
            jsonArray.add(0);
            paramsList.add(jsonArray);
        }
       return  sql.toString();
    }
    //

}
