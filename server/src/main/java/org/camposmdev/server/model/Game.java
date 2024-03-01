package org.camposmdev.server.model;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

public class Game {
    private final UUID id;
    private String hostId;
    private final List<Player> players;

    public Game() {
        this.id = UUID.randomUUID();
        this.hostId = null;
        this.players = new LinkedList<>();
    }

    public String getId() {
        return id.toString();
    }

    public String getHostId() {
        return hostId;
    }

    public void addPlayer(Player p) {
        /* set the host id to the first player that joins */
        if (players.size() == 0) {
            hostId = p.getId();
        }
        players.add(p);
    }

    public void removePlayer(Player p) {
        players.remove(p);
    }

    public List<Player> players() {
        return players;
    }

    @Override
    public String toString() {
        return "Game{" +
                "id=" + id +
                ", players=" + players +
                '}';
    }
}
