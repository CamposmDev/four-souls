package org.camposmdev.model.card.loot;

import org.camposmdev.model.card.attribute.EntityTarget;
import org.camposmdev.model.game.Reward;

public class PillEvent {
    protected byte[] values;
    protected Reward reward;
    protected EntityTarget rewardTo;
    protected byte discardCents;
    protected byte discardLoot;
    protected byte modPlayerDamage;
    protected byte modPlayerHitPoint;
    protected byte damage;
    protected EntityTarget damageTo;
    protected boolean rechargeAllItems;
    protected byte modPlayerDiceRoll;
    protected byte modMonsterAttackRoll;
    protected boolean cancelLootEffect;
    protected byte allDiscardLoot;
    protected boolean rerollYourItem;
    protected boolean rerollItemInPlay;
    protected boolean reollAllItems;
    protected byte otherPlayersDiscardLoot;
    protected boolean rerollAnyItem;
    protected byte putLoot;

    public byte[] values() {
        return values;
    }

    public PillEvent setValues(byte[] values) {
        this.values = values;
        return this;
    }

    public Reward reward() {
        return reward;
    }

    public PillEvent setReward(Reward reward) {
        this.reward = reward;
        return this;
    }

    public EntityTarget rewardTo() {
        return rewardTo;
    }

    public PillEvent setRewardTo(EntityTarget rewardTo) {
        this.rewardTo = rewardTo;
        return this;
    }

    public byte discardCents() {
        return discardCents;
    }

    public PillEvent setDiscardCents(byte discardCents) {
        this.discardCents = discardCents;
        return this;
    }

    public byte discardLoot() {
        return discardLoot;
    }

    public PillEvent setDiscardLoot(byte discardLoot) {
        this.discardLoot = discardLoot;
        return this;
    }

    public byte modPlayerDamage() {
        return modPlayerDamage;
    }

    public PillEvent setModPlayerDamage(byte modPlayerDamage) {
        this.modPlayerDamage = modPlayerDamage;
        return this;
    }

    public byte modPlayerHitPoint() {
        return modPlayerHitPoint;
    }

    public PillEvent setModPlayerHitPoint(byte modPlayerHitPoint) {
        this.modPlayerHitPoint = modPlayerHitPoint;
        return this;
    }

    public byte damage() {
        return damage;
    }

    public PillEvent setDamage(byte damage) {
        this.damage = damage;
        return this;
    }

    public EntityTarget damageTo() {
        return damageTo;
    }

    public PillEvent setDamageTo(EntityTarget damageTo) {
        this.damageTo = damageTo;
        return this;
    }

    public boolean rechargeAllItems() {
        return rechargeAllItems;
    }

    public PillEvent setRechargeAllItems(boolean rechargeAllItems) {
        this.rechargeAllItems = rechargeAllItems;
        return this;
    }

    public byte modPlayerDiceRoll() {
        return modPlayerDiceRoll;
    }

    public PillEvent setModPlayerDiceRoll(byte modPlayerDiceRoll) {
        this.modPlayerDiceRoll = modPlayerDiceRoll;
        return this;
    }

    public byte modMonsterAttackRoll() {
        return modMonsterAttackRoll;
    }

    public PillEvent setModMonsterAttackRoll(byte modMonsterAttackRoll) {
        this.modMonsterAttackRoll = modMonsterAttackRoll;
        return this;
    }

    public boolean cancelLootEffect() {
        return cancelLootEffect;
    }

    public PillEvent setCancelLootEffect(boolean cancelLootEffect) {
        this.cancelLootEffect = cancelLootEffect;
        return this;
    }

    public byte allDiscardLoot() {
        return allDiscardLoot;
    }

    public PillEvent setAllDiscardLoot(byte allDiscardLoot) {
        this.allDiscardLoot = allDiscardLoot;
        return this;
    }

    public boolean rerollYourItem() {
        return rerollYourItem;
    }

    public PillEvent setRerollYourItem(boolean rerollYourItem) {
        this.rerollYourItem = rerollYourItem;
        return this;
    }

    public boolean rerollItemInPlay() {
        return rerollItemInPlay;
    }

    public PillEvent setRerollItemInPlay(boolean rerollItemInPlay) {
        this.rerollItemInPlay = rerollItemInPlay;
        return this;
    }

    public boolean reollAllItems() {
        return reollAllItems;
    }

    public PillEvent setReollAllItems(boolean reollAllItems) {
        this.reollAllItems = reollAllItems;
        return this;
    }

    public byte otherPlayersDiscardLoot() {
        return otherPlayersDiscardLoot;
    }

    public PillEvent setOtherPlayersDiscardLoot(byte otherPlayersDiscardLoot) {
        this.otherPlayersDiscardLoot = otherPlayersDiscardLoot;
        return this;
    }

    public boolean rerollAnyItem() {
        return rerollAnyItem;
    }

    public PillEvent setRerollAnyItem(boolean rerollAnyItem) {
        this.rerollAnyItem = rerollAnyItem;
        return this;
    }

    public byte putLoot() {
        return putLoot;
    }

    public PillEvent setPutLoot(byte putLoot) {
        this.putLoot = putLoot;
        return this;
    }
}

