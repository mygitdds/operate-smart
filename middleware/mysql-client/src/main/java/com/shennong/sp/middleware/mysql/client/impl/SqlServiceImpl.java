package com.shennong.sp.middleware.mysql.client.impl;
import com.shennong.sp.middleware.mysql.client.assembly.SqlClient;
import com.shennong.sp.middleware.mysql.service.SqlService;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.sql.ResultSet;
import io.vertx.ext.sql.SQLClient;
import io.vertx.ext.sql.SQLConnection;
import java.util.List;

public class SqlServiceImpl implements SqlService {

    @Override
    public void selectList(JsonArray params, String sql, String database, Handler<AsyncResult<List<JsonObject>>> resultHandler) {
        SQLClient sqlClient =SqlClient.getInstance().getSqlClient(database);
        sqlClient.getConnection(res -> {
            if (res.succeeded()) {
                SQLConnection connection = res.result();
                connection.queryWithParams(sql, params, result -> {
                    if (result.succeeded()) {
                        // Get the result set
                        ResultSet resultSet = result.result();
                        resultHandler.handle(Future.succeededFuture(resultSet.getRows()));
                    } else {
                        // Failed!
                        resultHandler.handle(Future.failedFuture(result.cause()));
                    }
                });
            } else {
                resultHandler.handle(Future.failedFuture(res.cause()));
            }
        });
    }

    @Override
    public void delete(JsonArray params, String sql, String database, Handler<AsyncResult<Void>> resultHandler) {
        SQLClient sqlClient =SqlClient.getInstance().getSqlClient(database);
        sqlClient.getConnection(res -> {
            SQLConnection connection = res.result();
            if (res.succeeded()) {
                connection.updateWithParams(sql, params, result -> {
                    if (result.succeeded()) {
                        resultHandler.handle(Future.succeededFuture());
                    } else {
                        // Failed!
                        resultHandler.handle(Future.failedFuture(result.cause()));
                    }
                });
            } else {
                resultHandler.handle(Future.failedFuture(res.cause()));
            }
        });
    }

    @Override
    public void update(JsonArray params, String sql, String database, Handler<AsyncResult<Void>> resultHandler) {
        SQLClient sqlClient =SqlClient.getInstance().getSqlClient(database);
        sqlClient.getConnection(res -> {
            SQLConnection connection = res.result();
            if (res.succeeded()) {
                connection.updateWithParams(sql, params, result -> {
                    if (result.succeeded()) {
                        resultHandler.handle(Future.succeededFuture());
                    } else {
                        // Failed!
                        resultHandler.handle(Future.failedFuture(result.cause()));
                    }
                });
            } else {
                resultHandler.handle(Future.failedFuture(res.cause()));
            }
        });
    }

    @Override
    public void insert(JsonArray params, String sql, String database, Handler<AsyncResult<Void>> resultHandler) {
        SQLClient sqlClient =SqlClient.getInstance().getSqlClient(database);
        sqlClient.getConnection(res -> {
            SQLConnection connection = res.result();
            if (res.succeeded()) {
                connection.updateWithParams(sql, params, result -> {
                    if (result.succeeded()) {
                        resultHandler.handle(Future.succeededFuture());
                    } else {
                        // Failed!
                        resultHandler.handle(Future.failedFuture(result.cause()));
                    }
                });
            } else {
                resultHandler.handle(Future.failedFuture(res.cause()));
            }
        });
    }

    @Override
    public void batchOperation(List<JsonArray> params, String sql, String database, Handler<AsyncResult<Void>> resultHandler) {
        SQLClient sqlClient =SqlClient.getInstance().getSqlClient(database);
        sqlClient.getConnection(res -> {
            SQLConnection connection = res.result();
            if (res.succeeded()) {
                connection.batchWithParams(sql, params, result -> {
                    if (result.succeeded()) {
                        resultHandler.handle(Future.succeededFuture());
                    } else {
                        // Failed!
                        resultHandler.handle(Future.failedFuture(result.cause()));
                    }
                });
            } else {
                resultHandler.handle(Future.failedFuture(res.cause()));
            }
        });
    }
}
