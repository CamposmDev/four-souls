package org.camposmdev.model.player;

import org.camposmdev.model.card.CharacterCard;

import java.util.UUID;

public class Player {
    private UUID id;
    private CharacterCard character;
    private PlayerHand hand;
//    private Trinkets trinkets;
    private int money;

    public Player(CharacterCard character) {
        this.id = UUID.randomUUID();
        this.character = character;
        this.hand = new PlayerHand();
        this.money = 0;
    }

    public UUID getId() {
        return id;
    }

    public CharacterCard getCharacter() {
        return character;
    }

    public PlayerHand getHand() {
        return hand;
    }

    public int getMoney() {
        return money;
    }
}
