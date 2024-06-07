package io.github.camposmdev.foursouls.app.game.api;

import io.vertx.core.Vertx;
import io.vertx.core.eventbus.MessageConsumer;
import io.vertx.core.http.ClientWebSocket;
import io.vertx.core.http.HttpHeaders;
import io.vertx.core.http.WebSocketConnectOptions;
import io.vertx.core.json.DecodeException;
import io.vertx.core.json.JsonObject;
import io.github.camposmdev.foursouls.app.game.model.Model;
import io.github.camposmdev.foursouls.core.api.BusEvent;
import io.github.camposmdev.foursouls.core.api.message.MType;

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
            ex.printStackTrace(System.out);
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
        else if (arg.containsKey(MType.LOCAL_CHAT.name())) {
            var msg = arg.getJsonObject(MType.LOCAL_CHAT.name());
            var username = msg.getString("username");
            var message = msg.getString("message");
            var payload = String.format("[%s]: %s\n", username, message);
            this.notifySubscribers(BusEvent.LOBBY_CHAT, payload);
        }
        else if (arg.containsKey(MType.HOST_GAME.name())) {
            var msg = arg.getJsonObject(MType.HOST_GAME.name());
            this.notifySubscribers(BusEvent.SHOW_LOBBY, msg);
        }
//        else if (arg.containsKey(MType.BASEMENT_JOIN.name())) {
//            var msg = arg.getJsonObject(MType.BASEMENT_JOIN.name());
//            this.notifySubscribers(BusEvent.SHOW_LOBBY, msg);
//        }
//        else if (arg.containsKey(MType.BASEMENT_USERS.name())) {
//            var msg = arg.getJsonObject(MType.BASEMENT_USERS.name());
//            this.notifySubscribers(BusEvent.UPDATE_LOBBY, msg);
//        }
//        else if (arg.containsKey(MType.BASEMENT_LEAVE.name())) {
//            var msg = arg.getJsonObject(MType.BASEMENT_LEAVE.name());
//            this.notifySubscribers(BusEvent.REMOVE_LOBBY, msg);
//        }
//        else if (arg.containsKey(MType.BASEMENT_CLOSED.name())) {
//            var msg = arg.getJsonObject(MType.BASEMENT_CLOSED.name());
//            this.notifySubscribers(BusEvent.REMOVE_LOBBY, msg);
//        } else {
//            System.out.println(arg);
//        }
    }

    public void sendGlobalMessage(String message) {
        var username = Model.instance().getUser().getUsername();
        var payload = JsonObject.of("username", username, "message", message);
        var obj = JsonObject.of(MType.G_CHAT.name(), payload);
        ws.writeTextMessage(obj.toString());
    }

    public void sendLobbyMessage(String message) {
        var gameId = Model.instance().getCurrentLobby().getId();
        var userId = Model.instance().getUser().getId();
        var payload = JsonObject.of(
                "gameId", gameId,
                "userId", userId,
                "message", message);
        var obj = JsonObject.of(MType.LOCAL_CHAT.name(), payload);
        ws.writeTextMessage(obj.toString());
    }

    public void hostGame() {
        var msg = JsonObject.of(MType.HOST_GAME.name(), null);
        ws.writeTextMessage(msg.toString());
    }

    @Deprecated
    public void joinLobby(String gameId) {
//        var userId = Model.instance().getUser().getId();
//        var payload = JsonObject.of(
//                "gameId", gameId,
//                "userId", userId);
//        var msg = JsonObject.of(MType.BASEMENT_JOIN.name(), payload);
//        ws.writeTextMessage(msg.toString());
    }

    public void leaveLobby(String gameId) {
        var userId = Model.instance().getUser().getId();
        var payload = JsonObject.of(
                "gameId", gameId,
                "userId", userId);
//        var msg = JsonObject.of(MType.BASEMENT_LEAVE.name(), payload);
//        ws.writeTextMessage(msg.toString());
    }

    protected void close() {
        ws.close();
    }
}
