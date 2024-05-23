package org.camposmdev.server;

import io.vertx.core.Vertx;
import io.vertx.core.http.*;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;
import org.camposmdev.server.middleware.Auth;
import org.camposmdev.server.model.WSAgent;
import org.camposmdev.server.model.ClientRegistry;
import org.camposmdev.server.router.UserRouter;

public class Launcher {
    static final int PORT = 4000;

    public static void main(String[] args) {
        Vertx v = Vertx.vertx();
        HttpServerOptions options = new HttpServerOptions();
        HttpServer server = v.createHttpServer(options);
        Router apiRouter = Router.router(v);
        apiRouter.route().handler(BodyHandler.create());
        apiRouter.route("/api/*").subRouter(UserRouter.init(v));
        apiRouter.route("/ws")
                .handler(Auth.verifyToken)
                .handler(Launcher::handleWS);
        server.requestHandler(apiRouter).listen(PORT)
                .onSuccess(e -> System.out.println("Server started on port " + PORT))
                .onFailure(e -> System.out.println(e.getCause().toString()));
    }

    public static void handleWS(RoutingContext c) {
        var cookie = c.request().getCookie("token");
        Auth.parseToken(cookie).onSuccess(userId ->
                c.request().toWebSocket().onSuccess(ws -> {
            var client = new WSAgent(userId, ws);
            ClientRegistry.get().add(client);
        })).onFailure(e ->{
            c.response().setStatusCode(400).send();
        });
    }
}
