package com.shennong.sp.middleware.config.client.common;

import io.vertx.core.json.JsonObject;
import io.vertx.serviceproxy.ServiceException;

public class ConsoulException extends ServiceException {

    public ConsoulException(int failureCode, String message) {
        super(failureCode, message);
    }

    public ConsoulException(int failureCode, String message, JsonObject debugInfo) {
        super(failureCode, message, debugInfo);
    }
}
