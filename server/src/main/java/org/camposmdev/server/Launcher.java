package org.camposmdev.server;

import io.netty.util.internal.logging.InternalLoggerFactory;
import io.netty.util.internal.logging.Log4J2LoggerFactory;
import io.vertx.core.AsyncResult;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.http.Cookie;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServerOptions;
import io.vertx.core.impl.logging.Logger;
import io.vertx.core.impl.logging.LoggerFactory;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.auth.jwt.JWTAuth;
import io.vertx.ext.auth.jwt.JWTAuthOptions;
import org.camposmdev.server.net.ClientRunnable;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.function.Function;

import static io.vertx.core.http.HttpMethod.*;

public class Launcher {
    static final int PORT = 4000;
    public static void main(String[] args) {
//        JWTAuthOptions config = new JWTAuthOptions();
//        JWTAuth provider = JWTAuth.create(Vertx.vertx(), config);
//        var options = new HttpServerOptions();
//        var server = Vertx.vertx().createHttpServer(options);
//
//        server.requestHandler(e -> {
//            if (e.method() == POST) {
//                if (e.uri().equals("/login")) {
//                    e.bodyHandler(handler -> {
//                        var body = (JsonObject) handler.toJson();
//                        System.out.println(body.getString("username"));
//                        System.out.println(body.getString("password"));
//                        String token = provider.generateToken(new JsonObject().put("username", body.getString("username")));
//
//                        e.response().setStatusCode(200).addCookie(Cookie.cookie("token", token)).send(new JsonObject().put("message", "login ok").toString());
//                    });
//                }
//            } else if (e.uri().equals("/api/username/search")) {
//                var cookie = e.getCookie("token");
//                System.out.println(cookie.getValue());
//                e.response().setStatusCode(200).send("searching...");
//            } else {
//                e.response().setStatusCode(404).send();
//            }
//        });
//
//        server.listen(PORT).onComplete(e -> {
//            if (e.succeeded()) {
//                System.out.println("Server listening on port " + PORT);
//            } else {
//                System.err.println(e.cause().toString());
//            }
//        });
        final var PORT = 3000;
        try (ServerSocket ss = new ServerSocket(PORT);) {
            System.out.println("Server started on port " + PORT);
            while (true) {
                var socket = ss.accept();
                ClientRunnable runnable = new ClientRunnable(socket);
                Thread t = new Thread(runnable);
                t.start();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
