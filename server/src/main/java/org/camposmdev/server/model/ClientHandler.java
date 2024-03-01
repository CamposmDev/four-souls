package org.camposmdev.server.model;

import io.vertx.core.http.ServerWebSocket;
import io.vertx.core.json.DecodeException;
import io.vertx.core.json.JsonObject;
import org.camposmdev.model.MessageType;

public class ClientHandler {
    private final ServerWebSocket ws;
//    private Player player;


    public ClientHandler(ServerWebSocket ws) {
        this.ws = ws;
        this.ws.accept();
        this.ws.textMessageHandler(this::handleTextMessage);
        this.ws.closeHandler(this::closeHandler);
        ClientRegistry.get().add(this);
    }

    private void handleTextMessage(String msg) {
        try {
            var obj = new JsonObject(msg);
            if (obj.containsKey(MessageType.G_CHAT.name())) {
                ClientRegistry.get().notifyAll(obj);
            }
        } catch (DecodeException ex) {
            /* Failed to parse JSON */
            ws.writeTextMessage(JsonObject.of("message", "Invalid JSON").toString());
        }
    }

    private void closeHandler(Void x) {
        ClientRegistry.get().remove(this);
    }

    public ServerWebSocket getWS() {
        return ws;
    }

//    public Player getPlayer() {
//        return player;
//    }

    @Override
    public String toString() {
        return ws.remoteAddress().toString();
    }
}
