package com.shennong.sp.sass_web;

import com.shennong.sp.sass_web.handler.resource.ResourceHandler;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.Route;
import io.vertx.ext.web.Router;

public class MainVerticle extends AbstractVerticle {

  @Override
  public void start(Promise<Void> startPromise) throws Exception {
    ResourceHandler resourceHandler = new ResourceHandler();
    HttpServer server = vertx.createHttpServer();
    Router router = Router.router(vertx);
    resourceHandler.init(router);

    server.requestHandler(router).listen(8080);
  }
}
