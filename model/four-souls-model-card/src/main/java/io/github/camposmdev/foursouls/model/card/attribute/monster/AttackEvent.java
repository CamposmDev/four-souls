package io.github.camposmdev.foursouls.model.card.attribute.monster;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.camposmdev.foursouls.model.card.attribute.EntityTarget;
import io.github.camposmdev.foursouls.model.card.attribute.RollEvent;

public class AttackEvent {
    private byte loseCents;
    private EntityTarget loseCentsTarget;
    private byte healMonster;
    private byte modNextAttackRoll;
    private byte damage;
    private EntityTarget damageTo;
    private boolean endTurn;
    private boolean bigBony;
    private RollEvent[] rollEvent;
    private boolean henry;
    private byte discardLoot;
    private boolean cancelDamage;
    private boolean cancelAttack;
    private boolean killCounter;
    private boolean voteRightOrLeft;
    private boolean roll2Dice;

    public byte getLoseCents() {
        return loseCents;
    }

    public AttackEvent setLoseCents(byte loseCents) {
        this.loseCents = loseCents;
        return this;
    }

    public EntityTarget getLoseCentsTarget() {
        return loseCentsTarget;
    }

    public AttackEvent setLoseCentsTarget(EntityTarget loseCentsTarget) {
        this.loseCentsTarget = loseCentsTarget;
        return this;
    }

    public byte getHealMonster() {
        return healMonster;
    }

    public AttackEvent setHealMonster(byte healMonster) {
        this.healMonster = healMonster;
        return this;
    }

    public byte getModNextAttackRoll() {
        return modNextAttackRoll;
    }

    public AttackEvent setModNextAttackRoll(byte modNextAttackRoll) {
        this.modNextAttackRoll = modNextAttackRoll;
        return this;
    }

    public byte getDamage() {
        return damage;
    }

    public AttackEvent setDamage(byte damage) {
        this.damage = damage;
        return this;
    }

    public EntityTarget getDamageTo() {
        return damageTo;
    }

    public AttackEvent setDamageTo(EntityTarget damageTo) {
        this.damageTo = damageTo;
        return this;
    }

    public boolean isEndTurn() {
        return endTurn;
    }

    public AttackEvent setEndTurn(boolean endTurn) {
        this.endTurn = endTurn;
        return this;
    }

    public boolean isBigBony() {
        return bigBony;
    }

    public AttackEvent setBigBony(boolean bigBony) {
        this.bigBony = bigBony;
        return this;
    }

    public RollEvent[] getRollEvent() {
        return rollEvent;
    }

    public AttackEvent setRollEvent(RollEvent[] rollEvent) {
        this.rollEvent = rollEvent;
        return this;
    }

    public boolean isHenry() {
        return henry;
    }

    public AttackEvent setHenry(boolean henry) {
        this.henry = henry;
        return this;
    }

    public byte getDiscardLoot() {
        return discardLoot;
    }

    public AttackEvent setDiscardLoot(byte discardLoot) {
        this.discardLoot = discardLoot;
        return this;
    }

    public boolean isCancelDamage() {
        return cancelDamage;
    }

    public AttackEvent setCancelDamage(boolean cancelDamage) {
        this.cancelDamage = cancelDamage;
        return this;
    }

    public boolean isCancelAttack() {
        return cancelAttack;
    }

    public AttackEvent setCancelAttack(boolean cancelAttack) {
        this.cancelAttack = cancelAttack;
        return this;
    }

    public boolean isKillCounter() {
        return killCounter;
    }

    public AttackEvent setKillCounter(boolean killCounter) {
        this.killCounter = killCounter;
        return this;
    }

    public boolean isVoteRightOrLeft() {
        return voteRightOrLeft;
    }

    public AttackEvent setVoteRightOrLeft(boolean voteRightOrLeft) {
        this.voteRightOrLeft = voteRightOrLeft;
        return this;
    }

    public boolean isRoll2Dice() {
        return roll2Dice;
    }

    public AttackEvent setRoll2Dice(boolean roll2Dice) {
        this.roll2Dice = roll2Dice;
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
