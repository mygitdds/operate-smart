package com.shennong.sp.resource.check;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.shennong.sp.middleware.mysql.service.SqlService;
import com.shennong.sp.resource.build.ResourceSqlBuild;
import com.shennong.sp.resource.common.Constant;
import com.shongnong.sp.resource.vo.CheckCodeModel;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonArray;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.core.shareddata.AsyncMap;
import io.vertx.core.spi.cluster.ClusterManager;


public class CodeCheckEffective {

    private Vertx vertx;
    private SqlService sqlService;
    private ResourceSqlBuild resourceSqlBuild;
    private Logger logger = LoggerFactory.getLogger(CodeCheckEffective.class);
    private ClusterManager mgr;
    //check，code是否在有效时间段内
    public CodeCheckEffective(Vertx vertx,SqlService sqlService,ResourceSqlBuild resourceSqlBuild,ClusterManager mgr){
        this.vertx=vertx;
        this.sqlService = sqlService;
        this.resourceSqlBuild = resourceSqlBuild;
        this.mgr = mgr;
    }
    //类似redis上锁

    public boolean checkCode(String code){
        //根据code去查询
        JsonArray params = new JsonArray();
        String sql = resourceSqlBuild.getCode(code,params);
        sqlService.selectList(params,sql, Constant.RESOURCE_DB,result->{
            if (result.succeeded()) {
                //获取结果
                JsonArray codeNumber = result.result();
                if(!codeNumber.isEmpty()){
                    //查询到结果//继续验证卷码是否在有效期
                    CheckCodeModel checkCodeModel = JSON.parseObject(codeNumber.toString(),CheckCodeModel.class);

                    //拿到一把锁

                }

            }
        });
        return false;
    }


}
