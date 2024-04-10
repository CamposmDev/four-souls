package org.camposmdev.model.card.attribute.monster;

import org.camposmdev.model.card.attribute.AttributeModifier;
import org.camposmdev.model.card.attribute.EntityTarget;
import org.camposmdev.model.card.attribute.RollEvent;
import org.camposmdev.model.card.attribute.RollListener;

public class PassiveEvent {
    private byte modMonstersAttackRoll;
    private byte modMonstersDamage;
    private byte modMonstersHitPoints;
    private boolean attackable;
    private boolean deathLink;
    private AttributeModifier modifier;
    private boolean isShopkeeper;
    private boolean isIndomitable;
    private boolean isImposter;
    private boolean isDickKnot;
    private RollListener[] rollListeners;
    private byte activeItemDamage;
    private byte deathLinkDamage;
    private EntityTarget deathLinkDamageTo;
    private boolean isCursedLilHaunt;
    private byte deathLinkExpandMonster;
    private byte deathLinkOptionalCents;
    private boolean deathLinkAttackAgain;
    private boolean endGame;
    private boolean isDogma;
    private boolean isUltraGreed;
    private byte attackablePlayers;
    private boolean deathLinkCounter;
    private byte deathLinkCounterLimit;
    private boolean forceAttackDeck;
    private boolean playerDeathCounter;
    private boolean isRadiance;
    private boolean isTheCollector;
    private boolean isTimerEater;
    private boolean isDeliriumAlt;
    private boolean isBallos;
    private boolean isItLivesAlt;
    private boolean playerDeathLinkDamage;
    private byte expandMonsterOnAttack;
    private RollEvent preattackRollEvent;
    private boolean isPride;
    private boolean nonActivePlayerDiscardLoot;
    private boolean disableNonActivePlayerLootAndActiveItems;
    private boolean forceAttack;
    private boolean isSistersVis;
    private boolean cancelAttackOn;
    private byte damageLinkModMonstersAttackRoll;
    private boolean isGrandParent;
    private boolean expandMonsterOnPlay;
    private boolean forceAttackAgain;
    private boolean isTheButcher;
    private boolean isMelquiades;
    private boolean counterModsOtherMonstersAttackRoll;
    private boolean activeOnCovered;
    private boolean preattackGoldCounter;
    private boolean deliriumPreventDamage;
    private boolean deliriumRandomDeathPenaltyItem;
    private byte attackRandomOnPlay;
    private boolean deathPlayerLink2Counters;
    private boolean counterLinkToDamage;
    private byte forceAttackOnCounter;

    public byte modMonstersAttackRoll() {
        return modMonstersAttackRoll;
    }

    public PassiveEvent setModMonstersAttackRoll(byte modMonstersAttackRoll) {
        this.modMonstersAttackRoll = modMonstersAttackRoll;
        return this;
    }

    public byte modMonstersDamage() {
        return modMonstersDamage;
    }

    public PassiveEvent setModMonstersDamage(byte modMonstersDamage) {
        this.modMonstersDamage = modMonstersDamage;
        return this;
    }

    public byte modMonstersHitPoints() {
        return modMonstersHitPoints;
    }

    public PassiveEvent setModMonstersHitPoints(byte modMonstersHitPoints) {
        this.modMonstersHitPoints = modMonstersHitPoints;
        return this;
    }

    public boolean attackable() {
        return attackable;
    }

    public PassiveEvent setAttackable(boolean attackable) {
        this.attackable = attackable;
        return this;
    }

    public boolean deathLink() {
        return deathLink;
    }

    public PassiveEvent setDeathLink(boolean deathLink) {
        this.deathLink = deathLink;
        return this;
    }

    public AttributeModifier modifier() {
        return modifier;
    }

    public PassiveEvent setModifier(AttributeModifier modifier) {
        this.modifier = modifier;
        return this;
    }

    public boolean isShopkeeper() {
        return isShopkeeper;
    }

    public PassiveEvent setShopkeeper(boolean shopkeeper) {
        isShopkeeper = shopkeeper;
        return this;
    }

    public boolean isIndomitable() {
        return isIndomitable;
    }

    public PassiveEvent setIndomitable(boolean indomitable) {
        isIndomitable = indomitable;
        return this;
    }

    public boolean isImposter() {
        return isImposter;
    }

    public PassiveEvent setImposter(boolean imposter) {
        isImposter = imposter;
        return this;
    }

    public boolean isDickKnot() {
        return isDickKnot;
    }

    public PassiveEvent setDickKnot(boolean dickKnot) {
        isDickKnot = dickKnot;
        return this;
    }

    public RollListener[] rollListeners() {
        return rollListeners;
    }

    public PassiveEvent setRollListeners(RollListener[] rollListeners) {
        this.rollListeners = rollListeners;
        return this;
    }

    public byte activeItemDamage() {
        return activeItemDamage;
    }

    public PassiveEvent setActiveItemDamage(byte activeItemDamage) {
        this.activeItemDamage = activeItemDamage;
        return this;
    }

    public byte deathLinkDamage() {
        return deathLinkDamage;
    }

    public PassiveEvent setDeathLinkDamage(byte deathLinkDamage) {
        this.deathLinkDamage = deathLinkDamage;
        return this;
    }

    public EntityTarget deathLinkDamageTo() {
        return deathLinkDamageTo;
    }

    public PassiveEvent setDeathLinkDamageTo(EntityTarget deathLinkDamageTo) {
        this.deathLinkDamageTo = deathLinkDamageTo;
        return this;
    }

    public boolean isCursedLilHaunt() {
        return isCursedLilHaunt;
    }

    public PassiveEvent setCursedLilHaunt(boolean cursedLilHaunt) {
        isCursedLilHaunt = cursedLilHaunt;
        return this;
    }

    public byte deathLinkExpandMonster() {
        return deathLinkExpandMonster;
    }

    public PassiveEvent setDeathLinkExpandMonster(byte deathLinkExpandMonster) {
        this.deathLinkExpandMonster = deathLinkExpandMonster;
        return this;
    }

    public byte deathLinkOptionalCents() {
        return deathLinkOptionalCents;
    }

    public PassiveEvent setDeathLinkOptionalCents(byte deathLinkOptionalCents) {
        this.deathLinkOptionalCents = deathLinkOptionalCents;
        return this;
    }

    public boolean deathLinkAttackAgain() {
        return deathLinkAttackAgain;
    }

    public PassiveEvent setDeathLinkAttackAgain(boolean deathLinkAttackAgain) {
        this.deathLinkAttackAgain = deathLinkAttackAgain;
        return this;
    }

    public boolean endGame() {
        return endGame;
    }

    public PassiveEvent setEndGame(boolean endGame) {
        this.endGame = endGame;
        return this;
    }

    public boolean isDogma() {
        return isDogma;
    }

    public PassiveEvent setDogma(boolean dogma) {
        isDogma = dogma;
        return this;
    }

    public boolean isUltraGreed() {
        return isUltraGreed;
    }

    public PassiveEvent setUltraGreed(boolean ultraGreed) {
        isUltraGreed = ultraGreed;
        return this;
    }

    public byte attackablePlayers() {
        return attackablePlayers;
    }

    public PassiveEvent setAttackablePlayers(byte attackablePlayers) {
        this.attackablePlayers = attackablePlayers;
        return this;
    }

    public boolean deathLinkCounter() {
        return deathLinkCounter;
    }

    public PassiveEvent setDeathLinkCounter(boolean deathLinkCounter) {
        this.deathLinkCounter = deathLinkCounter;
        return this;
    }

    public byte deathLinkCounterLimit() {
        return deathLinkCounterLimit;
    }

    public PassiveEvent setDeathLinkCounterLimit(byte deathLinkCounterLimit) {
        this.deathLinkCounterLimit = deathLinkCounterLimit;
        return this;
    }

    public boolean forceAttackDeck() {
        return forceAttackDeck;
    }

    public PassiveEvent setForceAttackDeck(boolean forceAttackDeck) {
        this.forceAttackDeck = forceAttackDeck;
        return this;
    }

    public boolean playerDeathCounter() {
        return playerDeathCounter;
    }

    public PassiveEvent setPlayerDeathCounter(boolean playerDeathCounter) {
        this.playerDeathCounter = playerDeathCounter;
        return this;
    }

    public boolean isRadiance() {
        return isRadiance;
    }

    public PassiveEvent setRadiance(boolean radiance) {
        isRadiance = radiance;
        return this;
    }

    public boolean isTheCollector() {
        return isTheCollector;
    }

    public PassiveEvent setTheCollector(boolean theCollector) {
        isTheCollector = theCollector;
        return this;
    }

    public boolean isTimerEater() {
        return isTimerEater;
    }

    public PassiveEvent setTimerEater(boolean timerEater) {
        isTimerEater = timerEater;
        return this;
    }

    public boolean isDeliriumAlt() {
        return isDeliriumAlt;
    }

    public PassiveEvent setDeliriumAlt(boolean deliriumAlt) {
        isDeliriumAlt = deliriumAlt;
        return this;
    }

    public boolean isBallos() {
        return isBallos;
    }

    public PassiveEvent setBallos(boolean ballos) {
        isBallos = ballos;
        return this;
    }

    public boolean isItLivesAlt() {
        return isItLivesAlt;
    }

    public PassiveEvent setItLivesAlt(boolean itLivesAlt) {
        isItLivesAlt = itLivesAlt;
        return this;
    }

    public boolean playerDeathLinkDamage() {
        return playerDeathLinkDamage;
    }

    public PassiveEvent setPlayerDeathLinkDamage(boolean playerDeathLinkDamage) {
        this.playerDeathLinkDamage = playerDeathLinkDamage;
        return this;
    }

    public byte expandMonsterOnAttack() {
        return expandMonsterOnAttack;
    }

    public PassiveEvent setExpandMonsterOnAttack(byte expandMonsterOnAttack) {
        this.expandMonsterOnAttack = expandMonsterOnAttack;
        return this;
    }

    public RollEvent preattackRollEvent() {
        return preattackRollEvent;
    }

    public PassiveEvent setPreattackRollEvent(RollEvent preattackRollEvent) {
        this.preattackRollEvent = preattackRollEvent;
        return this;
    }

    public boolean isPride() {
        return isPride;
    }

    public PassiveEvent setPride(boolean pride) {
        isPride = pride;
        return this;
    }

    public boolean nonActivePlayerDiscardLoot() {
        return nonActivePlayerDiscardLoot;
    }

    public PassiveEvent setNonActivePlayerDiscardLoot(boolean nonActivePlayerDiscardLoot) {
        this.nonActivePlayerDiscardLoot = nonActivePlayerDiscardLoot;
        return this;
    }

    public boolean disableNonActivePlayerLootAndActiveItems() {
        return disableNonActivePlayerLootAndActiveItems;
    }

    public PassiveEvent setDisableNonActivePlayerLootAndActiveItems(boolean disableNonActivePlayerLootAndActiveItems) {
        this.disableNonActivePlayerLootAndActiveItems = disableNonActivePlayerLootAndActiveItems;
        return this;
    }

    public boolean forceAttack() {
        return forceAttack;
    }

    public PassiveEvent setForceAttack(boolean forceAttack) {
        this.forceAttack = forceAttack;
        return this;
    }

    public boolean isSistersVis() {
        return isSistersVis;
    }

    public PassiveEvent setSistersVis(boolean sistersVis) {
        isSistersVis = sistersVis;
        return this;
    }

    public boolean cancelAttackOn() {
        return cancelAttackOn;
    }

    public PassiveEvent setCancelAttackOn(boolean cancelAttackOn) {
        this.cancelAttackOn = cancelAttackOn;
        return this;
    }

    public byte damageLinkModMonstersAttackRoll() {
        return damageLinkModMonstersAttackRoll;
    }

    public PassiveEvent setDamageLinkModMonstersAttackRoll(byte damageLinkModMonstersAttackRoll) {
        this.damageLinkModMonstersAttackRoll = damageLinkModMonstersAttackRoll;
        return this;
    }

    public boolean isGrandParent() {
        return isGrandParent;
    }

    public PassiveEvent setGrandParent(boolean grandParent) {
        isGrandParent = grandParent;
        return this;
    }

    public boolean expandMonsterOnPlay() {
        return expandMonsterOnPlay;
    }

    public PassiveEvent setExpandMonsterOnPlay(boolean expandMonsterOnPlay) {
        this.expandMonsterOnPlay = expandMonsterOnPlay;
        return this;
    }

    public boolean forceAttackAgain() {
        return forceAttackAgain;
    }

    public PassiveEvent setForceAttackAgain(boolean forceAttackAgain) {
        this.forceAttackAgain = forceAttackAgain;
        return this;
    }

    public boolean isTheButcher() {
        return isTheButcher;
    }

    public PassiveEvent setTheButcher(boolean theButcher) {
        isTheButcher = theButcher;
        return this;
    }

    public boolean isMelquiades() {
        return isMelquiades;
    }

    public PassiveEvent setMelquiades(boolean melquiades) {
        isMelquiades = melquiades;
        return this;
    }

    public boolean counterModsOtherMonstersAttackRoll() {
        return counterModsOtherMonstersAttackRoll;
    }

    public PassiveEvent setCounterModsOtherMonstersAttackRoll(boolean counterModsOtherMonstersAttackRoll) {
        this.counterModsOtherMonstersAttackRoll = counterModsOtherMonstersAttackRoll;
        return this;
    }

    public boolean activeOnCovered() {
        return activeOnCovered;
    }

    public PassiveEvent setActiveOnCovered(boolean activeOnCovered) {
        this.activeOnCovered = activeOnCovered;
        return this;
    }

    public boolean preattackGoldCounter() {
        return preattackGoldCounter;
    }

    public PassiveEvent setPreattackGoldCounter(boolean preattackGoldCounter) {
        this.preattackGoldCounter = preattackGoldCounter;
        return this;
    }

    public boolean deliriumPreventDamage() {
        return deliriumPreventDamage;
    }

    public PassiveEvent setDeliriumPreventDamage(boolean deliriumPreventDamage) {
        this.deliriumPreventDamage = deliriumPreventDamage;
        return this;
    }

    public boolean deliriumRandomDeathPenaltyItem() {
        return deliriumRandomDeathPenaltyItem;
    }

    public PassiveEvent setDeliriumRandomDeathPenaltyItem(boolean deliriumRandomDeathPenaltyItem) {
        this.deliriumRandomDeathPenaltyItem = deliriumRandomDeathPenaltyItem;
        return this;
    }

    public byte attackRandomOnPlay() {
        return attackRandomOnPlay;
    }

    public PassiveEvent setAttackRandomOnPlay(byte attackRandomOnPlay) {
        this.attackRandomOnPlay = attackRandomOnPlay;
        return this;
    }

    public boolean deathPlayerLink2Counters() {
        return deathPlayerLink2Counters;
    }

    public PassiveEvent setDeathPlayerLink2Counters(boolean deathPlayerLink2Counters) {
        this.deathPlayerLink2Counters = deathPlayerLink2Counters;
        return this;
    }

    public boolean counterLinkToDamage() {
        return counterLinkToDamage;
    }

    public PassiveEvent setCounterLinkToDamage(boolean counterLinkToDamage) {
        this.counterLinkToDamage = counterLinkToDamage;
        return this;
    }

    public byte forceAttackOnCounter() {
        return forceAttackOnCounter;
    }

    public PassiveEvent setForceAttackOnCounter(byte forceAttackOnCounter) {
        this.forceAttackOnCounter = forceAttackOnCounter;
        return this;
    }
}
