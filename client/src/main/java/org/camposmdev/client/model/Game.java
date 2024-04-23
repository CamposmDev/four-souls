package org.camposmdev.client.model;

import org.camposmdev.model.atlas.MasterCardAtlas;
import org.camposmdev.model.game.deck.DeckAtlas;
import org.camposmdev.model.game.player.Players;
import org.camposmdev.util.FXUtil;

import java.util.Random;

public class Game {
    private final DeckAtlas deck;
    private final Players players;
    private final long seed;
    private final Random rand;

    private Game() {
        deck = DeckAtlas.create(FXUtil.loadJSON("cards.json", MasterCardAtlas.class));
        players = new Players();
        seed = new Random().nextLong();
        rand = new Random(seed);
    }

    public DeckAtlas deck() {
        return deck;
    }

    public void shuffle() {
        deck.shuffle(rand);
    }

    public Players players() {
        return players;
    }

    public long seed() {
        return seed;
    }

    public static Game create() {
        return new Game();
    }
}
