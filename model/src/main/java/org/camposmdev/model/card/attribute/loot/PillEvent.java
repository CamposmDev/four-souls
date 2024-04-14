package org.camposmdev.model.card.attribute.loot;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.camposmdev.model.card.attribute.EntityTarget;
import org.camposmdev.model.card.attribute.Reward;

import java.util.List;

public class PillEvent {
    protected List<Byte> values;
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
    protected boolean giftNonEternalItem;
    protected PillItem item;

    public List<Byte> getValues() {
        return values;
    }

    public PillEvent setValues(List<Byte> values) {
        this.values = values;
        return this;
    }

    public Reward getReward() {
        return reward;
    }

    public PillEvent setReward(Reward reward) {
        this.reward = reward;
        return this;
    }

    public EntityTarget getRewardTo() {
        return rewardTo;
    }

    public PillEvent setRewardTo(EntityTarget rewardTo) {
        this.rewardTo = rewardTo;
        return this;
    }

    public byte getDiscardCents() {
        return discardCents;
    }

    public PillEvent setDiscardCents(byte discardCents) {
        this.discardCents = discardCents;
        return this;
    }

    public byte getDiscardLoot() {
        return discardLoot;
    }

    public PillEvent setDiscardLoot(byte discardLoot) {
        this.discardLoot = discardLoot;
        return this;
    }

    public byte getModPlayerDamage() {
        return modPlayerDamage;
    }

    public PillEvent setModPlayerDamage(byte modPlayerDamage) {
        this.modPlayerDamage = modPlayerDamage;
        return this;
    }

    public byte getModPlayerHitPoint() {
        return modPlayerHitPoint;
    }

    public PillEvent setModPlayerHitPoint(byte modPlayerHitPoint) {
        this.modPlayerHitPoint = modPlayerHitPoint;
        return this;
    }

    public byte getDamage() {
        return damage;
    }

    public PillEvent setDamage(byte damage) {
        this.damage = damage;
        return this;
    }

    public EntityTarget getDamageTo() {
        return damageTo;
    }

    public PillEvent setDamageTo(EntityTarget damageTo) {
        this.damageTo = damageTo;
        return this;
    }

    public boolean isRechargeAllItems() {
        return rechargeAllItems;
    }

    public PillEvent setRechargeAllItems(boolean rechargeAllItems) {
        this.rechargeAllItems = rechargeAllItems;
        return this;
    }

    public byte getModPlayerDiceRoll() {
        return modPlayerDiceRoll;
    }

    public PillEvent setModPlayerDiceRoll(byte modPlayerDiceRoll) {
        this.modPlayerDiceRoll = modPlayerDiceRoll;
        return this;
    }

    public byte getModMonsterAttackRoll() {
        return modMonsterAttackRoll;
    }

    public PillEvent setModMonsterAttackRoll(byte modMonsterAttackRoll) {
        this.modMonsterAttackRoll = modMonsterAttackRoll;
        return this;
    }

    public boolean isCancelLootEffect() {
        return cancelLootEffect;
    }

    public PillEvent setCancelLootEffect(boolean cancelLootEffect) {
        this.cancelLootEffect = cancelLootEffect;
        return this;
    }

    public byte getAllDiscardLoot() {
        return allDiscardLoot;
    }

    public PillEvent setAllDiscardLoot(byte allDiscardLoot) {
        this.allDiscardLoot = allDiscardLoot;
        return this;
    }

    public boolean isRerollYourItem() {
        return rerollYourItem;
    }

    public PillEvent setRerollYourItem(boolean rerollYourItem) {
        this.rerollYourItem = rerollYourItem;
        return this;
    }

    public boolean isRerollItemInPlay() {
        return rerollItemInPlay;
    }

    public PillEvent setRerollItemInPlay(boolean rerollItemInPlay) {
        this.rerollItemInPlay = rerollItemInPlay;
        return this;
    }

    public boolean isReollAllItems() {
        return reollAllItems;
    }

    public PillEvent setReollAllItems(boolean reollAllItems) {
        this.reollAllItems = reollAllItems;
        return this;
    }

    public byte getOtherPlayersDiscardLoot() {
        return otherPlayersDiscardLoot;
    }

    public PillEvent setOtherPlayersDiscardLoot(byte otherPlayersDiscardLoot) {
        this.otherPlayersDiscardLoot = otherPlayersDiscardLoot;
        return this;
    }

    public boolean isRerollAnyItem() {
        return rerollAnyItem;
    }

    public PillEvent setRerollAnyItem(boolean rerollAnyItem) {
        this.rerollAnyItem = rerollAnyItem;
        return this;
    }

    public byte getPutLoot() {
        return putLoot;
    }

    public PillEvent setPutLoot(byte putLoot) {
        this.putLoot = putLoot;
        return this;
    }

    public boolean isGiftNonEternalItem() {
        return giftNonEternalItem;
    }

    public PillEvent setGiftNonEternalItem(boolean giftNonEternalItem) {
        this.giftNonEternalItem = giftNonEternalItem;
        return this;
    }

    public PillItem getItem() {
        return item;
    }

    public PillEvent setItem(PillItem item) {
        this.item = item;
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

