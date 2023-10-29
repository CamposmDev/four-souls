package org.camposmdev.model.card.builder;

import org.camposmdev.model.card.character.CharacterCard;
import org.camposmdev.model.card.eternal.EternalCard;

public class CharacterCardBuilder {
    private String name, imgSRC;
    private int maxHP;
    private int maxATK;
    private EternalCard eternalCard;

    public CharacterCardBuilder() {
        this.name = this.imgSRC = "";
        this.maxHP = 2;
        this.maxATK = 1;
    }

    public CharacterCardBuilder name(String name) {
        this.name = name;
        return this;
    }

    public CharacterCardBuilder imgSRC(String imgSRC) {
        this.imgSRC = imgSRC;
        return this;
    }

    public CharacterCardBuilder hp(int max) {
        this.maxHP = max;
        return this;
    }

    public CharacterCardBuilder atk(int max) {
        this.maxATK = max;
        return this;
    }

    public CharacterCardBuilder eternal(EternalCard card) {
        this.eternalCard = card;
        return this;
    }

    public CharacterCard build() {
        var x = new CharacterCard(name, imgSRC, maxHP, maxATK);
        x.setEternalCard(eternalCard);
        return x;
    }
}
