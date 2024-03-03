package org.camposmdev.server;

import io.vertx.core.Vertx;
import io.vertx.core.http.*;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;
import org.camposmdev.server.middleware.Auth;
import org.camposmdev.server.model.WSAgent;
import org.camposmdev.server.model.ClientRegistry;
import org.camposmdev.server.router.GameRouter;
import org.camposmdev.server.router.UserRouter;

public class Launcher {
    static final int PORT = 4000;
    public static void main(String[] args) {
        var v = Vertx.vertx();
        var options = new HttpServerOptions();
        var server = v.createHttpServer(options);
        var mainRouter = Router.router(v);
        mainRouter.route().handler(BodyHandler.create());
        mainRouter.route("/api/*").subRouter(UserRouter.init(v));
        mainRouter.route("/api/*").subRouter(GameRouter.init((v)));
        mainRouter.route("/ws")
                .handler(Auth.verifyToken)
                .handler(Launcher::handleWS);

//        server.webSocketHandler(Client::new);
        server.requestHandler(mainRouter).listen(PORT)
                .onSuccess(e -> System.out.println("Server started on port " + PORT))
                .onFailure(e -> System.out.println(e.getCause().toString()));
    }

    public static void handleWS(RoutingContext c) {
        var cookie = c.request().getCookie("token");
        Auth.parseToken(cookie).onSuccess(userId ->
                c.request().toWebSocket().onSuccess(ws -> {
            var client = new WSAgent(userId, ws);
            ClientRegistry.get().add(client);
        }));
    }
}
