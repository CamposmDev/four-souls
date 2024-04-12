package org.camposmdev.model.card.attribute.loot;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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

    public byte getDamage() {
        return damage;
    }

    public LootOptionEvent setDamage(byte damage) {
        this.damage = damage;
        return this;
    }

    public EntityTarget getDamageTo() {
        return damageTo;
    }

    public LootOptionEvent setDamageTo(EntityTarget damageTo) {
        this.damageTo = damageTo;
        return this;
    }

    public Reward getReward() {
        return reward;
    }

    public LootOptionEvent setReward(Reward reward) {
        this.reward = reward;
        return this;
    }

    public boolean isAttackAgain() {
        return attackAgain;
    }

    public LootOptionEvent setAttackAgain(boolean attackAgain) {
        this.attackAgain = attackAgain;
        return this;
    }

    public boolean isSummonMonster() {
        return summonMonster;
    }

    public LootOptionEvent setSummonMonster(boolean summonMonster) {
        this.summonMonster = summonMonster;
        return this;
    }

    public boolean isDestroyCurse() {
        return destroyCurse;
    }

    public LootOptionEvent setDestroyCurse(boolean destroyCurse) {
        this.destroyCurse = destroyCurse;
        return this;
    }

    public byte getPreventDamageToPlayer() {
        return preventDamageToPlayer;
    }

    public LootOptionEvent setPreventDamageToPlayer(byte preventDamageToPlayer) {
        this.preventDamageToPlayer = preventDamageToPlayer;
        return this;
    }

    @Override
    public String toString() {
        try {
            return new ObjectMapper().writeValueAsString(this);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
