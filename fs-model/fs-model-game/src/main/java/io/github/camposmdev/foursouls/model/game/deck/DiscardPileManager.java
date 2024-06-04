package io.github.camposmdev.foursouls.model.game.deck;

import io.github.camposmdev.foursouls.model.card.outside.OutsideCard;
import io.github.camposmdev.foursouls.model.card.loot.LootCard;
import io.github.camposmdev.foursouls.model.card.monster.BaseMonsterCard;
import io.github.camposmdev.foursouls.model.card.room.RoomCard;
import io.github.camposmdev.foursouls.model.card.treasure.TreasureCard;

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
