package org.camposmdev.model.card.attribute.monster;

public class EndEvent {
    private boolean isEyeStabber;
    private boolean moveMonsterToAnotherSlot;
    private byte loot;
    private byte discardLoot;
    private byte lootCents;
    private boolean hasTinyHands;
    private boolean deactivateItemsAndCharacter;
    private boolean discardLootAndCentsEqualToSouls;
    private boolean lastManStanding;
    private boolean addCounter;
    private boolean lastManStandingAlt;
    private byte putInMonsterDeck;
    private boolean notAttackedCounter;

    public boolean isEyeStabber() {
        return isEyeStabber;
    }

    public EndEvent setEyeStabber(boolean eyeStabber) {
        isEyeStabber = eyeStabber;
        return this;
    }

    public boolean moveMonsterToAnotherSlot() {
        return moveMonsterToAnotherSlot;
    }

    public EndEvent setMoveMonsterToAnotherSlot(boolean moveMonsterToAnotherSlot) {
        this.moveMonsterToAnotherSlot = moveMonsterToAnotherSlot;
        return this;
    }

    public byte loot() {
        return loot;
    }

    public EndEvent setLoot(byte loot) {
        this.loot = loot;
        return this;
    }

    public byte discardLoot() {
        return discardLoot;
    }

    public EndEvent setDiscardLoot(byte discardLoot) {
        this.discardLoot = discardLoot;
        return this;
    }

    public byte lootCents() {
        return lootCents;
    }

    public EndEvent setLootCents(byte lootCents) {
        this.lootCents = lootCents;
        return this;
    }

    public boolean hasTinyHands() {
        return hasTinyHands;
    }

    public EndEvent setHasTinyHands(boolean hasTinyHands) {
        this.hasTinyHands = hasTinyHands;
        return this;
    }

    public boolean deactivateItemsAndCharacter() {
        return deactivateItemsAndCharacter;
    }

    public EndEvent setDeactivateItemsAndCharacter(boolean deactivateItemsAndCharacter) {
        this.deactivateItemsAndCharacter = deactivateItemsAndCharacter;
        return this;
    }

    public boolean discardLootAndCentsEqualToSouls() {
        return discardLootAndCentsEqualToSouls;
    }

    public EndEvent setDiscardLootAndCentsEqualToSouls(boolean discardLootAndCentsEqualToSouls) {
        this.discardLootAndCentsEqualToSouls = discardLootAndCentsEqualToSouls;
        return this;
    }

    public boolean lastManStanding() {
        return lastManStanding;
    }

    public EndEvent setLastManStanding(boolean lastManStanding) {
        this.lastManStanding = lastManStanding;
        return this;
    }

    public boolean addCounter() {
        return addCounter;
    }

    public EndEvent setAddCounter(boolean addCounter) {
        this.addCounter = addCounter;
        return this;
    }

    public boolean lastManStandingAlt() {
        return lastManStandingAlt;
    }

    public EndEvent setLastManStandingAlt(boolean lastManStandingAlt) {
        this.lastManStandingAlt = lastManStandingAlt;
        return this;
    }

    public byte putInMonsterDeck() {
        return putInMonsterDeck;
    }

    public EndEvent setPutInMonsterDeck(byte putInMonsterDeck) {
        this.putInMonsterDeck = putInMonsterDeck;
        return this;
    }

    public boolean notAttackedCounter() {
        return notAttackedCounter;
    }

    public EndEvent setNotAttackedCounter(boolean notAttackedCounter) {
        this.notAttackedCounter = notAttackedCounter;
        return this;
    }
}
