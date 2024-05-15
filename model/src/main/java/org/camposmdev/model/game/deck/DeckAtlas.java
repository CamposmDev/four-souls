package org.camposmdev.model.game.deck;

import org.camposmdev.model.atlas.MasterCardAtlas;
import org.camposmdev.model.card.attribute.CardType;
import org.camposmdev.model.card.character.CharacterCard;
import org.camposmdev.model.card.eternal.EternalCard;
import org.camposmdev.model.card.extra.OutsideCard;
import org.camposmdev.model.card.loot.LootCard;
import org.camposmdev.model.card.monster.BaseMonsterCard;
import org.camposmdev.model.card.room.RoomCard;
import org.camposmdev.model.card.treasure.TreasureCard;

import java.util.Random;

public class DeckAtlas {
    private Deck<CharacterCard> characters;
    private Deck<EternalCard> eternals;
    private Deck<TreasureCard> treasures;
    private Deck<BaseMonsterCard> monsters;
    private Deck<LootCard> loot;
    private Deck<RoomCard> rooms;
    private Deck<OutsideCard> outsides;

    public DeckAtlas(MasterCardAtlas atlas) {
        this.characters = new Deck<>(atlas.characters());
        this.eternals = new Deck<>(atlas.eternals());
        this.treasures = new Deck<>(atlas.treasures());
        this.monsters = new Deck<>(atlas.monsters());
        this.loot = new Deck<>(atlas.loot());
        this.rooms = new Deck<>(atlas.rooms());
        this.outsides = new Deck<>(atlas.outsides());
    }

    public Deck<CharacterCard> characters() {
        return characters;
    }

    public Deck<EternalCard> eternals() {
        return eternals;
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

    /**
     * Shuffles all the cards
     * @param rand
     */
    public void shuffle(Random rand) {
        characters.shuffle(rand);
        eternals.shuffle(rand);
        treasures.shuffle(rand);
        monsters.shuffle(rand);
        loot.shuffle(rand);
        rooms.shuffle(rand);
        outsides.shuffle(rand);
    }

    /**
     * Shuffles a specific deck
     * @param cardType Type of deck to shuffle
     * @param rand
     */
    public void shuffle(CardType cardType, Random rand) {
        switch (cardType) {
            case TREASURE -> treasures.shuffle(rand);
            case MONSTER -> monsters.shuffle(rand);
            case LOOT -> loot.shuffle(rand);
            case ROOM -> rooms.shuffle(rand);
            default -> {}
        }
    }

    public static DeckAtlas create(MasterCardAtlas atlas) {
        return new DeckAtlas(atlas);
    }
}
