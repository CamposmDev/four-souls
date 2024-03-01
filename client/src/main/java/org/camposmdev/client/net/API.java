package org.camposmdev.client.net;

import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.eventbus.MessageConsumer;
import io.vertx.core.json.JsonObject;


public class API {
    private static final String HOST = "localhost";
    private static final int PORT = 4000;
    private static API singleton;

    public static API get() {
        if (singleton == null)
            singleton = new API();
        return singleton;
    }

    private final Vertx vertx;
    private final FSWebClient wc;
    private FSWebSocket ws;
    private String authCookie;

    private API() {
        this.vertx = Vertx.vertx();
        this.wc = new FSWebClient(vertx, HOST, PORT);
    }

    public Future<String> login(String username, String password) {
        return wc.login(username, password);
    }

    public Future<Integer> getOnlineUserCount() {
        return wc.getOnlineUserCount();
    }

    public Future<String> hostGame() {
        return wc.hostGame();
    }

    public Future<JsonObject> joinGame(String gameId) {
        return wc.joinGame(gameId);
    }

    public void close() {
        if (wc != null) wc.close();
        if (ws != null) ws.close();
        if (vertx != null) vertx.close();
    }

    public MessageConsumer<Object> subscribeTo(String s) {
        return ws.subscribeTo(s);
    }

    public void sendGlobalMessage(String s) {
        ws.sendGlobalMessage(s);
    }

    public String getAuthCookie() {
        return authCookie;
    }

    public void setAuthCookie(String authCookie) {
        this.authCookie = authCookie;
    }

    public void initWS() {
        this.ws = new FSWebSocket(vertx, HOST, PORT);
    }
}
