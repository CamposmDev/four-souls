package io.github.camposmdev.foursouls.model.card.attribute.monster;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.camposmdev.foursouls.model.card.attribute.AttributeModifier;
import io.github.camposmdev.foursouls.model.card.attribute.EntityTarget;
import io.github.camposmdev.foursouls.model.card.attribute.RollEvent;
import io.github.camposmdev.foursouls.model.card.attribute.RollListener;

import java.util.List;

public class PassiveEvent {
    private byte modMonstersAttackRoll;
    private byte modMonstersDamage;
    private byte modMonstersHitPoints;
    private boolean attackable;
    private boolean deathLink;
    private AttributeModifier modifier;
    private boolean shopkeeper;
    private boolean indomitable;
    private boolean imposter;
    private boolean dickKnot;
    private List<RollListener> rollListeners;
    private byte activeItemDamage;
    private byte deathLinkDamage;
    private EntityTarget deathLinkDamageTo;
    private boolean isCursedLilHaunt;
    private byte deathLinkExpandMonster;
    private byte deathLinkOptionalCents;
    private boolean deathLinkAttackAgain;
    private boolean endGame;
    private boolean dogma;
    private boolean ultraGreed;
    private byte attackablePlayers;
    private boolean deathLinkCounter;
    private byte deathLinkCounterLimit;
    private boolean forceAttackDeck;
    private boolean playerDeathCounter;
    private boolean radiance;
    private boolean theCollector;
    private boolean timeEater;
    private boolean deliriumAlt;
    private boolean ballos;
    private boolean itLivesAlt;
    private boolean playerDeathLinkDamage;
    private byte expandMonsterOnAttack;
    private List<RollEvent> preattackRollEvents;
    private boolean pride;
    private boolean nonActivePlayerDiscardLoot;
    private boolean disableNonActivePlayerLootAndActiveItems;
    private boolean forceAttack;
    private boolean sistersVis;
    private boolean cancelAttackOn;
    private byte damageLinkModMonstersAttackRoll;
    private boolean grandParent;
    private boolean expandMonsterOnPlay;
    private boolean forceAttackAgain;
    private boolean theButcher;
    private boolean melquiades;
    private boolean counterModsOtherMonstersAttackRoll;
    private boolean activeOnCovered;
    private boolean preattackGoldCounter;
    private boolean deliriumPreventDamage;
    private boolean deliriumRandomDeathPenaltyItem;
    private byte attackRandomOnPlay;
    private boolean deathPlayerLink2Counters;
    private boolean counterLinkToDamage;
    private byte forceAttackOnCounter;

    public byte getModMonstersAttackRoll() {
        return modMonstersAttackRoll;
    }

    public PassiveEvent setModMonstersAttackRoll(byte modMonstersAttackRoll) {
        this.modMonstersAttackRoll = modMonstersAttackRoll;
        return this;
    }

    public byte getModMonstersDamage() {
        return modMonstersDamage;
    }

    public PassiveEvent setModMonstersDamage(byte modMonstersDamage) {
        this.modMonstersDamage = modMonstersDamage;
        return this;
    }

    public byte getModMonstersHitPoints() {
        return modMonstersHitPoints;
    }

    public PassiveEvent setModMonstersHitPoints(byte modMonstersHitPoints) {
        this.modMonstersHitPoints = modMonstersHitPoints;
        return this;
    }

    public boolean isAttackable() {
        return attackable;
    }

    public PassiveEvent setAttackable(boolean attackable) {
        this.attackable = attackable;
        return this;
    }

    public boolean isDeathLink() {
        return deathLink;
    }

    public PassiveEvent setDeathLink(boolean deathLink) {
        this.deathLink = deathLink;
        return this;
    }

    public AttributeModifier getModifier() {
        return modifier;
    }

    public PassiveEvent setModifier(AttributeModifier modifier) {
        this.modifier = modifier;
        return this;
    }

    public boolean isShopkeeper() {
        return shopkeeper;
    }

    public PassiveEvent setShopkeeper(boolean shopkeeper) {
        this.shopkeeper = shopkeeper;
        return this;
    }

    public boolean isIndomitable() {
        return indomitable;
    }

    public PassiveEvent setIndomitable(boolean indomitable) {
        this.indomitable = indomitable;
        return this;
    }

    public boolean isImposter() {
        return imposter;
    }

    public PassiveEvent setImposter(boolean imposter) {
        this.imposter = imposter;
        return this;
    }

    public boolean isDickKnot() {
        return dickKnot;
    }

    public PassiveEvent setDickKnot(boolean dickKnot) {
        this.dickKnot = dickKnot;
        return this;
    }

    public List<RollListener> getRollListeners() {
        return rollListeners;
    }

    public PassiveEvent setRollListeners(List<RollListener> rollListeners) {
        this.rollListeners = rollListeners;
        return this;
    }

    public byte getActiveItemDamage() {
        return activeItemDamage;
    }

    public PassiveEvent setActiveItemDamage(byte activeItemDamage) {
        this.activeItemDamage = activeItemDamage;
        return this;
    }

    public byte getDeathLinkDamage() {
        return deathLinkDamage;
    }

    public PassiveEvent setDeathLinkDamage(byte deathLinkDamage) {
        this.deathLinkDamage = deathLinkDamage;
        return this;
    }

    public EntityTarget getDeathLinkDamageTo() {
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

    public byte getDeathLinkExpandMonster() {
        return deathLinkExpandMonster;
    }

    public PassiveEvent setDeathLinkExpandMonster(byte deathLinkExpandMonster) {
        this.deathLinkExpandMonster = deathLinkExpandMonster;
        return this;
    }

    public byte getDeathLinkOptionalCents() {
        return deathLinkOptionalCents;
    }

    public PassiveEvent setDeathLinkOptionalCents(byte deathLinkOptionalCents) {
        this.deathLinkOptionalCents = deathLinkOptionalCents;
        return this;
    }

    public boolean isDeathLinkAttackAgain() {
        return deathLinkAttackAgain;
    }

    public PassiveEvent setDeathLinkAttackAgain(boolean deathLinkAttackAgain) {
        this.deathLinkAttackAgain = deathLinkAttackAgain;
        return this;
    }

    public boolean isEndGame() {
        return endGame;
    }

    public PassiveEvent setEndGame(boolean endGame) {
        this.endGame = endGame;
        return this;
    }

    public boolean isDogma() {
        return dogma;
    }

    public PassiveEvent setDogma(boolean dogma) {
        this.dogma = dogma;
        return this;
    }

    public boolean isUltraGreed() {
        return ultraGreed;
    }

    public PassiveEvent setUltraGreed(boolean ultraGreed) {
        this.ultraGreed = ultraGreed;
        return this;
    }

    public byte getAttackablePlayers() {
        return attackablePlayers;
    }

    public PassiveEvent setAttackablePlayers(byte attackablePlayers) {
        this.attackablePlayers = attackablePlayers;
        return this;
    }

    public boolean isDeathLinkCounter() {
        return deathLinkCounter;
    }

    public PassiveEvent setDeathLinkCounter(boolean deathLinkCounter) {
        this.deathLinkCounter = deathLinkCounter;
        return this;
    }

    public byte getDeathLinkCounterLimit() {
        return deathLinkCounterLimit;
    }

    public PassiveEvent setDeathLinkCounterLimit(byte deathLinkCounterLimit) {
        this.deathLinkCounterLimit = deathLinkCounterLimit;
        return this;
    }

    public boolean isForceAttackDeck() {
        return forceAttackDeck;
    }

    public PassiveEvent setForceAttackDeck(boolean forceAttackDeck) {
        this.forceAttackDeck = forceAttackDeck;
        return this;
    }

    public boolean isPlayerDeathCounter() {
        return playerDeathCounter;
    }

    public PassiveEvent setPlayerDeathCounter(boolean playerDeathCounter) {
        this.playerDeathCounter = playerDeathCounter;
        return this;
    }

    public boolean isRadiance() {
        return radiance;
    }

    public PassiveEvent setRadiance(boolean radiance) {
        this.radiance = radiance;
        return this;
    }

    public boolean isTheCollector() {
        return theCollector;
    }

    public PassiveEvent setTheCollector(boolean theCollector) {
        this.theCollector = theCollector;
        return this;
    }

    public boolean isTimeEater() {
        return timeEater;
    }

    public PassiveEvent setTimeEater(boolean timeEater) {
        this.timeEater = timeEater;
        return this;
    }

    public boolean isDeliriumAlt() {
        return deliriumAlt;
    }

    public PassiveEvent setDeliriumAlt(boolean deliriumAlt) {
        this.deliriumAlt = deliriumAlt;
        return this;
    }

    public boolean isBallos() {
        return ballos;
    }

    public PassiveEvent setBallos(boolean ballos) {
        this.ballos = ballos;
        return this;
    }

    public boolean isItLivesAlt() {
        return itLivesAlt;
    }

    public PassiveEvent setItLivesAlt(boolean itLivesAlt) {
        this.itLivesAlt = itLivesAlt;
        return this;
    }

    public boolean isPlayerDeathLinkDamage() {
        return playerDeathLinkDamage;
    }

    public PassiveEvent setPlayerDeathLinkDamage(boolean playerDeathLinkDamage) {
        this.playerDeathLinkDamage = playerDeathLinkDamage;
        return this;
    }

    public byte getExpandMonsterOnAttack() {
        return expandMonsterOnAttack;
    }

    public PassiveEvent setExpandMonsterOnAttack(byte expandMonsterOnAttack) {
        this.expandMonsterOnAttack = expandMonsterOnAttack;
        return this;
    }

    public List<RollEvent> getPreattackRollEvents() {
        return preattackRollEvents;
    }

    public PassiveEvent setPreattackRollEvents(List<RollEvent> preattackRollEvents) {
        this.preattackRollEvents = preattackRollEvents;
        return this;
    }

    public boolean isPride() {
        return pride;
    }

    public PassiveEvent setPride(boolean pride) {
        this.pride = pride;
        return this;
    }

    public boolean isNonActivePlayerDiscardLoot() {
        return nonActivePlayerDiscardLoot;
    }

    public PassiveEvent setNonActivePlayerDiscardLoot(boolean nonActivePlayerDiscardLoot) {
        this.nonActivePlayerDiscardLoot = nonActivePlayerDiscardLoot;
        return this;
    }

    public boolean isDisableNonActivePlayerLootAndActiveItems() {
        return disableNonActivePlayerLootAndActiveItems;
    }

    public PassiveEvent setDisableNonActivePlayerLootAndActiveItems(boolean disableNonActivePlayerLootAndActiveItems) {
        this.disableNonActivePlayerLootAndActiveItems = disableNonActivePlayerLootAndActiveItems;
        return this;
    }

    public boolean isForceAttack() {
        return forceAttack;
    }

    public PassiveEvent setForceAttack(boolean forceAttack) {
        this.forceAttack = forceAttack;
        return this;
    }

    public boolean isSistersVis() {
        return sistersVis;
    }

    public PassiveEvent setSistersVis(boolean sistersVis) {
        this.sistersVis = sistersVis;
        return this;
    }

    public boolean isCancelAttackOn() {
        return cancelAttackOn;
    }

    public PassiveEvent setCancelAttackOn(boolean cancelAttackOn) {
        this.cancelAttackOn = cancelAttackOn;
        return this;
    }

    public byte getDamageLinkModMonstersAttackRoll() {
        return damageLinkModMonstersAttackRoll;
    }

    public PassiveEvent setDamageLinkModMonstersAttackRoll(byte damageLinkModMonstersAttackRoll) {
        this.damageLinkModMonstersAttackRoll = damageLinkModMonstersAttackRoll;
        return this;
    }

    public boolean isGrandParent() {
        return grandParent;
    }

    public PassiveEvent setGrandParent(boolean grandParent) {
        this.grandParent = grandParent;
        return this;
    }

    public boolean isExpandMonsterOnPlay() {
        return expandMonsterOnPlay;
    }

    public PassiveEvent setExpandMonsterOnPlay(boolean expandMonsterOnPlay) {
        this.expandMonsterOnPlay = expandMonsterOnPlay;
        return this;
    }

    public boolean isForceAttackAgain() {
        return forceAttackAgain;
    }

    public PassiveEvent setForceAttackAgain(boolean forceAttackAgain) {
        this.forceAttackAgain = forceAttackAgain;
        return this;
    }

    public boolean isTheButcher() {
        return theButcher;
    }

    public PassiveEvent setTheButcher(boolean theButcher) {
        this.theButcher = theButcher;
        return this;
    }

    public boolean isMelquiades() {
        return melquiades;
    }

    public PassiveEvent setMelquiades(boolean melquiades) {
        this.melquiades = melquiades;
        return this;
    }

    public boolean isCounterModsOtherMonstersAttackRoll() {
        return counterModsOtherMonstersAttackRoll;
    }

    public PassiveEvent setCounterModsOtherMonstersAttackRoll(boolean counterModsOtherMonstersAttackRoll) {
        this.counterModsOtherMonstersAttackRoll = counterModsOtherMonstersAttackRoll;
        return this;
    }

    public boolean isActiveOnCovered() {
        return activeOnCovered;
    }

    public PassiveEvent setActiveOnCovered(boolean activeOnCovered) {
        this.activeOnCovered = activeOnCovered;
        return this;
    }

    public boolean isPreattackGoldCounter() {
        return preattackGoldCounter;
    }

    public PassiveEvent setPreattackGoldCounter(boolean preattackGoldCounter) {
        this.preattackGoldCounter = preattackGoldCounter;
        return this;
    }

    public boolean isDeliriumPreventDamage() {
        return deliriumPreventDamage;
    }

    public PassiveEvent setDeliriumPreventDamage(boolean deliriumPreventDamage) {
        this.deliriumPreventDamage = deliriumPreventDamage;
        return this;
    }

    public boolean isDeliriumRandomDeathPenaltyItem() {
        return deliriumRandomDeathPenaltyItem;
    }

    public PassiveEvent setDeliriumRandomDeathPenaltyItem(boolean deliriumRandomDeathPenaltyItem) {
        this.deliriumRandomDeathPenaltyItem = deliriumRandomDeathPenaltyItem;
        return this;
    }

    public byte getAttackRandomOnPlay() {
        return attackRandomOnPlay;
    }

    public PassiveEvent setAttackRandomOnPlay(byte attackRandomOnPlay) {
        this.attackRandomOnPlay = attackRandomOnPlay;
        return this;
    }

    public boolean isDeathPlayerLink2Counters() {
        return deathPlayerLink2Counters;
    }

    public PassiveEvent setDeathPlayerLink2Counters(boolean deathPlayerLink2Counters) {
        this.deathPlayerLink2Counters = deathPlayerLink2Counters;
        return this;
    }

    public boolean isCounterLinkToDamage() {
        return counterLinkToDamage;
    }

    public PassiveEvent setCounterLinkToDamage(boolean counterLinkToDamage) {
        this.counterLinkToDamage = counterLinkToDamage;
        return this;
    }

    public byte getForceAttackOnCounter() {
        return forceAttackOnCounter;
    }

    public PassiveEvent setForceAttackOnCounter(byte forceAttackOnCounter) {
        this.forceAttackOnCounter = forceAttackOnCounter;
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
