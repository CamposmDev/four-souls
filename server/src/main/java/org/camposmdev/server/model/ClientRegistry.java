package org.camposmdev.server.model;

import io.vertx.core.json.JsonObject;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class ClientRegistry {
    private static ClientRegistry cr;
    private static final int SIZE = 4;
    private final List<ClientHandler> clients;

    public static ClientRegistry get() {
        if (cr == null)
            cr = new ClientRegistry();
        return cr;
    }

    private ClientRegistry() {
        this.clients = Collections.synchronizedList(new LinkedList<>());
    }

    public void add(ClientHandler x) {
        if (clients.size() >= SIZE) return;
        System.out.println("Registered client " + x);
        clients.add(x);
    }

    public void remove(ClientHandler x) {
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

    /**
     * Sends a message to global chat
     * @param msg Message to be sent to all online users
     */
    public void notifyAll(JsonObject msg) {
        for (ClientHandler c : clients) {
            c.getWS().writeTextMessage(msg.toString());
        }
    }

    @Override
    public String toString() {
        return this.clients.toString();
    }
}
