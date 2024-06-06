package io.github.camposmdev.foursouls.core.game

import io.github.camposmdev.foursouls.core.game.player.Player

/**
 * Interface for managing players in a game.
 */
interface PlayerManager {
    /**
     * Adds one or more players to the game.
     * @param players One or more Player objects to be added
     */
    fun add(vararg players: Player)

    /**
     * Removes a player from the game by their ID.
     * @param id The ID of the player to be removed
     * @return true if the player was successfully removed, otherwise false
     */
    fun removeById(id: String): Boolean

    /**
     * Returns the player that currently has their turn.
     * @return Player who currently has their turn, or null if no players are present
     */
    fun currentTurn(): Player?

    /**
     * Checks if a player has their turn by ID.
     * @param id Player ID to check if it's their turn
     * @return true if the Player ID matches the current turn, otherwise false
     */
    fun isTheirTurn(id: String): Boolean

    /**
     * Returns the player on the left of the current player's turn.
     * @return Player on the left of the current player, or null if not available
     */
    fun leftPlayer(): Player?

    /**
     * Returns the player on the right of the current player's turn.
     * @return Player on the right of the current player, or null if not available
     */
    fun rightPlayer(): Player?

    /**
     * Returns players on the left and right of the current player's turn.
     * @return An array containing the players on the left and right of the current player, or null if not available
     */
    fun leftAndRightPlayer(): Array<Player?>

    /**
     * Advances to the next player's turn.
     * @return Player who has their next turn, or null if no players are present
     */
    fun passTurn(): Player?

    /**
     * Checks if there are no players in the game.
     * @return true if there are no players, otherwise false
     */
    fun isEmpty(): Boolean

    /**
     * Returns the number of turns that have been taken.
     * @return The number of turns taken
     */
    fun turns(): Int

    /**
     * Returns the number of players currently in the game.
     * @return The number of players in the game
     */
    fun size(): Int
}
