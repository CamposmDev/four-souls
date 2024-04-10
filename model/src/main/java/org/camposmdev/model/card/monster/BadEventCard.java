package org.camposmdev.model.card.monster;

import org.camposmdev.model.card.attribute.CardType;
import org.camposmdev.model.card.attribute.EntityTarget;
import org.camposmdev.model.card.attribute.RollEvent;

public class BadEventCard extends AbstractMonsterCard {
    private byte ambush;
    private boolean ambushAlt;
    private RollEvent[] rollEvents;
    private boolean greed;
    private byte damage;
    private EntityTarget damageTo;
    private boolean bossRush;
    private byte discardLoot;
    private boolean mothersShadow;
    private boolean overflow;
    private boolean endTurn;
    private boolean dontStarve;
    private boolean bloat;
    private boolean isGoldenIdol;
    private boolean isGrubFather;
    private boolean isNightmareTick;
    private boolean isQwop;
    private boolean isTrialByTrolly;
    private boolean isCorruptedData;

    public BadEventCard() {
        super.setCardType(CardType.BEVENT);
    }

    public byte ambush() {
        return ambush;
    }

    public BadEventCard setAmbush(byte ambush) {
        this.ambush = ambush;
        return this;
    }

    public boolean ambushAlt() {
        return ambushAlt;
    }

    public BadEventCard setAmbushAlt(boolean ambushAlt) {
        this.ambushAlt = ambushAlt;
        return this;
    }

    public RollEvent[] rollEvents() {
        return rollEvents;
    }

    public BadEventCard setRollEvents(RollEvent[] rollEvents) {
        this.rollEvents = rollEvents;
        return this;
    }

    public boolean greed() {
        return greed;
    }

    public BadEventCard setGreed(boolean greed) {
        this.greed = greed;
        return this;
    }

    public byte damage() {
        return damage;
    }

    public BadEventCard setDamage(byte damage) {
        this.damage = damage;
        return this;
    }

    public EntityTarget damageTo() {
        return damageTo;
    }

    public BadEventCard setDamageTo(EntityTarget damageTo) {
        this.damageTo = damageTo;
        return this;
    }

    public boolean bossRush() {
        return bossRush;
    }

    public BadEventCard setBossRush(boolean bossRush) {
        this.bossRush = bossRush;
        return this;
    }

    public byte discardLoot() {
        return discardLoot;
    }

    public BadEventCard setDiscardLoot(byte discardLoot) {
        this.discardLoot = discardLoot;
        return this;
    }

    public boolean mothersShadow() {
        return mothersShadow;
    }

    public BadEventCard setMothersShadow(boolean mothersShadow) {
        this.mothersShadow = mothersShadow;
        return this;
    }

    public boolean overflow() {
        return overflow;
    }

    public BadEventCard setOverflow(boolean overflow) {
        this.overflow = overflow;
        return this;
    }

    public boolean cancelTurn() {
        return endTurn;
    }

    public BadEventCard setEndTurn(boolean endTurn) {
        this.endTurn = endTurn;
        return this;
    }

    public boolean dontStarve() {
        return dontStarve;
    }

    public BadEventCard setDontStarve(boolean dontStarve) {
        this.dontStarve = dontStarve;
        return this;
    }

    public boolean bloat() {
        return bloat;
    }

    public BadEventCard setBloat(boolean bloat) {
        this.bloat = bloat;
        return this;
    }

    public boolean isGoldenIdol() {
        return isGoldenIdol;
    }

    public BadEventCard setGoldenIdol(boolean goldenIdol) {
        isGoldenIdol = goldenIdol;
        return this;
    }

    public boolean isGrubFather() {
        return isGrubFather;
    }

    public BadEventCard setGrubFather(boolean grubFather) {
        isGrubFather = grubFather;
        return this;
    }

    public boolean isNightmareTick() {
        return isNightmareTick;
    }

    public BadEventCard setNightmareTick(boolean nightmareTick) {
        isNightmareTick = nightmareTick;
        return this;
    }

    public boolean isQwop() {
        return isQwop;
    }

    public BadEventCard setQwop(boolean qwop) {
        isQwop = qwop;
        return this;
    }

    public boolean isTrialByTrolly() {
        return isTrialByTrolly;
    }

    public BadEventCard setTrialByTrolly(boolean trialByTrolly) {
        isTrialByTrolly = trialByTrolly;
        return this;
    }

    public boolean isCorruptedData() {
        return isCorruptedData;
    }

    public BadEventCard setCorruptedData(boolean corruptedData) {
        isCorruptedData = corruptedData;
        return this;
    }
}
