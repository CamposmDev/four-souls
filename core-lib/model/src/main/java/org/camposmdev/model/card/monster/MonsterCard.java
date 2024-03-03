package org.camposmdev.model.card.monster;

import org.camposmdev.model.Killable;
import org.camposmdev.model.Reward;
import org.camposmdev.model.card.BaseCard;
import org.camposmdev.model.card.statistic.Attribute;

public class MonsterCard extends BaseCard implements Killable {
    private Attribute hp;
    private Attribute atk;
    private Attribute atkRoll;
    private Reward reward;

    public MonsterCard(String name, int maxHP, int maxATK, int maxATKRoll, Reward reward) {
        super(name);
        this.hp = new Attribute(maxHP);
        this.atk = new Attribute(maxATK);
        this.atkRoll = new Attribute(maxATKRoll);
        this.reward = reward;
    }

    public Attribute getHP() {
        return hp;
    }

    @Override
    public Attribute getATK() {
        return atk;
    }

    public Attribute getATKRoll() {
        return atkRoll;
    }

    public Reward getReward() {
        return reward;
    }

    public void setReward(Reward reward) {
        this.reward = reward;
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
}
