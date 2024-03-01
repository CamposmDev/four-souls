package org.camposmdev.server.model;

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

    public Game get(String gameId) {
        return games.stream()
                .filter(x -> x.getId().equals(gameId))
                .findFirst().orElse(null);
    }

    public boolean contains(String gameId) {
        return get(gameId) != null;
    }
}
