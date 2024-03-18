package org.camposmdev.client.net;

import io.vertx.core.Vertx;
import io.vertx.core.eventbus.MessageConsumer;
import io.vertx.core.http.ClientWebSocket;
import io.vertx.core.http.HttpHeaders;
import io.vertx.core.http.WebSocketConnectOptions;
import io.vertx.core.json.DecodeException;
import io.vertx.core.json.JsonObject;
import org.camposmdev.client.game.UserContext;
import org.camposmdev.model.net.BusEvent;
import org.camposmdev.model.net.MType;

class FSWebSocket {
    private static final String WS_ROUTE = "/ws";
    private final Vertx vertx;
    private final ClientWebSocket ws;

    protected FSWebSocket(Vertx vertx, final String HOST, final int PORT) {
        var options = new WebSocketConnectOptions()
                .setHost(HOST)
                .setPort(PORT)
                .setURI(WS_ROUTE)
                .addHeader(HttpHeaders.COOKIE, API.get().getAuthCookie());
        this.vertx = vertx;
        this.ws = vertx.createWebSocketClient().webSocket();
        ws.textMessageHandler(this::handleTextMessage)
                .connect(options)
                .onSuccess(e -> System.out.println("Connected to " + e.remoteAddress()))
                .onFailure(e -> System.out.println("Failed to connect WS"));
    }

    public MessageConsumer<Object> subscribeTo(BusEvent event) {
        return vertx.eventBus().consumer(event.name());
    }

    public void notifySubscribers(BusEvent event, Object payload) {
        vertx.eventBus().publish(event.name(), payload);
    }

    private void handleTextMessage(String text) {
        try {
            var obj = new JsonObject(text);
            handleJSON(obj);
        } catch (DecodeException ex) {
            System.err.println(ex.getCause());
        }
    }

    private void handleJSON(JsonObject arg) {
        /* notify global chat controller */
        if (arg.containsKey(MType.G_CHAT.name())) {
            var username = arg.getJsonObject(MType.G_CHAT.name()).getString("username");
            var message = arg.getJsonObject(MType.G_CHAT.name()).getString("message");
            var payload = username + ": " + message;
            this.notifySubscribers(BusEvent.GLOBAL_CHAT, payload);
        }
        /* notify lobby controller */
        else if (arg.containsKey(MType.L_CHAT.name())) {
            var msg = arg.getJsonObject(MType.L_CHAT.name());
            var username = msg.getString("username");
            var message = msg.getString("message");
            var payload = String.format("[%s]: %s\n", username, message);
            this.notifySubscribers(BusEvent.LOBBY_CHAT, payload);
        }
        else if (arg.containsKey(MType.HOST_GAME.name())) {
            var msg = arg.getJsonObject(MType.HOST_GAME.name());
            this.notifySubscribers(BusEvent.SHOW_LOBBY, msg);
        }
        else if (arg.containsKey(MType.JOIN_LOBBY.name())) {
            var msg = arg.getJsonObject(MType.JOIN_LOBBY.name());
            this.notifySubscribers(BusEvent.SHOW_LOBBY, msg);
        }
        else if (arg.containsKey(MType.UPDATE_LOBBY.name())) {
            var msg = arg.getJsonObject(MType.UPDATE_LOBBY.name());
            this.notifySubscribers(BusEvent.UPDATE_LOBBY, msg);
        }
        else if (arg.containsKey(MType.LEAVE_LOBBY.name())) {
            var msg = arg.getJsonObject(MType.LEAVE_LOBBY.name());
            this.notifySubscribers(BusEvent.REMOVE_LOBBY, msg);
        }
        else if (arg.containsKey(MType.LOBBY_CLOSED.name())) {
            var msg = arg.getJsonObject(MType.LOBBY_CLOSED.name());
            this.notifySubscribers(BusEvent.REMOVE_LOBBY, msg);
        } else {
            System.out.println(arg);
        }
    }

    public void sendGlobalMessage(String s) {
        var payload = JsonObject.of("username", UserContext.get().getUsername()).put("message", s);
        var obj = JsonObject.of(MType.G_CHAT.name(), payload);
        ws.writeTextMessage(obj.toString());
    }

    public void sendLobbyMessage(String s) {
        var payload = JsonObject.of(
                "gameId", UserContext.get().getCurrentLobby().getId(),
                "userId", UserContext.get().getId(),
                "message", s);
        var obj = JsonObject.of(MType.L_CHAT.name(), payload);
        ws.writeTextMessage(obj.toString());
    }

    public void hostGame() {
        var msg = JsonObject.of(MType.HOST_GAME.name(), null);
        ws.writeTextMessage(msg.toString());
    }

    public void joinLobby(String gameId) {
        var payload = JsonObject.of(
                "gameId", gameId,
                "userId", UserContext.get().getId());
        var msg = JsonObject.of(MType.JOIN_LOBBY.name(), payload);
        ws.writeTextMessage(msg.toString());
    }

    public void leaveLobby(String gameId) {
        var payload = JsonObject.of(
                "gameId", gameId,
                "userId", UserContext.get().getId());
        var msg = JsonObject.of(MType.LEAVE_LOBBY.name(), payload);
        ws.writeTextMessage(msg.toString());
    }

    protected void close() {
        ws.close();
    }
}
