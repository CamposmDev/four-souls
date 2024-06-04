package io.github.camposmdev.foursouls.model.game.deck;

import io.github.camposmdev.foursouls.model.atlas.MasterCardAtlas;
import io.github.camposmdev.foursouls.model.card.attribute.CardType;
import io.github.camposmdev.foursouls.model.card.character.CharacterCard;
import io.github.camposmdev.foursouls.model.card.eternal.EternalCard;
import io.github.camposmdev.foursouls.model.card.loot.LootCard;
import io.github.camposmdev.foursouls.model.card.monster.BaseMonsterCard;
import io.github.camposmdev.foursouls.model.card.outside.OutsideCard;
import io.github.camposmdev.foursouls.model.card.room.RoomCard;
import io.github.camposmdev.foursouls.model.card.treasure.TreasureCard;

import java.util.Random;

public class DeckManager {
    private Deck<CharacterCard> characters;
    private Deck<EternalCard> eternals;
    private Deck<TreasureCard> treasures;
    private Deck<BaseMonsterCard> monsters;
    private Deck<LootCard> loot;
    private Deck<RoomCard> rooms;
    private Deck<OutsideCard> outsides;

    public DeckManager(MasterCardAtlas atlas) {
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
}
