package org.camposmdev.model.card.attribute.monster;

import org.camposmdev.model.card.attribute.EntityTarget;
import org.camposmdev.model.card.attribute.RollEvent;

public class AttackEvent {
    private byte loseCents;
    private EntityTarget loseCentsTarget;
    private byte healMonster;
    private byte modNextAttackRoll;
    private byte damage;
    private EntityTarget damageTarget;
    private boolean cancelEverything;
    private boolean isBigBony;
    private RollEvent rollEvent;
    private boolean isHenry;
    private byte discardLoot;
    private boolean cancelDamage;
    private boolean cancelAttack;
    private boolean killCounter;
    private boolean voteRightOrLeft;
    private boolean roll2Dice;

    public byte loseCents() {
        return loseCents;
    }

    public AttackEvent setLoseCents(byte loseCents) {
        this.loseCents = loseCents;
        return this;
    }

    public EntityTarget loseCentsTarget() {
        return loseCentsTarget;
    }

    public AttackEvent setLoseCentsTarget(EntityTarget loseCentsTarget) {
        this.loseCentsTarget = loseCentsTarget;
        return this;
    }

    public byte healMonster() {
        return healMonster;
    }

    public AttackEvent setHealMonster(byte healMonster) {
        this.healMonster = healMonster;
        return this;
    }

    public byte modNextAttackRoll() {
        return modNextAttackRoll;
    }

    public AttackEvent setModNextAttackRoll(byte modNextAttackRoll) {
        this.modNextAttackRoll = modNextAttackRoll;
        return this;
    }

    public byte damage() {
        return damage;
    }

    public AttackEvent setDamage(byte damage) {
        this.damage = damage;
        return this;
    }

    public EntityTarget damageTarget() {
        return damageTarget;
    }

    public AttackEvent setDamageTarget(EntityTarget damageTarget) {
        this.damageTarget = damageTarget;
        return this;
    }

    public boolean cancelEverything() {
        return cancelEverything;
    }

    public AttackEvent setCancelEverything(boolean cancelEverything) {
        this.cancelEverything = cancelEverything;
        return this;
    }

    public boolean isBigBony() {
        return isBigBony;
    }

    public AttackEvent setBigBony(boolean bigBony) {
        isBigBony = bigBony;
        return this;
    }

    public RollEvent rollEvent() {
        return rollEvent;
    }

    public AttackEvent setRollEvent(RollEvent rollEvent) {
        this.rollEvent = rollEvent;
        return this;
    }

    public boolean isHenry() {
        return isHenry;
    }

    public AttackEvent setHenry(boolean henry) {
        isHenry = henry;
        return this;
    }

    public byte discardLoot() {
        return discardLoot;
    }

    public AttackEvent setDiscardLoot(byte discardLoot) {
        this.discardLoot = discardLoot;
        return this;
    }

    public boolean cancelDamage() {
        return cancelDamage;
    }

    public AttackEvent setCancelDamage(boolean cancelDamage) {
        this.cancelDamage = cancelDamage;
        return this;
    }

    public boolean cancelAttack() {
        return cancelAttack;
    }

    public AttackEvent setCancelAttack(boolean cancelAttack) {
        this.cancelAttack = cancelAttack;
        return this;
    }

    public boolean killCounter() {
        return killCounter;
    }

    public AttackEvent setKillCounter(boolean killCounter) {
        this.killCounter = killCounter;
        return this;
    }

    public boolean voteRightOrLeft() {
        return voteRightOrLeft;
    }

    public AttackEvent setVoteRightOrLeft(boolean voteRightOrLeft) {
        this.voteRightOrLeft = voteRightOrLeft;
        return this;
    }

    public boolean roll2Dice() {
        return roll2Dice;
    }

    public AttackEvent setRoll2Dice(boolean roll2Dice) {
        this.roll2Dice = roll2Dice;
        return this;
    }
}
