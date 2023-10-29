package org.camposmdev.model.card.character;

import org.camposmdev.model.card.BaseCard;
import org.camposmdev.model.card.eternal.EternalCard;
import org.camposmdev.model.card.statistic.Attribute;

public class CharacterCard extends BaseCard {
    protected String imgSRC;
    protected final Attribute HP;
    protected final Attribute ATK;
    protected EternalCard eternalCard;

    public CharacterCard() {
        this("", "");
    }

    public CharacterCard(String name, String imgSRC) {
        super(name);
        this.imgSRC = imgSRC;
        this.HP = new Attribute(2);
        this.ATK = new Attribute(1);
    }

    public CharacterCard(String name, String imgSRC, int maxHP, int maxATK) {
        super(name);
        this.imgSRC = imgSRC;
        this.HP = new Attribute(maxHP);
        this.ATK = new Attribute(maxATK);
    }

    public CharacterCard(CharacterCard card) {
        this(card.name, card.imgSRC, card.HP.getMax(), card.ATK.getMax());
        if (card.eternalCard == null) this.eternalCard = null;
        else this.eternalCard = card.eternalCard;
    }

    public String getImgSRC() {
        return this.imgSRC;
    }

    public void setImgSRC(String imgSRC) {
        this.imgSRC = imgSRC;
    }

    public Attribute getHP() {
        return this.HP;
    }

    public Attribute getATK() {
        return this.ATK;
    }

    public EternalCard getEternalCard() {
        return this.eternalCard;
    }

    public void setEternalCard(EternalCard eternalCard) {
        this.eternalCard = eternalCard;
    }

    @Override
    public String toString() {
        return "CharacterCard{" +
                "life=" + HP +
                ", attack=" + ATK +
                ", eternalCard=" + eternalCard +
                ", name='" + name + '\'' +
                '}';
    }
}
