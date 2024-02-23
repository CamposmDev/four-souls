package org.camposmdev.client.api;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.http.*;
import io.vertx.core.json.DecodeException;
import io.vertx.core.json.JsonObject;
import org.camposmdev.model.MessageType;


public class FSClient extends AbstractVerticle {
    private static final String HOST = "localhost";
    private static final int PORT = 4000;
    private static final String WS_ROUTE = "/";
    private static FSClient theClient;

    public static FSClient getInstance() {
        if (theClient == null)
            theClient = new FSClient();
        return theClient;
    }
//    private final Vertx vertx;
    private final HttpClient client;
    private ClientWebSocket ws;
    private String tokenCookie;

    private FSClient() {
//        this.vertx = Vertx.vertx();
        var options = new HttpClientOptions().setDefaultHost(HOST).setDefaultPort(PORT);
        this.client = vertx.createHttpClient(options);
    }

    public EventBus bus() {
        return vertx.eventBus();
    }

    private void initWS() {
        var options = new WebSocketConnectOptions().setHost(HOST).setPort(PORT).setURI(WS_ROUTE);
        var wsc = vertx.createWebSocketClient();
        ws = wsc.webSocket();
        ws.textMessageHandler(this::handleTextMessage)
                .connect(options)
                .onSuccess(e -> System.out.println("Connected to " + e.remoteAddress()))
                .onFailure(e -> System.out.println("Failed to connect WS"));
    }

    public void handleTextMessage(String msg) {
        try {
            System.out.println(msg);
            var obj = new JsonObject(msg);
            if (obj.containsKey(MessageType.GCHAT.name())) {
                /* notify global chat controller */
                var username = obj.getJsonObject(MessageType.GCHAT.name()).getString("username");
                var message = obj.getJsonObject(MessageType.GCHAT.name()).getString("message");
                var payload = username + ": " + message;
                vertx.eventBus().publish(MessageType.GCHAT.name(), payload);
            }
        } catch (DecodeException ex) {
            ex.printStackTrace();
        }
    }

    public void sendGlobalMessage(String s) {
        var payload = JsonObject.of("username", "Camposm").put("message", s);
        var obj = JsonObject.of(MessageType.GCHAT.name(), payload);
        ws.writeTextMessage(obj.toString());
    }

    public Future<String> login(String username, String password) {
        Promise<String> promise = Promise.promise();
        var payload = JsonObject.of("username", username).put("password", password);
        client.request(HttpMethod.POST, "/api/user/login").onSuccess(req -> {
            req.send(payload.toString()).onComplete(ar -> {
                if (ar.succeeded()) {
                    var code = ar.result().statusCode();
                    ar.result().body().onSuccess(handler -> {
                        var obj = handler.toJsonObject();
                        if (code == 200) {
                            /* save our cookie token */
                            tokenCookie = ar.result().cookies().get(0);
                            /* connect to websocket server */
                            initWS();
                            promise.complete(obj.getString("message"));
                        } else {
                            promise.fail(obj.getString("message"));
                        }
                    });
                }
            });
        });
        return promise.future();
    }

    public Future<Integer> getOnlineUsers() {
        Promise<Integer> promise = Promise.promise();
        client.request(HttpMethod.GET, "/api/user").onSuccess(req -> {
            req.headers().add("Cookie", tokenCookie);
            req.send().onSuccess(res -> {
                res.body().onSuccess(ar -> {
                    if (res.statusCode() == 200) {
                        promise.complete(ar.toJsonObject().getInteger("online"));
                    } else {
                        promise.fail(ar.toJsonObject().getString("message"));
                    }
                });
            }).onFailure(e -> promise.fail("Failed to connect to server"));
        });
        return promise.future();
    }

    public void close() {
        client.close();
        if (ws != null) ws.close();
        vertx.close();
    }
}
