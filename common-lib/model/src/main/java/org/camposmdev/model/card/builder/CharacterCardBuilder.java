package org.camposmdev.model.card.builder;

import org.camposmdev.model.card.character.CharacterCard;
import org.camposmdev.model.card.eternal.EternalCard;

public class CharacterCardBuilder {
    private final CharacterCard card;

    public CharacterCardBuilder() {
        this.card = new CharacterCard();
    }

    public CharacterCardBuilder name(String name) {
        this.card.setName(name);
        return this;
    }

    public CharacterCardBuilder imgSRC(String imgSRC) {
        this.card.setImgSRC(imgSRC);
        return this;
    }

    public CharacterCardBuilder hp(int max) {
        this.card.getHP().setMax(max);
        return this;
    }

    public CharacterCardBuilder atk(int max) {
        this.card.getATK().setMax(max);
        return this;
    }

    public CharacterCardBuilder eternalCard(EternalCard card) {
        this.card.setEternalCard(card);
        return this;
    }

    public CharacterCard build() {
        return new CharacterCard(card);
    }
}
