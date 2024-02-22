package org.camposmdev.server.model;

import io.vertx.core.http.ServerWebSocket;
import io.vertx.core.json.DecodeException;
import io.vertx.core.json.JsonObject;
import org.camposmdev.model.MessageType;
import org.camposmdev.model.packet.Packet;
import org.camposmdev.model.packet.PacketType;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client {
    private ServerWebSocket ws;
    private Player player;


    public Client(ServerWebSocket ws) {
        this.ws = ws;
        this.player = null;
        this.ws.accept();
        this.ws.textMessageHandler(this::handleTextMessage);
        this.ws.closeHandler(this::closeHandler);
        ClientRegistry.getInstance().add(this);
    }

    private void handleTextMessage(String msg) {
        try {
            var obj = new JsonObject(msg);
            if (obj.containsKey(MessageType.GCHAT.name())) {
                ClientRegistry.getInstance().notifyAll(obj);
            }
        } catch (DecodeException ex) {
            /* Failed to parse JSON */
            ws.writeTextMessage(JsonObject.of("message", "Invalid JSON").toString());
        }
    }

    private void closeHandler(Void x) {
        ClientRegistry.getInstance().remove(this);
    }

//    public void sendPacket(PacketType type, Object payload) throws IOException {
//        ws.writeTextMessage(JsonObject.of(type.name(), JsonObject.mapFrom(payload)).toString());
//    }

//    public void sendNACKPacket() {
//        Packet.sendNACKPacket(out);
//    }
//
//    public void sendNACKPacket(String msg) {
//        Packet.sendNACKPacket(out, msg);
//    }
//
//    public void sendACKPacket() {
//        Packet.sendACKPacket(out);
//    }

    public ServerWebSocket getWS() {
        return ws;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public boolean isLoggedIn() {
        return player != null;
    }

    public void disconnect() throws IOException {
        ws.close();
    }

    @Override
    public String toString() {
        return ws.remoteAddress().toString();
    }
}
