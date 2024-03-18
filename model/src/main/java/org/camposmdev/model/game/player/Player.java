package org.camposmdev.model.game.player;

import java.util.UUID;

public class Player implements Comparable<Player> {
    private static final int INITIAL_MONEY = 3;
    private final UUID id;

    public Player(UUID id) {
        this.id = UUID.randomUUID();
    }

    @Override
    public int compareTo(Player p) {
        return id.compareTo(p.id);
    }

    public String getId() {
        return id.toString();
    }
}
