package org.camposmdev.server.model;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class PlayerRegistry {
    private static PlayerRegistry pr;
    private volatile List<Player> players;

    private PlayerRegistry() {
        this.players = Collections.synchronizedList(new LinkedList<>());
    }

    public Player get(String name) {
        return players.stream()
                .filter(x -> x.getName().equals(name))
                .findFirst().orElse(null);
    }

    public boolean add(Player x) {
        System.out.println("Added player " + x);
        return players.add(x);
    }

    public boolean remove(Player x) {
        System.out.println("Removed player " + x);
        return players.remove(x);
    }

    public boolean contains(String name) {
        return players.contains(new Player(name));
    }

    @Override
    public String toString() {
        return players.toString();
    }

    public static PlayerRegistry getInstance() {
        if (pr == null) pr = new PlayerRegistry();
        return pr;
    }
}
