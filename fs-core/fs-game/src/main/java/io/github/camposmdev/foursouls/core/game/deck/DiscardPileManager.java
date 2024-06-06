package io.github.camposmdev.foursouls.core.game.deck;

import io.github.camposmdev.foursouls.core.card.outside.OutsideCard;
import io.github.camposmdev.foursouls.core.card.loot.LootCard;
import io.github.camposmdev.foursouls.core.card.monster.BaseMonsterCard;
import io.github.camposmdev.foursouls.core.card.room.RoomCard;
import io.github.camposmdev.foursouls.core.card.treasure.TreasureCard;

public class DiscardPileManager {
	private final Deck<TreasureCard> treasures;
	private final Deck<BaseMonsterCard> monsters;
	private final Deck<LootCard> loot;
	private final Deck<RoomCard> rooms;
	private final Deck<OutsideCard> outsides;

	public DiscardPileManager() {
		this.treasures = new Deck<>();
		this.monsters = new Deck<>();
		this.loot = new Deck<>();
		this.rooms = new Deck<>();
		this.outsides = new Deck<>();
	}

	public Deck<TreasureCard> treasures() {
		return treasures;
	}

	public Deck<BaseMonsterCard> monsters() {
		return monsters;
	}

	public Deck<LootCard> loot() {
		return loot;
	}

	public Deck<RoomCard> rooms() {
		return rooms;
	}

	public Deck<OutsideCard> outsides() {
		return outsides;
	}
}
