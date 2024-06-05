package io.github.camposmdev.foursouls.app.game.api;

import io.github.camposmdev.foursouls.model.api.BusEvent;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.eventbus.MessageConsumer;

@Deprecated
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

    public void hostGame() {
        ws.hostGame();
    }

    public void joinLobby(String gameId) {
        ws.joinLobby(gameId);
    }

    public void leaveLobby(String gameId) {
        ws.leaveLobby(gameId);
    }

    public void close() {
        if (wc != null) wc.close();
        if (ws != null) ws.close();
        if (vertx != null) vertx.close();
    }

    public MessageConsumer<Object> subscribeTo(BusEvent event) {
        return ws.subscribeTo(event);
    }

    public void sendGlobalMessage(String s) {
        ws.sendGlobalMessage(s);
    }

    public void sendLobbyMessage(String message) {
        ws.sendLobbyMessage(message);
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
