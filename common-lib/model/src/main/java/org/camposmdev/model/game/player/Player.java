package org.camposmdev.model.game.player;

import org.camposmdev.model.Dice;
import org.camposmdev.model.Killable;
import org.camposmdev.model.card.character.CharacterCard;
import org.camposmdev.model.card.loot.LootCard;
import org.camposmdev.model.card.monster.MonsterCard;
import org.camposmdev.model.card.treasure.TreasureCard;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

public class Player implements Killable, Comparable<Player> {
    private UUID id;
    private CharacterCard character;
    private List<LootCard> hand;
    private List<TreasureCard> trinkets;
    private int money;

    public Player(CharacterCard character) {
        this.id = UUID.randomUUID();
        this.character = character;
        this.hand = new LinkedList<>();
        this.trinkets = new LinkedList<>();
        this.money = 0;
    }

    public UUID getId() {
        return id;
    }

    public CharacterCard getCharacter() {
        return character;
    }

    public List<LootCard> getHand() {
        return hand;
    }

    public void playLootCard(LootCard lc) {
        /* TODO */
    }

    public void attack() {
        /* TODO */
    }

    public void playTreasureCard(TreasureCard tc) {
        /* TODO */
    }


    public int roll(int nSides) {
        return new Dice(nSides).roll().get();
    }

    public List<TreasureCard> getTrinkets() {
        return trinkets;
    }

    public int getMoney() {
        return money;
    }

    @Override
    public int compareTo(Player p) {
        return id.compareTo(p.id);
    }
}
