package org.camposmdev.server.model;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class PlayerRegistry {
    private static PlayerRegistry pr;
    private final List<Player> players;

    private PlayerRegistry() {
        this.players = Collections.synchronizedList(new LinkedList<>());
    }

    public Player getByUsername(String username) {
        return players.stream()
                .filter(x -> x.getUsername().equals(username))
                .findFirst().orElse(null);
    }

    public Player getById(String id) {
        return players.stream()
                .filter(x -> x.getId().equals(id))
                .findFirst().orElse(null);
    }

    public void add(Player x) {
        System.out.println("Added player " + x);
        players.add(x);
    }

    public boolean remove(Player x) {
        System.out.println("Removed player " + x);
        return players.remove(x);
    }

    public boolean containsUsername(String username) {
        return getByUsername(username) != null;
    }

    public boolean containsId(String id) {
        return getById(id) != null;
    }


    @Override
    public String toString() {
        return players.toString();
    }

    public static PlayerRegistry get() {
        if (pr == null) pr = new PlayerRegistry();
        return pr;
    }
}
