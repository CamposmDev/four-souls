package org.camposmdev.model.card.attribute.loot;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.camposmdev.model.card.attribute.EntityTarget;
import org.camposmdev.model.card.attribute.Reward;

public class LootOption {
    protected byte damage;
    protected EntityTarget damageTo;
    protected Reward reward;
    protected boolean mayAttackAgain;
    protected boolean summonMonster;
    protected boolean destroyCurse;
    protected byte preventDamage;
    protected EntityTarget preventDamageTo;

    public byte getDamage() {
        return damage;
    }

    public LootOption setDamage(byte damage) {
        this.damage = damage;
        return this;
    }

    public EntityTarget getDamageTo() {
        return damageTo;
    }

    public LootOption setDamageTo(EntityTarget damageTo) {
        this.damageTo = damageTo;
        return this;
    }

    public Reward getReward() {
        return reward;
    }

    public LootOption setReward(Reward reward) {
        this.reward = reward;
        return this;
    }

    public boolean isMayAttackAgain() {
        return mayAttackAgain;
    }

    public LootOption setMayAttackAgain(boolean mayAttackAgain) {
        this.mayAttackAgain = mayAttackAgain;
        return this;
    }

    public boolean isSummonMonster() {
        return summonMonster;
    }

    public LootOption setSummonMonster(boolean summonMonster) {
        this.summonMonster = summonMonster;
        return this;
    }

    public boolean isDestroyCurse() {
        return destroyCurse;
    }

    public LootOption setDestroyCurse(boolean destroyCurse) {
        this.destroyCurse = destroyCurse;
        return this;
    }

    public byte getPreventDamage() {
        return preventDamage;
    }

    public LootOption setPreventDamage(byte preventDamage) {
        this.preventDamage = preventDamage;
        return this;
    }

    public EntityTarget getPreventDamageTo() {
        return preventDamageTo;
    }

    public LootOption setPreventDamageTo(EntityTarget preventDamageTo) {
        this.preventDamageTo = preventDamageTo;
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
