package org.camposmdev.model.game.player;

import org.camposmdev.model.card.BaseCard;
import org.camposmdev.model.card.character.CharacterCard;
import org.camposmdev.model.card.eternal.EternalCard;
import org.camposmdev.model.card.loot.LootCard;
import org.camposmdev.model.card.treasure.TreasureCard;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

public class Player implements Comparable<Player> {
    private final UUID id;
    private CharacterCard character;
    private EternalCard eternal;
    private List<LootCard> loot;
    private List<TreasureCard> treasure;
    private List<BaseCard> souls;
    private Integer cents;

    public Player() {
        this.id = UUID.randomUUID();
        this.character = null;
        this.eternal = null;
        this.loot = new LinkedList<>();
        this.treasure = new LinkedList<>();
        this.souls = new LinkedList<>();
        this.cents = 0;
    }

    public String getId() {
        return id.toString();
    }

    public CharacterCard character() {
        return character;
    }

    public void setCharacter(CharacterCard character) {
        this.character = character;
    }

    public EternalCard eternal() {
        return eternal;
    }

    public void setEternal(EternalCard eternal) {
        this.eternal = eternal;
    }

    public List<LootCard> loot() {
        return loot;
    }

    public List<BaseCard> souls() {
        return souls;
    }

    public Integer cents() {
        return cents;
    }

    public void setCents(Integer cents) {
        this.cents = cents;
    }

    public List<TreasureCard> treasure() {
        return treasure;
    }

    @Override
    public int compareTo(Player p) {
        return id.compareTo(p.id);
    }
}
