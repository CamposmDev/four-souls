package org.camposmdev.model.game;

import org.camposmdev.model.game.player.Player;
import org.camposmdev.model.game.player.Players;

public class PlayerManager {
	private final Players players;

	public PlayerManager() {
		this.players = new Players();
	}

	public void add(Player... arg0) {
		for (Player p : arg0)
			players.add(p);
	}

	public boolean removeById(String id) {
		return players.remove(id);
	}

	/**
	 * Returns the player that currently has their turn.
	 * @return Player who currently has their turn
	 */
	public Player currentTurn() {
		return players.peek();
	}

	/**
	 * Check if a player has their turn by id
	 * @param id Player id to check if it's their turn
	 * @return true if the Player ids matches, otherwise false
	 */
	public boolean isTheirTurn(String id) {
		return players.isTheirTurn(id);
	}

	/**
	 * Returns player on the left of the current player's turn
	 * @return Player left of current player
	 */
	public Player leftPlayer() {
		return players.left();
	}

	/**
	 * Returns player on the right of the current player's turn
	 * @return Player right of current player
	 */
	public Player rightPlayer() {
		return players.right();
	}

	/**
	 * Returns players on the left and right of the current player's turn
	 * @return Player[leftPlayer, rightPlayer]
	 */
	public Player[] leftAndRightPlayer() {
		return new Player[] {players.left(), players.right()};
	}

	/**
	 * Goes to the next player's turn
	 * @return Player who has their next turn
	 */
	public Player nextTurn() {
		return players.next();
	}

	public boolean isEmpty() {
		return players.isEmpty();
	}

	public int turns() {
		return players.turns();
	}

	public int size() {
		return players.size();
	}
}
