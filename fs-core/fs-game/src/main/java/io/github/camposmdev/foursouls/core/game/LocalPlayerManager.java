package io.github.camposmdev.foursouls.core.game;

import io.github.camposmdev.foursouls.core.game.player.Player;
import io.github.camposmdev.foursouls.core.game.player.Players;
import org.jetbrains.annotations.NotNull;

/**
 * The LocalPlayerManager class is responsible for managing the players in the game. It handles adding and removing players,
 * determining the current player turn and navigating between players in the turn order.
 */
public class LocalPlayerManager implements PlayerManager {
	private final Players players;

	public LocalPlayerManager() {
		this.players = new Players();
	}

	@Override
	public void add(Player... arg0) {
		for (Player p : arg0)
			players.add(p);
	}

	@Override
	public boolean removeById(String id) {
		return players.remove(id);
	}

	@Override
	public Player currentTurn() {
		return players.peek();
	}

	@Override
	public boolean isTheirTurn(String id) {
		return players.isTheirTurn(id);
	}

	@Override
	public Player leftPlayer() {
		return players.left();
	}

	@Override
	public Player rightPlayer() {
		return players.right();
	}

	@Override
	public Player @NotNull [] leftAndRightPlayer() {
		return new Player[] {players.left(), players.right()};
	}

	@Override
	public Player passTurn() {
		return players.next();
	}

	@Override
	public boolean isEmpty() {
		return players.isEmpty();
	}

	@Override
	public int turns() {
		return players.turns();
	}

	@Override
	public int size() {
		return players.size();
	}
}
