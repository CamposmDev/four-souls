package org.camposmdev.model.game.player;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.camposmdev.model.card.character.CharacterCard;
import org.camposmdev.model.card.eternal.EternalCard;
import org.camposmdev.model.card.loot.LootCard;
import org.camposmdev.model.card.treasure.TreasureCard;

import java.util.UUID;

/**
 * Represents a player in the game.
 */
public class Player implements Comparable<Player> {
    private final static int DEFAULT_CENTS = 3;
    private final UUID id;
    private Attribute<Integer> hp;
    private Attribute<Integer> atk;
    private CharacterCard character;
    private EternalCard eternal;
    private final ObservableList<LootCard> loot;
    private final ObservableList<TreasureCard> treasure;
    private final Souls souls;
    private final SimpleIntegerProperty cents;

    /**
     * Constructs a new Player with default values.
     * The player is assigned a unique ID and default cents value.
     */
    public Player() {
        this.id = UUID.randomUUID();
        this.hp = null;
        this.atk = null;
        this.character = null;
        this.eternal = null;
        this.loot = FXCollections.observableArrayList();
        this.treasure = FXCollections.observableArrayList();
        this.souls = new Souls();
        this.cents = new SimpleIntegerProperty(DEFAULT_CENTS);
    }

    public Player(CharacterCard character, EternalCard eternal) {
        this.id = UUID.randomUUID();
        this.hp = new Attribute<>(character.getHp());
        this.atk = new Attribute<>(character.getAtk());
        this.character = character;
        this.eternal = eternal;
        this.loot = FXCollections.observableArrayList();
        this.treasure = FXCollections.observableArrayList();
        this.souls = new Souls();
        this.cents = new SimpleIntegerProperty(DEFAULT_CENTS);
    }

    /**
     * Returns the player's unique ID as a string.
     *
     * @return the player's ID
     */
    public String id() {
        return id.toString();
    }

    /**
     * Returns the player's health points (HP) attribute.
     *
     * @return the player's HP attribute
     */
    public Attribute<Integer> hp() {
        return hp;
    }

    /**
     * Returns the player's attack points (ATK) attribute.
     *
     * @return the player's ATK attribute
     */
    public Attribute<Integer> atk() {
        return atk;
    }

    /**
     * Returns the player's character card.
     *
     * @return the player's character card
     */
    public CharacterCard character() {
        return character;
    }

    /**
     * Sets the player's character card and initializes their HP and ATK attributes based on the character's values.
     *
     * @param character the character card to set
     */
    public void setCharacter(CharacterCard character) {
        this.character = character;
        this.hp = new Attribute<>(character.getHp());
        this.atk = new Attribute<>(character.getAtk());
    }

    /**
     * Returns the player's eternal card.
     *
     * @return the player's eternal card
     */
    public EternalCard eternal() {
        return eternal;
    }

    /**
     * Sets the player's eternal card.
     *
     * @param eternal the eternal card to set
     */
    public void setEternal(EternalCard eternal) {
        this.eternal = eternal;
    }

    /**
     * Returns the player's collection of loot cards.
     *
     * @return an observable list of loot cards
     */
    public ObservableList<LootCard> loot() {
        return loot;
    }

    /**
     * Returns the player's collection of treasure cards.
     *
     * @return an observable list of treasure cards
     */
    public ObservableList<TreasureCard> treasure() {
        return treasure;
    }

    /**
     * Returns the player's collection of soul cards.
     *
     * @return an observable list of soul cards
     */
    public Souls souls() {
        return souls;
    }

    /**
     * Returns the player's current amount of cents.
     *
     * @return the player's cents as a SimpleIntegerProperty
     */
    public SimpleIntegerProperty cents() {
        return cents;
    }

    public boolean isDead() {
        return hp.current().get() <= 0;
    }

    /**
     * Compares this player to another player based on their unique ID.
     *
     * @param p the player to compare to
     * @return a negative integer, zero, or a positive integer as this player is less than, equal to, or greater than the specified player
     */
    @Override
    public int compareTo(Player p) {
        return id.compareTo(p.id);
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     *
     * @param obj the reference object with which to compare
     * @return true if this player is the same as the obj argument, otherwise false
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Player p) {
            return id.equals(p.id);
        }
        return super.equals(obj);
    }
}
