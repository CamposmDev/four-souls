package org.camposmdev.model.card.loot;

import org.camposmdev.model.card.attribute.EntityTarget;
import org.camposmdev.model.game.Reward;

public class OptionEvent {
    protected byte damage;
    protected EntityTarget damageTo;
    protected Reward reward;
    protected boolean attackAgain;
    protected boolean summonMonster;
    protected boolean destroyCurse;
    protected byte preventDamageToPlayer;

    public byte damage() {
        return damage;
    }

    public OptionEvent setDamage(byte damage) {
        this.damage = damage;
        return this;
    }

    public EntityTarget damageTo() {
        return damageTo;
    }

    public OptionEvent setDamageTo(EntityTarget damageTo) {
        this.damageTo = damageTo;
        return this;
    }

    public Reward reward() {
        return reward;
    }

    public OptionEvent setReward(Reward reward) {
        this.reward = reward;
        return this;
    }

    public boolean attackAgain() {
        return attackAgain;
    }

    public OptionEvent setAttackAgain(boolean attackAgain) {
        this.attackAgain = attackAgain;
        return this;
    }

    public boolean summonMonster() {
        return summonMonster;
    }

    public OptionEvent setSummonMonster(boolean summonMonster) {
        this.summonMonster = summonMonster;
        return this;
    }

    public boolean destroyCurse() {
        return destroyCurse;
    }

    public OptionEvent setDestroyCurse(boolean destroyCurse) {
        this.destroyCurse = destroyCurse;
        return this;
    }

    public byte preventDamageToPlayer() {
        return preventDamageToPlayer;
    }

    public OptionEvent setPreventDamageToPlayer(byte preventDamageToPlayer) {
        this.preventDamageToPlayer = preventDamageToPlayer;
        return this;
    }
}
