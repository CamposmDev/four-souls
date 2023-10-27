package org.camposmdev.model.card.builder;

import org.camposmdev.model.card.CharacterCard;
import org.camposmdev.model.card.EternalCard;

public class CharacterCardBuilder {
    private final CharacterCard card;

    public CharacterCardBuilder() {
        this.card = new CharacterCard();
    }

    public CharacterCardBuilder setName(String name) {
        this.card.setName(name);
        return this;
    }

    public CharacterCardBuilder setMaxHitPoints(int max) {
        this.card.getLife().setMax(max);
        return this;
    }

    public CharacterCardBuilder setMaxAttackPoints(int max) {
        this.card.getAttack().setMax(max);
        return this;
    }

    public CharacterCardBuilder setEternalCard(EternalCard card) {
        this.card.setEternalCard(card);
        return this;
    }

    public CharacterCard build() {
        return new CharacterCard(card);
    }
}
