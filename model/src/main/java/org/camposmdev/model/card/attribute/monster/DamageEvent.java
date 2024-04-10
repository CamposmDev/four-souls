package org.camposmdev.model.card.attribute.monster;

import org.camposmdev.model.card.attribute.EntityTarget;
import org.camposmdev.model.card.attribute.RollEvent;

public class DamageEvent {
    private byte modPlayersNextAttackRoll;
    private byte damage;
    private EntityTarget damageTo;
    private RollEvent[] rollEvents;
    private boolean preventDamage;
    private RollEvent[] damageRollEvents;
    private byte modAttackRoll;
    private byte modDamage;
    private boolean isTheScourge;
    private boolean damageCounter;
    private boolean pooCounter;
    private boolean noteAttackRolls;
    private boolean flipNextAttackRoll;
    private boolean spiderCounter;


    public byte modPlayersNextAttackRoll() {
        return modPlayersNextAttackRoll;
    }

    public DamageEvent setModPlayersNextAttackRoll(byte modPlayersNextAttackRoll) {
        this.modPlayersNextAttackRoll = modPlayersNextAttackRoll;
        return this;
    }

    public byte damage() {
        return damage;
    }

    public DamageEvent setDamage(byte damage) {
        this.damage = damage;
        return this;
    }

    public EntityTarget damageTo() {
        return damageTo;
    }

    public DamageEvent setDamageTo(EntityTarget damageTo) {
        this.damageTo = damageTo;
        return this;
    }

    public RollEvent[] rollEvent() {
        return rollEvents;
    }

    public DamageEvent setRollEvents(RollEvent[] rollEvents) {
        this.rollEvents = rollEvents;
        return this;
    }

    public boolean preventDamage() {
        return preventDamage;
    }

    public DamageEvent setPreventDamage(boolean preventDamage) {
        this.preventDamage = preventDamage;
        return this;
    }

    public RollEvent[] damageRollEvent() {
        return damageRollEvents;
    }

    public DamageEvent setDamageRollEvents(RollEvent[] damageRollEvents) {
        this.damageRollEvents = damageRollEvents;
        return this;
    }

    public byte modAttackRoll() {
        return modAttackRoll;
    }

    public DamageEvent setModAttackRoll(byte modAttackRoll) {
        this.modAttackRoll = modAttackRoll;
        return this;
    }

    public byte modDamage() {
        return modDamage;
    }

    public DamageEvent setModDamage(byte modDamage) {
        this.modDamage = modDamage;
        return this;
    }

    public boolean isTheScourge() {
        return isTheScourge;
    }

    public DamageEvent setTheScourge(boolean theScourge) {
        isTheScourge = theScourge;
        return this;
    }

    public boolean damageCounter() {
        return damageCounter;
    }

    public DamageEvent setDamageCounter(boolean damageCounter) {
        this.damageCounter = damageCounter;
        return this;
    }

    public boolean pooCounter() {
        return pooCounter;
    }

    public DamageEvent setPooCounter(boolean pooCounter) {
        this.pooCounter = pooCounter;
        return this;
    }

    public boolean noteAttackRolls() {
        return noteAttackRolls;
    }

    public DamageEvent setNoteAttackRolls(boolean noteAttackRolls) {
        this.noteAttackRolls = noteAttackRolls;
        return this;
    }

    public boolean flipNextAttackRoll() {
        return flipNextAttackRoll;
    }

    public DamageEvent setFlipNextAttackRoll(boolean flipNextAttackRoll) {
        this.flipNextAttackRoll = flipNextAttackRoll;
        return this;
    }

    public boolean spiderCounter() {
        return spiderCounter;
    }

    public DamageEvent setSpiderCounter(boolean spiderCounter) {
        this.spiderCounter = spiderCounter;
        return this;
    }
}
