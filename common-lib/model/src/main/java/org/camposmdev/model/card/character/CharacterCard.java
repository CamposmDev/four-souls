package org.camposmdev.model.card.character;

import org.camposmdev.model.Killable;
import org.camposmdev.model.card.BaseCard;
import org.camposmdev.model.card.eternal.EternalCard;
import org.camposmdev.model.card.statistic.Attribute;

public class CharacterCard extends BaseCard implements Killable {
    protected final Attribute hp;
    protected final Attribute atk;
    protected EternalCard eternalCard;

    public CharacterCard() {
        this("", "");
    }

    public CharacterCard(String name, String imgSRC) {
        super(name);
        this.hp = new Attribute(2);
        this.atk = new Attribute(1);
    }

    public CharacterCard(String name, int maxHP, int maxATK) {
        super(name);
        this.hp = new Attribute(maxHP);
        this.atk = new Attribute(maxATK);
    }

    public CharacterCard(CharacterCard card) {
        this(card.name, card.hp.getMax(), card.atk.getMax());
        if (card.eternalCard == null) this.eternalCard = null;
        else this.eternalCard = card.eternalCard;
    }

    public Attribute getHP() {
        return this.hp;
    }

    public EternalCard getEternalCard() {
        return this.eternalCard;
    }

    public void setEternalCard(EternalCard eternalCard) {
        this.eternalCard = eternalCard;
    }

    @Override
    public Attribute getATK() {
        return atk;
    }

    @Override
    public void damage(Killable entity) {
        getHP().setCurrent(getHP().getCurrent() - entity.getATK().getCurrent());
    }

    @Override
    public void attack(Killable entity) {
        entity.damage(this);
    }

    @Override
    public boolean isDead() {
        return getHP().getCurrent() <= 0;
    }

    @Override
    public String toString() {
        return "CharacterCard{" +
                "life=" + hp +
                ", attack=" + atk +
                ", eternalCard=" + eternalCard +
                ", name='" + name + '\'' +
                '}';
    }
}
