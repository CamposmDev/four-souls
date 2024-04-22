package org.camposmdev.client.model;

import org.camposmdev.model.card.character.CharacterCard;
import org.camposmdev.model.card.eternal.EternalCard;
import org.camposmdev.model.card.extra.OutsideCard;
import org.camposmdev.model.card.loot.LootCard;
import org.camposmdev.model.card.monster.MonsterCard;
import org.camposmdev.model.card.room.RoomCard;
import org.camposmdev.model.card.treasure.TreasureCard;
import org.camposmdev.model.game.deck.Deck;

public class Game {
    private Deck<CharacterCard> characters;
    private Deck<EternalCard> eternals;
    private Deck<TreasureCard> treasures;
    private Deck<MonsterCard> monsters;
    private Deck<LootCard> loot;
    private Deck<RoomCard> rooms;
    private Deck<OutsideCard> outside;

    private Game() {
        characters = new Deck<>();
        eternals = new Deck<>();
        treasures = new Deck<>();
        monsters = new Deck<>();
        loot = new Deck<>();
        rooms = new Deck<>();
        outside = new Deck<>();
    }

    private static Game singleton;
    public static Game instance() {
        if (singleton == null) singleton = new Game();
        return singleton;
    }
}
