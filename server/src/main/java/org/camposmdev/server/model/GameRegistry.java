package org.camposmdev.server.model;

import io.vertx.core.json.JsonObject;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class GameRegistry {
    private static GameRegistry gr;

    public static GameRegistry get() {
        if (gr == null) gr = new GameRegistry();
        return gr;
    }

    private final List<Game> games;

    private GameRegistry() {
        this.games = Collections.synchronizedList(new LinkedList<>());
    }

    public void add(Game x) {
        System.out.println("Registered game " + x);
        games.add(x);
    }

    public void remove(Game x) {
        System.out.println("Removed game " + x);
        games.remove(x);
    }

    public Game getById(String gameId) {
        return games.stream()
                .filter(x -> x.getId().equals(gameId))
                .findFirst().orElse(null);
    }

    /**
     * Sends a message to lobby chat
     * @param gameId ID of the Game
     * @param arg Message to be sent to players in lobby
     */
    public void notifyLobby(String gameId, JsonObject arg) {
        var game = getById(gameId);
        for (var player : game.players())
            ClientRegistry.get().sendMessageTo(player.getId(), arg);
    }

    public boolean contains(String gameId) {
        return getById(gameId) != null;
    }
}
