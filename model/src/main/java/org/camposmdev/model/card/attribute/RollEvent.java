package org.camposmdev.model.card.attribute;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public class RollEvent {
    private List<Byte> values;
    private byte multReward;
    private byte multPlayersDamage;
    private boolean noMonsterDamage;
    private byte modMonsterDamage;
    private byte multMonsterDamage;
    private boolean endTurn;
    private boolean stealPlayerLoot;
    private byte damage;
    private EntityTarget damageTo;
    private boolean attackAgain;
    private boolean forceAttackAgain;
    private AttributeModifier modPlayer;
    private boolean attackable;
    private boolean returnToDeck;
    private byte modMonstersDC;
    private byte modMonstersAttack;
    private byte healMonster;
    private boolean endAttack;
    private byte expandMonster;
    private Reward reward;
    private byte discardLoot;
    private byte discardCents;
    private DeckType peekDeck;
    private byte peekDeckAmount;
    private boolean peekDeckSort;
    private byte soulHitPoints;
    private boolean soul;
    private boolean skipNextTurn;
    private boolean heartItem;
    private byte rerollItem;
    private boolean guppyItem;
    private EntityTarget kill;
    private byte modAllMonstersDC;
    private boolean putOnTopOfMonsterDeck;
    private List<RollEvent> rollEvents;
    private boolean damageLink;
    private boolean discardRandomItem;
    private boolean putOnMonsterSlot;

    public List<Byte> getValues() {
        return values;
    }

    public RollEvent setValues(List<Byte> values) {
        this.values = values;
        return this;
    }

    public byte getMultReward() {
        return multReward;
    }

    public RollEvent setMultReward(byte multReward) {
        this.multReward = multReward;
        return this;
    }

    public byte getMultPlayersDamage() {
        return multPlayersDamage;
    }

    public RollEvent setMultPlayersDamage(byte multPlayersDamage) {
        this.multPlayersDamage = multPlayersDamage;
        return this;
    }

    public boolean isNoMonsterDamage() {
        return noMonsterDamage;
    }

    public RollEvent setNoMonsterDamage(boolean noMonsterDamage) {
        this.noMonsterDamage = noMonsterDamage;
        return this;
    }

    public byte getModMonsterDamage() {
        return modMonsterDamage;
    }

    public RollEvent setModMonsterDamage(byte modMonsterDamage) {
        this.modMonsterDamage = modMonsterDamage;
        return this;
    }

    public byte getMultMonsterDamage() {
        return multMonsterDamage;
    }

    public RollEvent setMultMonsterDamage(byte multMonsterDamage) {
        this.multMonsterDamage = multMonsterDamage;
        return this;
    }

    public boolean isEndTurn() {
        return endTurn;
    }

    public RollEvent setEndTurn(boolean endTurn) {
        this.endTurn = endTurn;
        return this;
    }

    public boolean isStealPlayerLoot() {
        return stealPlayerLoot;
    }

    public RollEvent setStealPlayerLoot(boolean stealPlayerLoot) {
        this.stealPlayerLoot = stealPlayerLoot;
        return this;
    }

    public byte getDamage() {
        return damage;
    }

    public RollEvent setDamage(byte damage) {
        this.damage = damage;
        return this;
    }

    public EntityTarget getDamageTo() {
        return damageTo;
    }

    public RollEvent setDamageTo(EntityTarget damageTo) {
        this.damageTo = damageTo;
        return this;
    }

    public boolean isAttackAgain() {
        return attackAgain;
    }

    public RollEvent setAttackAgain(boolean attackAgain) {
        this.attackAgain = attackAgain;
        return this;
    }

    public boolean isForceAttackAgain() {
        return forceAttackAgain;
    }

    public RollEvent setForceAttackAgain(boolean forceAttackAgain) {
        this.forceAttackAgain = forceAttackAgain;
        return this;
    }

    public AttributeModifier getModPlayer() {
        return modPlayer;
    }

    public RollEvent setModPlayer(AttributeModifier modPlayer) {
        this.modPlayer = modPlayer;
        return this;
    }

    public boolean isAttackable() {
        return attackable;
    }

    public RollEvent setAttackable(boolean attackable) {
        this.attackable = attackable;
        return this;
    }

    public boolean isReturnToDeck() {
        return returnToDeck;
    }

    public RollEvent setReturnToDeck(boolean returnToDeck) {
        this.returnToDeck = returnToDeck;
        return this;
    }

    public byte getModMonstersDC() {
        return modMonstersDC;
    }

    public RollEvent setModMonstersDC(byte modMonstersDC) {
        this.modMonstersDC = modMonstersDC;
        return this;
    }

    public byte getModMonstersAttack() {
        return modMonstersAttack;
    }

    public RollEvent setModMonstersAttack(byte modMonstersAttack) {
        this.modMonstersAttack = modMonstersAttack;
        return this;
    }

    public byte getHealMonster() {
        return healMonster;
    }

    public RollEvent setHealMonster(byte healMonster) {
        this.healMonster = healMonster;
        return this;
    }

    public boolean isEndAttack() {
        return endAttack;
    }

    public RollEvent setEndAttack(boolean endAttack) {
        this.endAttack = endAttack;
        return this;
    }

    public byte getExpandMonster() {
        return expandMonster;
    }

    public RollEvent setExpandMonster(byte expandMonster) {
        this.expandMonster = expandMonster;
        return this;
    }

    public Reward getReward() {
        return reward;
    }

    public RollEvent setReward(Reward reward) {
        this.reward = reward;
        return this;
    }

    public byte getDiscardLoot() {
        return discardLoot;
    }

    public RollEvent setDiscardLoot(byte discardLoot) {
        this.discardLoot = discardLoot;
        return this;
    }

    public byte getDiscardCents() {
        return discardCents;
    }

    public RollEvent setDiscardCents(byte discardCents) {
        this.discardCents = discardCents;
        return this;
    }

    public DeckType getPeekDeck() {
        return peekDeck;
    }

    public RollEvent setPeekDeck(DeckType peekDeck) {
        this.peekDeck = peekDeck;
        return this;
    }

    public byte getPeekDeckAmount() {
        return peekDeckAmount;
    }

    public RollEvent setPeekDeckAmount(byte peekDeckAmount) {
        this.peekDeckAmount = peekDeckAmount;
        return this;
    }

    public boolean isPeekDeckSort() {
        return peekDeckSort;
    }

    public RollEvent setPeekDeckSort(boolean peekDeckSort) {
        this.peekDeckSort = peekDeckSort;
        return this;
    }

    public byte getSoulHitPoints() {
        return soulHitPoints;
    }

    public RollEvent setSoulHitPoints(byte soulHitPoints) {
        this.soulHitPoints = soulHitPoints;
        return this;
    }

    public boolean isSoul() {
        return soul;
    }

    public RollEvent setSoul(boolean soul) {
        this.soul = soul;
        return this;
    }

    public boolean isSkipNextTurn() {
        return skipNextTurn;
    }

    public RollEvent setSkipNextTurn(boolean skipNextTurn) {
        this.skipNextTurn = skipNextTurn;
        return this;
    }

    public boolean isHeartItem() {
        return heartItem;
    }

    public RollEvent setHeartItem(boolean heartItem) {
        this.heartItem = heartItem;
        return this;
    }

    public byte getRerollItem() {
        return rerollItem;
    }

    public RollEvent setRerollItem(byte rerollItem) {
        this.rerollItem = rerollItem;
        return this;
    }

    public boolean isGuppyItem() {
        return guppyItem;
    }

    public RollEvent setGuppyItem(boolean guppyItem) {
        this.guppyItem = guppyItem;
        return this;
    }

    public EntityTarget getKill() {
        return kill;
    }

    public RollEvent setKill(EntityTarget kill) {
        this.kill = kill;
        return this;
    }

    public byte getModAllMonstersDC() {
        return modAllMonstersDC;
    }

    public RollEvent setModAllMonstersDC(byte modAllMonstersDC) {
        this.modAllMonstersDC = modAllMonstersDC;
        return this;
    }

    public boolean isPutOnTopOfMonsterDeck() {
        return putOnTopOfMonsterDeck;
    }

    public RollEvent setPutOnTopOfMonsterDeck(boolean putOnTopOfMonsterDeck) {
        this.putOnTopOfMonsterDeck = putOnTopOfMonsterDeck;
        return this;
    }

    public List<RollEvent> getRollEvents() {
        return rollEvents;
    }

    public RollEvent setRollEvents(List<RollEvent> rollEvents) {
        this.rollEvents = rollEvents;
        return this;
    }

    public boolean isDamageLink() {
        return damageLink;
    }

    public RollEvent setDamageLink(boolean damageLink) {
        this.damageLink = damageLink;
        return this;
    }

    public boolean isDiscardRandomItem() {
        return discardRandomItem;
    }

    public RollEvent setDiscardRandomItem(boolean discardRandomItem) {
        this.discardRandomItem = discardRandomItem;
        return this;
    }

    public boolean isPutOnMonsterSlot() {
        return putOnMonsterSlot;
    }

    public RollEvent setPutOnMonsterSlot(boolean putOnMonsterSlot) {
        this.putOnMonsterSlot = putOnMonsterSlot;
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
