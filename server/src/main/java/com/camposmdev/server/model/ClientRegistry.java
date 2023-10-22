package com.camposmdev.server.model;

import java.io.Serial;
import java.io.Serializable;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class ClientRegistry implements Serializable {
    private static ClientRegistry cr;
    private static final int SIZE = 4;
    private volatile List<Client> clients;

    public static ClientRegistry getInstance() {
        if (cr == null)
            cr = new ClientRegistry();
        return cr;
    }

    private ClientRegistry() {
        this.clients = Collections.synchronizedList(new LinkedList<>());
    }

    public boolean add(Client x) {
        if (clients.size() >= SIZE) return false;
        System.out.println("Registered client " + x);
        return clients.add(x);
    }

    public boolean remove(Client x) {
        System.out.println("Removed client " + x);
        return clients.remove(x);
    }

    public boolean isPlayerTaken(String name) {
        return clients.stream()
                .filter(x -> x.getPlayer() != null && x.getPlayer().getName().equals(name))
                .collect(Collectors.toList()).size() >= 1;
    }

    @Override
    public String toString() {
        return this.clients.toString();
    }
}
