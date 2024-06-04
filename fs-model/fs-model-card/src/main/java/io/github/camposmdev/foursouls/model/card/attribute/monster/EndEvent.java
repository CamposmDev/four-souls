package io.github.camposmdev.foursouls.model.card.attribute.monster;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class EndEvent {
    private boolean eyeStabber;
    private boolean moveMonsterToAnotherSlot;
    private byte loot;
    private byte discardLoot;
    private byte lootCents;
    private boolean tinyHands;
    private boolean deactivateItemsAndCharacter;
    private boolean discardLootAndCentsEqualToSouls;
    private boolean lastManStanding;
    private boolean addCounter;
    private boolean lastManStandingAlt;
    private byte putInMonsterDeck;
    private boolean notAttackedCounter;

    public boolean isEyeStabber() {
        return eyeStabber;
    }

    public EndEvent setEyeStabber(boolean eyeStabber) {
        this.eyeStabber = eyeStabber;
        return this;
    }

    public boolean isMoveMonsterToAnotherSlot() {
        return moveMonsterToAnotherSlot;
    }

    public EndEvent setMoveMonsterToAnotherSlot(boolean moveMonsterToAnotherSlot) {
        this.moveMonsterToAnotherSlot = moveMonsterToAnotherSlot;
        return this;
    }

    public byte getLoot() {
        return loot;
    }

    public EndEvent setLoot(byte loot) {
        this.loot = loot;
        return this;
    }

    public byte getDiscardLoot() {
        return discardLoot;
    }

    public EndEvent setDiscardLoot(byte discardLoot) {
        this.discardLoot = discardLoot;
        return this;
    }

    public byte getLootCents() {
        return lootCents;
    }

    public EndEvent setLootCents(byte lootCents) {
        this.lootCents = lootCents;
        return this;
    }

    public boolean isTinyHands() {
        return tinyHands;
    }

    public EndEvent setTinyHands(boolean tinyHands) {
        this.tinyHands = tinyHands;
        return this;
    }

    public boolean isDeactivateItemsAndCharacter() {
        return deactivateItemsAndCharacter;
    }

    public EndEvent setDeactivateItemsAndCharacter(boolean deactivateItemsAndCharacter) {
        this.deactivateItemsAndCharacter = deactivateItemsAndCharacter;
        return this;
    }

    public boolean isDiscardLootAndCentsEqualToSouls() {
        return discardLootAndCentsEqualToSouls;
    }

    public EndEvent setDiscardLootAndCentsEqualToSouls(boolean discardLootAndCentsEqualToSouls) {
        this.discardLootAndCentsEqualToSouls = discardLootAndCentsEqualToSouls;
        return this;
    }

    public boolean isLastManStanding() {
        return lastManStanding;
    }

    public EndEvent setLastManStanding(boolean lastManStanding) {
        this.lastManStanding = lastManStanding;
        return this;
    }

    public boolean isAddCounter() {
        return addCounter;
    }

    public EndEvent setAddCounter(boolean addCounter) {
        this.addCounter = addCounter;
        return this;
    }

    public boolean isLastManStandingAlt() {
        return lastManStandingAlt;
    }

    public EndEvent setLastManStandingAlt(boolean lastManStandingAlt) {
        this.lastManStandingAlt = lastManStandingAlt;
        return this;
    }

    public byte getPutInMonsterDeck() {
        return putInMonsterDeck;
    }

    public EndEvent setPutInMonsterDeck(byte putInMonsterDeck) {
        this.putInMonsterDeck = putInMonsterDeck;
        return this;
    }

    public boolean isNotAttackedCounter() {
        return notAttackedCounter;
    }

    public EndEvent setNotAttackedCounter(boolean notAttackedCounter) {
        this.notAttackedCounter = notAttackedCounter;
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
