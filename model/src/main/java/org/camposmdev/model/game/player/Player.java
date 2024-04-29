package org.camposmdev.model.game.player;

import java.util.UUID;

public class Player implements Comparable<Player> {
    private final UUID id;

    public Player() {
        this.id = UUID.randomUUID();
    }

    public String getId() {
        return id.toString();
    }

    @Override
    public int compareTo(Player p) {
        return id.compareTo(p.id);
    }
}
