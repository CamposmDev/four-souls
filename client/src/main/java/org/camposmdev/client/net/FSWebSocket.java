package org.camposmdev.client.net;

import io.vertx.core.Vertx;
import io.vertx.core.eventbus.MessageConsumer;
import io.vertx.core.http.ClientWebSocket;
import io.vertx.core.http.HttpHeaders;
import io.vertx.core.http.WebSocketConnectOptions;
import io.vertx.core.json.DecodeException;
import io.vertx.core.json.JsonObject;
import org.camposmdev.client.model.UserAccount;
import org.camposmdev.model.MessageType;

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

    public MessageConsumer<Object> subscribeTo(String s) {
        return vertx.eventBus().consumer(s);
    }

    public void notifySubscribers(String s, Object payload) {
        vertx.eventBus().publish(s, payload);
    }

    private void handleTextMessage(String msg) {
        try {
            var obj = new JsonObject(msg);
            if (obj.containsKey(MessageType.G_CHAT.name())) {
                /* notify global chat controller */
                var username = obj.getJsonObject(MessageType.G_CHAT.name()).getString("username");
                var message = obj.getJsonObject(MessageType.G_CHAT.name()).getString("message");
                var payload = username + ": " + message;
                this.notifySubscribers(MessageType.G_CHAT.name(), payload);
            }
        } catch (DecodeException ex) {
            ex.printStackTrace();
        }
    }

    public void sendGlobalMessage(String s) {
        var payload = JsonObject.of("username", UserAccount.get().getUsername()).put("message", s);
        var obj = JsonObject.of(MessageType.G_CHAT.name(), payload);
        ws.writeTextMessage(obj.toString());
    }

    protected void close() {
        ws.close();
    }
}
