package io.github.camposmdev.foursouls.model.card.attribute.loot;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.camposmdev.foursouls.model.card.attribute.EntityTarget;
import io.github.camposmdev.foursouls.model.card.attribute.Reward;

public class RuneEvent {
    private byte[] values;
    private byte damage;
    private EntityTarget damageTo;
    private Reward reward;
    private EntityTarget rewardTo;
    private boolean destroyItemInPlaceAndReplace;
    private boolean rerollAnyItem;
    private byte discardHandThenLoot;

    public byte[] getValues() {
        return values;
    }

    public RuneEvent setValues(byte[] values) {
        this.values = values;
        return this;
    }

    public byte getDamage() {
        return damage;
    }

    public RuneEvent setDamage(byte damage) {
        this.damage = damage;
        return this;
    }

    public EntityTarget getDamageTo() {
        return damageTo;
    }

    public RuneEvent setDamageTo(EntityTarget damageTo) {
        this.damageTo = damageTo;
        return this;
    }

    public Reward getReward() {
        return reward;
    }

    public RuneEvent setReward(Reward reward) {
        this.reward = reward;
        return this;
    }

    public EntityTarget getRewardTo() {
        return rewardTo;
    }

    public RuneEvent setRewardTo(EntityTarget rewardTo) {
        this.rewardTo = rewardTo;
        return this;
    }

    public boolean isDestroyItemInPlaceAndReplace() {
        return destroyItemInPlaceAndReplace;
    }

    public RuneEvent setDestroyItemInPlaceAndReplace(boolean destroyItemInPlaceAndReplace) {
        this.destroyItemInPlaceAndReplace = destroyItemInPlaceAndReplace;
        return this;
    }

    public boolean isRerollAnyItem() {
        return rerollAnyItem;
    }

    public RuneEvent setRerollAnyItem(boolean rerollAnyItem) {
        this.rerollAnyItem = rerollAnyItem;
        return this;
    }

    public byte getDiscardHandThenLoot() {
        return discardHandThenLoot;
    }

    public RuneEvent setDiscardHandThenLoot(byte discardHandThenLoot) {
        this.discardHandThenLoot = discardHandThenLoot;
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
