package org.camposmdev.client.model;

import org.camposmdev.model.atlas.MasterCardAtlas;
import org.camposmdev.model.game.GameManager;
import org.camposmdev.model.game.LocalPlayerManager;
import org.camposmdev.model.game.PlayerManager;
import org.camposmdev.model.game.deck.DeckManager;
import org.camposmdev.model.game.deck.DiscardPileManager;

import java.util.Random;

/**
 * Manages players, decks, shop items, monsters, loot, and everything else. Utilized in either
 * singleplayer games or multiplayer games on the server.
 */
public class LocalGameManager implements GameManager {
    private final long seed;
    private final Random rand;
    private final LocalPlayerManager playerManager;
    private final DeckManager deckManager;
    private final DiscardPileManager discardPileManager;

    public LocalGameManager(MasterCardAtlas atlas) {
        this.seed = new Random().nextLong();
        this.rand = new Random(seed);
        this.playerManager = new LocalPlayerManager();
        this.deckManager = new DeckManager(atlas);
        this.discardPileManager = new DiscardPileManager();
    }

    public LocalGameManager(long seed, MasterCardAtlas atlas) {
        this.seed = seed;
        this.rand = new Random(seed);
        this.playerManager = new LocalPlayerManager();
        this.deckManager = new DeckManager(atlas);
        this.discardPileManager = new DiscardPileManager();
    }

    @Override
    public PlayerManager players() {
        return playerManager;
    }

    public DeckManager deck() {
        return deckManager;
    }

    public DiscardPileManager discards() {
        return discardPileManager;
    }

    public void shuffle() {
        deckManager.shuffle(rand);
    }

    @Override
    public long seed() {
        return seed;
    }
}
