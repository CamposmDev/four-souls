package org.camposmdev.model.card.builder;

import org.camposmdev.model.card.character.CharacterCard;
import org.camposmdev.model.card.eternal.EternalCard;

public class CharacterCardBuilder {
    private CharacterCard card;

    public CharacterCardBuilder() {
        card = new CharacterCard();
    }

    public CharacterCardBuilder name(String name) {
        card.setName(name);
        return this;
    }

    public CharacterCardBuilder imgSRC(String imgSRC) {
        card.setImgSRC(imgSRC);
        return this;
    }

    public CharacterCardBuilder hp(int max) {
        card.getHP().setMax(max);
        return this;
    }

    public CharacterCardBuilder atk(int max) {
        card.getATK().setMax(max);
        return this;
    }

    public CharacterCardBuilder eternal(EternalCard eternal) {
        card.setEternalCard(eternal);
        return this;
    }

    public CharacterCard build() {
        return card;
    }
}
