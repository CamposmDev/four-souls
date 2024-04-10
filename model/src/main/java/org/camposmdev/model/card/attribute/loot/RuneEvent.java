package org.camposmdev.model.card.attribute.loot;

import org.camposmdev.model.card.attribute.EntityTarget;
import org.camposmdev.model.card.attribute.Reward;

public class RuneEvent {
    private byte[] values;
    private byte damage;
    private EntityTarget damageTo;
    private Reward reward;
    private EntityTarget rewardTo;
    private boolean destroyItemInPlaceAndReplace;
    private boolean rerollAnyItem;
    private byte discardHandThenLoot;

    public byte[] values() {
        return values;
    }

    public RuneEvent setValues(byte[] values) {
        this.values = values;
        return this;
    }

    public byte damage() {
        return damage;
    }

    public RuneEvent setDamage(byte damage) {
        this.damage = damage;
        return this;
    }

    public EntityTarget damageTo() {
        return damageTo;
    }

    public RuneEvent setDamageTo(EntityTarget damageTo) {
        this.damageTo = damageTo;
        return this;
    }

    public Reward reward() {
        return reward;
    }

    public RuneEvent setReward(Reward reward) {
        this.reward = reward;
        return this;
    }

    public EntityTarget rewardTo() {
        return rewardTo;
    }

    public RuneEvent setRewardTo(EntityTarget rewardTo) {
        this.rewardTo = rewardTo;
        return this;
    }

    public boolean destroyItemInPlaceAndReplace() {
        return destroyItemInPlaceAndReplace;
    }

    public RuneEvent setDestroyItemInPlaceAndReplace(boolean destroyItemInPlaceAndReplace) {
        this.destroyItemInPlaceAndReplace = destroyItemInPlaceAndReplace;
        return this;
    }

    public boolean rerollAnyItem() {
        return rerollAnyItem;
    }

    public RuneEvent setRerollAnyItem(boolean rerollAnyItem) {
        this.rerollAnyItem = rerollAnyItem;
        return this;
    }

    public byte discardHandThenLoot() {
        return discardHandThenLoot;
    }

    public RuneEvent setDiscardHandThenLoot(byte discardHandThenLoot) {
        this.discardHandThenLoot = discardHandThenLoot;
        return this;
    }
}
