package org.camposmdev.server;

import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.core.http.*;
import io.vertx.core.json.DecodeException;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.auth.User;
import io.vertx.ext.auth.authentication.TokenCredentials;
import io.vertx.ext.auth.jwt.JWTAuth;
import io.vertx.ext.auth.jwt.JWTAuthOptions;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;
import io.vertx.ext.web.handler.JWTAuthHandler;
import org.camposmdev.server.middleware.Auth;
import org.camposmdev.server.model.Client;
import org.camposmdev.server.model.ClientRegistry;
import org.camposmdev.server.model.PlayerRegistry;
import org.camposmdev.server.router.UserRouter;

import javax.swing.*;

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


        /* OLD CODE */
//        final var PORT = 3000;
//        try (ServerSocket ss = new ServerSocket(PORT);) {
//            System.out.println("Server started on port " + PORT);
//            while (true) {
//                var socket = ss.accept();
//                ClientRunnable runnable = new ClientRunnable(socket);
//                Thread t = new Thread(runnable);
//                t.start();
//            }
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
    }
}
