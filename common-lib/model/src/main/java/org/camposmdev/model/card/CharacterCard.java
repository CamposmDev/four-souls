package org.camposmdev.model.card;

import org.camposmdev.model.card.statistic.Attribute;

public class CharacterCard extends BaseCard {
    protected Attribute life;
    protected Attribute attack;
    protected EternalCard eternalCard;

    public CharacterCard() {
        this("");
    }

    public CharacterCard(String name) {
        super(name);
        this.life = new Attribute(2);
        this.attack = new Attribute(1);
    }

    public CharacterCard(String name, int maxLife, int maxAttack) {
        super(name);
        this.life = new Attribute(maxLife);
        this.attack = new Attribute(maxAttack);
    }

    public CharacterCard(CharacterCard card) {
        this(card.name, card.life.getMax(), card.attack.getMax());
        if (card.eternalCard == null) this.eternalCard = null;
    }

    public Attribute getLife() {
        return this.life;
    }

    public Attribute getAttack() {
        return this.attack;
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
                "life=" + life +
                ", attack=" + attack +
                ", eternalCard=" + eternalCard +
                ", name='" + name + '\'' +
                '}';
    }
}
