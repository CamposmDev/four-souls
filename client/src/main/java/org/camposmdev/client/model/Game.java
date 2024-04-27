package org.camposmdev.client.model;

import org.camposmdev.model.atlas.MasterCardAtlas;
import org.camposmdev.model.game.deck.DeckAtlas;
import org.camposmdev.model.game.player.Players;
import org.camposmdev.util.FXUtil;

import java.util.Random;

public class Game {
    private final long seed;
    private final Random rand;
    private final DeckAtlas deck;
    private final Players players;


    private Game() {
        this.seed = new Random().nextLong();
        this.rand = new Random(seed);
        this.deck = DeckAtlas.create(FXUtil.loadJSON("cards.json", MasterCardAtlas.class));
        this.players = new Players();
    }

    private Game(long seed) {
        this.seed = seed;
        this.rand = new Random(seed);
        this.deck = DeckAtlas.create(FXUtil.loadJSON("cards.json", MasterCardAtlas.class));
        this.players = new Players();
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

    public static Game create(long seed) {
        return new Game(seed);
    }
}
