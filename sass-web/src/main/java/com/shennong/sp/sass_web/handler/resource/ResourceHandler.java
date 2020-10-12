package com.shennong.sp.sass_web.handler.resource;

import io.vertx.ext.web.Router;
import io.vertx.ext.web.Session;

public class ResourceHandler  {
    private Router router;
    public void init(Router router){
        this.router = router;
        createResource();
    }
    public void createResource(){
        router.route("/somepath/blah/").handler(routingContext -> {
            Session session = routingContext.session();

        });

    }
}
