package org.camposmdev.model.card.monster;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.camposmdev.model.card.attribute.Reward;
import org.camposmdev.model.card.attribute.monster.*;

public class MonsterCard extends BaseMonsterCard {
    private byte hitPoints;
    private byte damage;
    private byte roll;
    private Reward reward;
    private byte soul;
    private StartEvent startEvent;
    private PassiveEvent passiveEvent;
    private AttackEvent attackEvent;
    private DamageEvent damageEvent;
    private DeathEvent deathEvent;
    private EndEvent endEvent;
    private GameType game;
    private ChallengeType challenge;

    public byte getHitPoints() {
        return hitPoints;
    }

    public MonsterCard setHitPoints(byte hitPoints) {
        this.hitPoints = hitPoints;
        return this;
    }

    public byte getDamage() {
        return damage;
    }

    public MonsterCard setDamage(byte damage) {
        this.damage = damage;
        return this;
    }

    public byte getRoll() {
        return roll;
    }

    public MonsterCard setRoll(byte roll) {
        this.roll = roll;
        return this;
    }

    public Reward getReward() {
        return reward;
    }

    public MonsterCard setReward(Reward reward) {
        this.reward = reward;
        return this;
    }

    public byte getSoul() {
        return soul;
    }

    public MonsterCard setSoul(byte soul) {
        this.soul = soul;
        return this;
    }

    public StartEvent getStartEvent() {
        return startEvent;
    }

    public MonsterCard setStartEvent(StartEvent startEvent) {
        this.startEvent = startEvent;
        return this;
    }

    public PassiveEvent getPassiveEvent() {
        return passiveEvent;
    }

    public MonsterCard setPassiveEvent(PassiveEvent passiveEvent) {
        this.passiveEvent = passiveEvent;
        return this;
    }

    public AttackEvent getAttackEvent() {
        return attackEvent;
    }

    public MonsterCard setAttackEvent(AttackEvent attackEvent) {
        this.attackEvent = attackEvent;
        return this;
    }

    public DamageEvent getDamageEvent() {
        return damageEvent;
    }

    public MonsterCard setDamageEvent(DamageEvent damageEvent) {
        this.damageEvent = damageEvent;
        return this;
    }

    public DeathEvent getDeathEvent() {
        return deathEvent;
    }

    public MonsterCard setDeathEvent(DeathEvent deathEvent) {
        this.deathEvent = deathEvent;
        return this;
    }

    public EndEvent getEndEvent() {
        return endEvent;
    }

    public MonsterCard setEndEvent(EndEvent endEvent) {
        this.endEvent = endEvent;
        return this;
    }

    public GameType getGame() {
        return game;
    }

    public MonsterCard setGame(GameType game) {
        this.game = game;
        return this;
    }

    public ChallengeType getChallenge() {
        return challenge;
    }

    public MonsterCard setChallenge(ChallengeType challenge) {
        this.challenge = challenge;
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
