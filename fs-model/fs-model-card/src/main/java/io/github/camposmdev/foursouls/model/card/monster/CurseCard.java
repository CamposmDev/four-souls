package io.github.camposmdev.foursouls.model.card.monster;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.camposmdev.foursouls.model.card.attribute.monster.EndEvent;
import io.github.camposmdev.foursouls.model.card.attribute.monster.StartEvent;
import io.github.camposmdev.foursouls.model.card.attribute.CardType;

public class CurseCard extends BaseMonsterCard {
    private StartEvent startEffect;
    private EndEvent endEffect;
    private boolean discardSoulOnDeath;
    private byte modMonsterAttackRoll;
    private boolean forceAttack;
    private boolean monsterEmpathy;
    private byte monsterEmpathyLoot;
    private byte monsterEmpathyCents;
    private byte modMonsterDamage;
    private boolean soulless;
    private boolean gift;
    private boolean suspicious;
    public CurseCard() {
        super.setCardType(CardType.CURSE);
    }

    public StartEvent getStartEffect() {
        return startEffect;
    }

    public CurseCard setStartEffect(StartEvent startEffect) {
        this.startEffect = startEffect;
        return this;
    }

    public EndEvent getEndEffect() {
        return endEffect;
    }

    public CurseCard setEndEffect(EndEvent endEffect) {
        this.endEffect = endEffect;
        return this;
    }

    public boolean isDiscardSoulOnDeath() {
        return discardSoulOnDeath;
    }

    public CurseCard setDiscardSoulOnDeath(boolean discardSoulOnDeath) {
        this.discardSoulOnDeath = discardSoulOnDeath;
        return this;
    }

    public byte getModMonsterAttackRoll() {
        return modMonsterAttackRoll;
    }

    public CurseCard setModMonsterAttackRoll(byte modMonsterAttackRoll) {
        this.modMonsterAttackRoll = modMonsterAttackRoll;
        return this;
    }

    public boolean isForceAttack() {
        return forceAttack;
    }

    public CurseCard setForceAttack(boolean forceAttack) {
        this.forceAttack = forceAttack;
        return this;
    }

    public boolean isMonsterEmpathy() {
        return monsterEmpathy;
    }

    public CurseCard setMonsterEmpathy(boolean monsterEmpathy) {
        this.monsterEmpathy = monsterEmpathy;
        return this;
    }

    public byte getMonsterEmpathyLoot() {
        return monsterEmpathyLoot;
    }

    public CurseCard setMonsterEmpathyLoot(byte monsterEmpathyLoot) {
        this.monsterEmpathyLoot = monsterEmpathyLoot;
        return this;
    }

    public byte getMonsterEmpathyCents() {
        return monsterEmpathyCents;
    }

    public CurseCard setMonsterEmpathyCents(byte monsterEmpathyCents) {
        this.monsterEmpathyCents = monsterEmpathyCents;
        return this;
    }

    public byte getModMonsterDamage() {
        return modMonsterDamage;
    }

    public CurseCard setModMonsterDamage(byte modMonsterDamage) {
        this.modMonsterDamage = modMonsterDamage;
        return this;
    }

    public boolean isSoulless() {
        return soulless;
    }

    public CurseCard setSoulless(boolean soulless) {
        this.soulless = soulless;
        return this;
    }

    public boolean isGift() {
        return gift;
    }

    public CurseCard setGift(boolean gift) {
        this.gift = gift;
        return this;
    }

    public boolean isSuspicious() {
        return suspicious;
    }

    public CurseCard setSuspicious(boolean suspicious) {
        this.suspicious = suspicious;
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
