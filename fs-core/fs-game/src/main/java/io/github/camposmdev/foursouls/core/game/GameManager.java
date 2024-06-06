package io.github.camposmdev.foursouls.core.game;

/**
 * An interface for a game manager to manage local games or multiplayer games.
 */
public interface GameManager {
    /* Returns the seed of the game */
    long seed();
    
    /* Shuffles all the decks.  */
    void shuffle();

    PlayerManager players();
}
