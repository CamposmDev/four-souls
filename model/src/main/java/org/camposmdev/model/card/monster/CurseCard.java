package org.camposmdev.model.card.monster;

import org.camposmdev.model.card.attribute.CardType;
import org.camposmdev.model.card.attribute.monster.EndEvent;
import org.camposmdev.model.card.attribute.monster.StartEvent;

public class CurseCard extends AbstractMonsterCard {
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

    public StartEvent startEffect() {
        return startEffect;
    }

    public CurseCard setStartEffect(StartEvent startEffect) {
        this.startEffect = startEffect;
        return this;
    }

    public EndEvent endEffect() {
        return endEffect;
    }

    public CurseCard setEndEffect(EndEvent endEffect) {
        this.endEffect = endEffect;
        return this;
    }

    public boolean discardSoulOnDeath() {
        return discardSoulOnDeath;
    }

    public CurseCard setDiscardSoulOnDeath(boolean discardSoulOnDeath) {
        this.discardSoulOnDeath = discardSoulOnDeath;
        return this;
    }

    public byte modMonsterAttackRoll() {
        return modMonsterAttackRoll;
    }

    public CurseCard setModMonsterAttackRoll(byte modMonsterAttackRoll) {
        this.modMonsterAttackRoll = modMonsterAttackRoll;
        return this;
    }

    public boolean forceAttack() {
        return forceAttack;
    }

    public CurseCard setForceAttack(boolean forceAttack) {
        this.forceAttack = forceAttack;
        return this;
    }

    public boolean monsterEmpathy() {
        return monsterEmpathy;
    }

    public CurseCard setMonsterEmpathy(boolean monsterEmpathy) {
        this.monsterEmpathy = monsterEmpathy;
        return this;
    }

    public byte monsterEmpathyLoot() {
        return monsterEmpathyLoot;
    }

    public CurseCard setMonsterEmpathyLoot(byte monsterEmpathyLoot) {
        this.monsterEmpathyLoot = monsterEmpathyLoot;
        return this;
    }

    public byte monsterEmpathyCents() {
        return monsterEmpathyCents;
    }

    public CurseCard setMonsterEmpathyCents(byte monsterEmpathyCents) {
        this.monsterEmpathyCents = monsterEmpathyCents;
        return this;
    }

    public byte modMonsterDamage() {
        return modMonsterDamage;
    }

    public CurseCard setModMonsterDamage(byte modMonsterDamage) {
        this.modMonsterDamage = modMonsterDamage;
        return this;
    }

    public boolean soulless() {
        return soulless;
    }

    public CurseCard setSoulless(boolean soulless) {
        this.soulless = soulless;
        return this;
    }

    public boolean gift() {
        return gift;
    }

    public CurseCard setGift(boolean gift) {
        this.gift = gift;
        return this;
    }

    public boolean suspicious() {
        return suspicious;
    }

    public CurseCard setSuspicious(boolean suspicious) {
        this.suspicious = suspicious;
        return this;
    }
}
