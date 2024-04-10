package org.camposmdev.model.card.attribute.loot;

import org.camposmdev.model.card.attribute.EntityTarget;
import org.camposmdev.model.card.attribute.Reward;

public class LootOptionEvent {
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

    public LootOptionEvent setDamage(byte damage) {
        this.damage = damage;
        return this;
    }

    public EntityTarget damageTo() {
        return damageTo;
    }

    public LootOptionEvent setDamageTo(EntityTarget damageTo) {
        this.damageTo = damageTo;
        return this;
    }

    public Reward reward() {
        return reward;
    }

    public LootOptionEvent setReward(Reward reward) {
        this.reward = reward;
        return this;
    }

    public boolean attackAgain() {
        return attackAgain;
    }

    public LootOptionEvent setAttackAgain(boolean attackAgain) {
        this.attackAgain = attackAgain;
        return this;
    }

    public boolean summonMonster() {
        return summonMonster;
    }

    public LootOptionEvent setSummonMonster(boolean summonMonster) {
        this.summonMonster = summonMonster;
        return this;
    }

    public boolean destroyCurse() {
        return destroyCurse;
    }

    public LootOptionEvent setDestroyCurse(boolean destroyCurse) {
        this.destroyCurse = destroyCurse;
        return this;
    }

    public byte preventDamageToPlayer() {
        return preventDamageToPlayer;
    }

    public LootOptionEvent setPreventDamageToPlayer(byte preventDamageToPlayer) {
        this.preventDamageToPlayer = preventDamageToPlayer;
        return this;
    }
}
