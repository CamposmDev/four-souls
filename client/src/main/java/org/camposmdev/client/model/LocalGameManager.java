package org.camposmdev.client.model;

import org.camposmdev.model.atlas.MasterCardAtlas;
import org.camposmdev.model.game.GameManager;
import org.camposmdev.model.game.deck.DeckManager;
import org.camposmdev.model.game.player.Players;
import org.camposmdev.util.FXUtil;

import java.util.Random;

/**
 * Manages players, decks, shop items, monsters, loot, and everything else. Utilized in either
 * singleplayer games or multiplayer games on the server.
 */
public class LocalGameManager implements GameManager {
    private final long seed;
    private final Random rand;
    private final DeckManager deck;
    private final Players players;


    private LocalGameManager() {
        this.seed = new Random().nextLong();
        this.rand = new Random(seed);
        this.deck = DeckManager.create(FXUtil.loadJSON("cards.json", MasterCardAtlas.class));
        this.players = new Players();
    }

    private LocalGameManager(long seed) {
        this.seed = seed;
        this.rand = new Random(seed);
        this.deck = DeckManager.create(FXUtil.loadJSON("cards.json", MasterCardAtlas.class));
        this.players = new Players();
    }

    public DeckManager deck() {
        return deck;
    }

    public void shuffle() {
        deck.shuffle(rand);
    }

    public Players players() {
        return players;
    }

    @Override
    public long seed() {
        return seed;
    }

    public static LocalGameManager create() {
        return new LocalGameManager();
    }

    public static LocalGameManager create(long seed) {
        return new LocalGameManager(seed);
    }
}
