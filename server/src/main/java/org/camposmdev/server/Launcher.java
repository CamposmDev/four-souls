package org.camposmdev.server;

import io.vertx.core.Vertx;
import io.vertx.core.http.*;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;
import org.camposmdev.server.model.Client;
import org.camposmdev.server.router.UserRouter;

public class Launcher {
    static final int PORT = 4000;
    public static void main(String[] args) {
        var vertx = Vertx.vertx();
        var options = new HttpServerOptions();
        var server = vertx.createHttpServer(options);
        var userRouter = new UserRouter(vertx);
        var apiRouter = Router.router(vertx);
        apiRouter.route().handler(BodyHandler.create());
        apiRouter.route("/api/*").subRouter(userRouter.get());
        server.webSocketHandler(Client::new);
        server.requestHandler(apiRouter).listen(PORT)
                .onSuccess(e -> System.out.println("Server started on port " + PORT))
                .onFailure(e -> System.out.println(e.getCause().toString()));
    }
}
