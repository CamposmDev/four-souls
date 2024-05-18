package org.camposmdev.model.game.player;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.camposmdev.model.card.BaseCard;
import org.camposmdev.model.card.character.CharacterCard;
import org.camposmdev.model.card.eternal.EternalCard;
import org.camposmdev.model.card.loot.LootCard;
import org.camposmdev.model.card.treasure.TreasureCard;

import java.util.UUID;

public class Player implements Comparable<Player> {
    private final static int DEFAULT_CENTS = 3;
    private final UUID id;
    private CharacterCard character;
    private EternalCard eternal;
    private ObservableList<LootCard> loot;
    private ObservableList<TreasureCard> treasure;
    private ObservableList<BaseCard> souls;
    private SimpleIntegerProperty cents;

    public Player() {
        this.id = UUID.randomUUID();
        this.character = null;
        this.eternal = null;
        this.loot = FXCollections.observableArrayList();
        this.treasure = FXCollections.observableArrayList();
        this.souls = FXCollections.observableArrayList();
        this.cents = new SimpleIntegerProperty(DEFAULT_CENTS);
    }

    public String id() {
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

    public ObservableList<LootCard> loot() {
        return loot;
    }

    public ObservableList<TreasureCard> treasure() {
        return treasure;
    }

    public ObservableList<BaseCard> souls() {
        return souls;
    }

    public SimpleIntegerProperty cents() {
        return cents;
    }

    @Override
    public int compareTo(Player p) {
        return id.compareTo(p.id);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Player p) {
            return id.equals(p.id);
        }
        return super.equals(obj);
    }
}
