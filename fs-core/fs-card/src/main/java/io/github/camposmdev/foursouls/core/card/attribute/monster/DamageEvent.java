package io.github.camposmdev.foursouls.core.card.attribute.monster;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.camposmdev.foursouls.core.card.attribute.EntityTarget;
import io.github.camposmdev.foursouls.core.card.attribute.RollEvent;

public class DamageEvent {
    private byte modPlayersNextAttackRoll;
    private byte damage;
    private EntityTarget damageTo;
    private RollEvent[] rollEvents;
    private boolean preventDamage;
    private RollEvent[] damageRollEvents;
    private byte modAttackRoll;
    private byte modDamage;
    private boolean theScourge;
    private boolean damageCounter;
    private boolean pooCounter;
    private boolean noteAttackRolls;
    private boolean flipNextAttackRoll;
    private boolean spiderCounter;

    public byte getModPlayersNextAttackRoll() {
        return modPlayersNextAttackRoll;
    }

    public DamageEvent setModPlayersNextAttackRoll(byte modPlayersNextAttackRoll) {
        this.modPlayersNextAttackRoll = modPlayersNextAttackRoll;
        return this;
    }

    public byte getDamage() {
        return damage;
    }

    public DamageEvent setDamage(byte damage) {
        this.damage = damage;
        return this;
    }

    public EntityTarget getDamageTo() {
        return damageTo;
    }

    public DamageEvent setDamageTo(EntityTarget damageTo) {
        this.damageTo = damageTo;
        return this;
    }

    public RollEvent[] getRollEvents() {
        return rollEvents;
    }

    public DamageEvent setRollEvents(RollEvent[] rollEvents) {
        this.rollEvents = rollEvents;
        return this;
    }

    public boolean isPreventDamage() {
        return preventDamage;
    }

    public DamageEvent setPreventDamage(boolean preventDamage) {
        this.preventDamage = preventDamage;
        return this;
    }

    public RollEvent[] getDamageRollEvents() {
        return damageRollEvents;
    }

    public DamageEvent setDamageRollEvents(RollEvent[] damageRollEvents) {
        this.damageRollEvents = damageRollEvents;
        return this;
    }

    public byte getModAttackRoll() {
        return modAttackRoll;
    }

    public DamageEvent setModAttackRoll(byte modAttackRoll) {
        this.modAttackRoll = modAttackRoll;
        return this;
    }

    public byte getModDamage() {
        return modDamage;
    }

    public DamageEvent setModDamage(byte modDamage) {
        this.modDamage = modDamage;
        return this;
    }

    public boolean isTheScourge() {
        return theScourge;
    }

    public DamageEvent setTheScourge(boolean theScourge) {
        this.theScourge = theScourge;
        return this;
    }

    public boolean isDamageCounter() {
        return damageCounter;
    }

    public DamageEvent setDamageCounter(boolean damageCounter) {
        this.damageCounter = damageCounter;
        return this;
    }

    public boolean isPooCounter() {
        return pooCounter;
    }

    public DamageEvent setPooCounter(boolean pooCounter) {
        this.pooCounter = pooCounter;
        return this;
    }

    public boolean isNoteAttackRolls() {
        return noteAttackRolls;
    }

    public DamageEvent setNoteAttackRolls(boolean noteAttackRolls) {
        this.noteAttackRolls = noteAttackRolls;
        return this;
    }

    public boolean isFlipNextAttackRoll() {
        return flipNextAttackRoll;
    }

    public DamageEvent setFlipNextAttackRoll(boolean flipNextAttackRoll) {
        this.flipNextAttackRoll = flipNextAttackRoll;
        return this;
    }

    public boolean isSpiderCounter() {
        return spiderCounter;
    }

    public DamageEvent setSpiderCounter(boolean spiderCounter) {
        this.spiderCounter = spiderCounter;
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
