package org.camposmdev.model.game.deck;

import org.camposmdev.model.card.outside.OutsideCard;
import org.camposmdev.model.card.loot.LootCard;
import org.camposmdev.model.card.monster.BaseMonsterCard;
import org.camposmdev.model.card.room.RoomCard;
import org.camposmdev.model.card.treasure.TreasureCard;

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
