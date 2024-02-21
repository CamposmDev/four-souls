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
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.auth.authentication.Credentials;
import io.vertx.ext.auth.jwt.JWTAuth;
import io.vertx.ext.auth.jwt.JWTAuthOptions;
import org.camposmdev.server.net.ClientRunnable;

import javax.security.auth.login.CredentialNotFoundException;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.function.Function;

import static io.vertx.core.http.HttpMethod.*;

public class Launcher {
    static final int PORT = 4000;
    public static void main(String[] args) {
        JWTAuthOptions config = new JWTAuthOptions();
        JWTAuth provider = JWTAuth.create(Vertx.vertx(), config);
        var options = new HttpServerOptions();
        var server = Vertx.vertx().createHttpServer(options);
        server.requestHandler(e -> {
            if (e.method() == POST) {
                if (e.uri().equals("/api/user/login")) {
                    e.bodyHandler(handler -> {
                        var body = (JsonObject) handler.toJson();
                        System.out.println(body.getString("username"));
                        System.out.println(body.getString("password"));
                        String token = provider.generateToken(new JsonObject().put("username", body.getString("username")));
                        e.response().setStatusCode(200).addCookie(Cookie.cookie("token", token)).send(new JsonObject().put("message", "login ok").toString());
                    });
                }
            } else if (e.uri().equals("/api/user")) {
                var cookie = e.getCookie("token");
                var obj = JsonObject.of("token", cookie.getValue());
                System.out.println(obj);
                provider.authenticate(() -> obj).onSuccess(x -> {
                    System.out.println("good");
                }).onFailure(x -> {
                    System.out.println("bad");
                });
//                provider.authenticate(obj).onSuccess(handler -> {
//                    System.out.println("good");
//                    System.out.println(handler.attributes());
//                    e.response().setStatusCode(200).send(JsonObject.of("online", 0).toString());
//                }).onFailure(handler -> {
//                    System.out.println("bad");
//                    System.out.println(handler.toString());
//                    e.response().setStatusCode(400).send(JsonObject.of("message", "Invalid Credentials").toString());
//                });
//                provider.authenticate(() -> obj).andThen(handler -> {
//                    System.out.println(handler.succeeded());
//                    System.out.println(handler.result());
//                });
            } else {
                e.response().setStatusCode(404).send(JsonObject.of("message", "Endpoint Not Found").toString());
            }
        });

        server.listen(PORT).onComplete(e -> {
            if (e.succeeded()) {
                System.out.println("Server listening on port " + PORT);
            } else {
                System.err.println(e.cause().toString());
            }
        });
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
