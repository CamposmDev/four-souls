package org.camposmdev.model.card.monster;

import org.camposmdev.model.card.attribute.Reward;
import org.camposmdev.model.card.attribute.monster.*;

public class MonsterCard extends AbstractMonsterCard {
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

    public byte hitPoints() {
        return hitPoints;
    }

    public MonsterCard setHitPoints(byte hitPoints) {
        this.hitPoints = hitPoints;
        return this;
    }

    public byte damage() {
        return damage;
    }

    public MonsterCard setDamage(byte damage) {
        this.damage = damage;
        return this;
    }

    public byte roll() {
        return roll;
    }

    public MonsterCard setRoll(byte roll) {
        this.roll = roll;
        return this;
    }

    public Reward reward() {
        return reward;
    }

    public MonsterCard setReward(Reward reward) {
        this.reward = reward;
        return this;
    }

    public byte soul() {
        return soul;
    }

    public MonsterCard setSoul(byte soul) {
        this.soul = soul;
        return this;
    }

    public StartEvent startEvent() {
        return startEvent;
    }

    public MonsterCard setStartEvent(StartEvent startEvent) {
        this.startEvent = startEvent;
        return this;
    }

    public PassiveEvent passiveEvent() {
        return passiveEvent;
    }

    public MonsterCard setPassiveEvent(PassiveEvent passiveEvent) {
        this.passiveEvent = passiveEvent;
        return this;
    }

    public AttackEvent attackEvent() {
        return attackEvent;
    }

    public MonsterCard setAttackEvent(AttackEvent attackEvent) {
        this.attackEvent = attackEvent;
        return this;
    }

    public DamageEvent damageEvent() {
        return damageEvent;
    }

    public MonsterCard setDamageEvent(DamageEvent damageEvent) {
        this.damageEvent = damageEvent;
        return this;
    }

    public DeathEvent deathEvent() {
        return deathEvent;
    }

    public MonsterCard setDeathEvent(DeathEvent deathEvent) {
        this.deathEvent = deathEvent;
        return this;
    }

    public EndEvent endEvent() {
        return endEvent;
    }

    public MonsterCard setEndEvent(EndEvent endEvent) {
        this.endEvent = endEvent;
        return this;
    }

    public GameType game() {
        return game;
    }

    public MonsterCard setGame(GameType game) {
        this.game = game;
        return this;
    }

    public ChallengeType challenge() {
        return challenge;
    }

    public MonsterCard setChallenge(ChallengeType challenge) {
        this.challenge = challenge;
        return this;
    }
}
