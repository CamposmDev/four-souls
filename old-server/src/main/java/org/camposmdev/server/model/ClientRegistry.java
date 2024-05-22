package org.camposmdev.server.model;

import io.vertx.core.json.JsonObject;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class ClientRegistry {
    private static ClientRegistry cr;

    public static ClientRegistry get() {
        if (cr == null)
            cr = new ClientRegistry();
        return cr;
    }

    private final List<WSAgent> clients;

    private ClientRegistry() {
        this.clients = Collections.synchronizedList(new LinkedList<>());
    }

    public void add(WSAgent x) {
        System.out.println("Registered client " + x);
        clients.add(x);
    }

    public void remove(WSAgent x) {
        System.out.println("Removed client " + x);
        clients.remove(x);
    }

    public int size() {
        return clients.size();
    }

//    public boolean isPlayerTaken(String name) {
//        return clients.stream()
//                .filter(x -> x.getPlayer() != null && x.getPlayer().getName().equals(name))
//                .toList().size() >= 1;
//    }

    public WSAgent getById(String id) {
        return clients.stream().filter(x -> x.getUserId().equals(id)).findFirst().orElse(null);
    }

    public void sendMessageTo(String id, JsonObject arg) {
        getById(id).getWS().writeTextMessage(arg.toString());
    }

    /**
     * Sends a message to global chat
     * @param arg Message to be sent to all online users
     */
    public void notifyAll(JsonObject arg) {
        for (WSAgent c : clients) {
            c.getWS().writeTextMessage(arg.toString());
        }
    }

    @Override
    public String toString() {
        return this.clients.toString();
    }
}
