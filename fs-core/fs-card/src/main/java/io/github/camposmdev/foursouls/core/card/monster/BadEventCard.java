package io.github.camposmdev.foursouls.core.card.monster;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.camposmdev.foursouls.core.card.attribute.CardType;
import io.github.camposmdev.foursouls.core.card.attribute.EntityTarget;
import io.github.camposmdev.foursouls.core.card.attribute.RollEvent;

import java.util.List;

public class BadEventCard extends BaseMonsterCard {
    private byte ambush;
    private boolean ambushAlt;
    private List<RollEvent> rollEvents;
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
    private boolean goldenIdol;
    private boolean grubFather;
    private boolean nightmareTick;
    private boolean qwop;
    private boolean trialByTrolly;
    private boolean corruptedData;

    public BadEventCard() {
        super.setCardType(CardType.BEVENT);
    }

    public byte getAmbush() {
        return ambush;
    }

    public BadEventCard setAmbush(byte ambush) {
        this.ambush = ambush;
        return this;
    }

    public boolean isAmbushAlt() {
        return ambushAlt;
    }

    public BadEventCard setAmbushAlt(boolean ambushAlt) {
        this.ambushAlt = ambushAlt;
        return this;
    }

    public List<RollEvent> getRollEvents() {
        return rollEvents;
    }

    public BadEventCard setRollEvents(List<RollEvent> rollEvents) {
        this.rollEvents = rollEvents;
        return this;
    }

    public boolean isGreed() {
        return greed;
    }

    public BadEventCard setGreed(boolean greed) {
        this.greed = greed;
        return this;
    }

    public byte getDamage() {
        return damage;
    }

    public BadEventCard setDamage(byte damage) {
        this.damage = damage;
        return this;
    }

    public EntityTarget getDamageTo() {
        return damageTo;
    }

    public BadEventCard setDamageTo(EntityTarget damageTo) {
        this.damageTo = damageTo;
        return this;
    }

    public boolean isBossRush() {
        return bossRush;
    }

    public BadEventCard setBossRush(boolean bossRush) {
        this.bossRush = bossRush;
        return this;
    }

    public byte getDiscardLoot() {
        return discardLoot;
    }

    public BadEventCard setDiscardLoot(byte discardLoot) {
        this.discardLoot = discardLoot;
        return this;
    }

    public boolean isMothersShadow() {
        return mothersShadow;
    }

    public BadEventCard setMothersShadow(boolean mothersShadow) {
        this.mothersShadow = mothersShadow;
        return this;
    }

    public boolean isOverflow() {
        return overflow;
    }

    public BadEventCard setOverflow(boolean overflow) {
        this.overflow = overflow;
        return this;
    }

    public boolean isEndTurn() {
        return endTurn;
    }

    public BadEventCard setEndTurn(boolean endTurn) {
        this.endTurn = endTurn;
        return this;
    }

    public boolean isDontStarve() {
        return dontStarve;
    }

    public BadEventCard setDontStarve(boolean dontStarve) {
        this.dontStarve = dontStarve;
        return this;
    }

    public boolean isBloat() {
        return bloat;
    }

    public BadEventCard setBloat(boolean bloat) {
        this.bloat = bloat;
        return this;
    }

    public boolean isGoldenIdol() {
        return goldenIdol;
    }

    public BadEventCard setGoldenIdol(boolean goldenIdol) {
        this.goldenIdol = goldenIdol;
        return this;
    }

    public boolean isGrubFather() {
        return grubFather;
    }

    public BadEventCard setGrubFather(boolean grubFather) {
        this.grubFather = grubFather;
        return this;
    }

    public boolean isNightmareTick() {
        return nightmareTick;
    }

    public BadEventCard setNightmareTick(boolean nightmareTick) {
        this.nightmareTick = nightmareTick;
        return this;
    }

    public boolean isQwop() {
        return qwop;
    }

    public BadEventCard setQwop(boolean qwop) {
        this.qwop = qwop;
        return this;
    }

    public boolean isTrialByTrolly() {
        return trialByTrolly;
    }

    public BadEventCard setTrialByTrolly(boolean trialByTrolly) {
        this.trialByTrolly = trialByTrolly;
        return this;
    }

    public boolean isCorruptedData() {
        return corruptedData;
    }

    public BadEventCard setCorruptedData(boolean corruptedData) {
        this.corruptedData = corruptedData;
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
