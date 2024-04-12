package org.camposmdev.model.card.eternal;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.camposmdev.model.card.attribute.CardType;
import org.camposmdev.model.card.attribute.DeckType;
import org.camposmdev.model.card.attribute.EntityTarget;
import org.camposmdev.model.card.attribute.RerollType;
import org.camposmdev.model.card.attribute.RollType;

public class ActiveEternalCard extends EternalCard {
    private RerollType reroll;
    private boolean preventDamage;
    private byte preventDamageAmount;
    private EntityTarget preventDamageFor;
    private DeckType peekDeck;
    private byte peekDeckAmount;
    private RollType modifyDiceRoll;
    private byte modifyDiceRollOffset;
    private boolean foreverAlone, recoverCardAtStartOfTurn, recoverCard;
    private byte recoverCardAmount;
    private DeckType DeckType;
    private boolean modifyAttack;
    private byte modifyAttackAmount;
    private EntityTarget modifyAttackTo;
    private boolean incubus, rechargeAtEndOfTurn, cancelAttack, attackAgain;
    private boolean preventDeath, endTurn, woodenNickel, voidFlag;
    private byte lootAmount;
    private byte discardAmount;
    private boolean siblingRivalry, spindownDice, ceremonialBlade, hemoptysis;
    private boolean gello, keepersBargin, abyss, deadWeight;
    private boolean lemegeton, animaSola, classicRoller, theRealLeftHand;
    private byte damage;
    private EntityTarget damageTo;
    private boolean bowAndArrow, girlFriend, gravity, emergencyMeeting;
    private boolean football, polarStar, rustySpoons, popPop, pinkProglottid;

    public ActiveEternalCard() {
        super.setCardType(CardType.AETERNAL);
    }

    public RerollType getReroll() {
        return reroll;
    }

    public ActiveEternalCard setReroll(RerollType reroll) {
        this.reroll = reroll;
        return this;
    }

    public boolean isPreventDamage() {
        return preventDamage;
    }

    public ActiveEternalCard setPreventDamage(boolean preventDamage) {
        this.preventDamage = preventDamage;
        return this;
    }

    public byte getPreventDamageAmount() {
        return preventDamageAmount;
    }

    public ActiveEternalCard setPreventDamageAmount(byte preventDamageAmount) {
        this.preventDamageAmount = preventDamageAmount;
        return this;
    }

    public EntityTarget getPreventDamageFor() {
        return preventDamageFor;
    }

    public ActiveEternalCard setPreventDamageFor(EntityTarget preventDamageFor) {
        this.preventDamageFor = preventDamageFor;
        return this;
    }

    public org.camposmdev.model.card.attribute.DeckType getPeekDeck() {
        return peekDeck;
    }

    public ActiveEternalCard setPeekDeck(org.camposmdev.model.card.attribute.DeckType peekDeck) {
        this.peekDeck = peekDeck;
        return this;
    }

    public byte getPeekDeckAmount() {
        return peekDeckAmount;
    }

    public ActiveEternalCard setPeekDeckAmount(byte peekDeckAmount) {
        this.peekDeckAmount = peekDeckAmount;
        return this;
    }

    public RollType getModifyDiceRoll() {
        return modifyDiceRoll;
    }

    public ActiveEternalCard setModifyDiceRoll(RollType modifyDiceRoll) {
        this.modifyDiceRoll = modifyDiceRoll;
        return this;
    }

    public byte getModifyDiceRollOffset() {
        return modifyDiceRollOffset;
    }

    public ActiveEternalCard setModifyDiceRollOffset(byte modifyDiceRollOffset) {
        this.modifyDiceRollOffset = modifyDiceRollOffset;
        return this;
    }

    public boolean isForeverAlone() {
        return foreverAlone;
    }

    public ActiveEternalCard setForeverAlone(boolean foreverAlone) {
        this.foreverAlone = foreverAlone;
        return this;
    }

    public boolean isRecoverCardAtStartOfTurn() {
        return recoverCardAtStartOfTurn;
    }

    public ActiveEternalCard setRecoverCardAtStartOfTurn(boolean recoverCardAtStartOfTurn) {
        this.recoverCardAtStartOfTurn = recoverCardAtStartOfTurn;
        return this;
    }

    public boolean isRecoverCard() {
        return recoverCard;
    }

    public ActiveEternalCard setRecoverCard(boolean recoverCard) {
        this.recoverCard = recoverCard;
        return this;
    }

    public byte getRecoverCardAmount() {
        return recoverCardAmount;
    }

    public ActiveEternalCard setRecoverCardAmount(byte recoverCardAmount) {
        this.recoverCardAmount = recoverCardAmount;
        return this;
    }

    public org.camposmdev.model.card.attribute.DeckType getDeckType() {
        return DeckType;
    }

    public ActiveEternalCard setDeckType(org.camposmdev.model.card.attribute.DeckType deckType) {
        DeckType = deckType;
        return this;
    }

    public boolean isModifyAttack() {
        return modifyAttack;
    }

    public ActiveEternalCard setModifyAttack(boolean modifyAttack) {
        this.modifyAttack = modifyAttack;
        return this;
    }

    public byte getModifyAttackAmount() {
        return modifyAttackAmount;
    }

    public ActiveEternalCard setModifyAttackAmount(byte modifyAttackAmount) {
        this.modifyAttackAmount = modifyAttackAmount;
        return this;
    }

    public EntityTarget getModifyAttackTo() {
        return modifyAttackTo;
    }

    public ActiveEternalCard setModifyAttackTo(EntityTarget modifyAttackTo) {
        this.modifyAttackTo = modifyAttackTo;
        return this;
    }

    public boolean isIncubus() {
        return incubus;
    }

    public ActiveEternalCard setIncubus(boolean incubus) {
        this.incubus = incubus;
        return this;
    }

    public boolean isRechargeAtEndOfTurn() {
        return rechargeAtEndOfTurn;
    }

    public ActiveEternalCard setRechargeAtEndOfTurn(boolean rechargeAtEndOfTurn) {
        this.rechargeAtEndOfTurn = rechargeAtEndOfTurn;
        return this;
    }

    public boolean isCancelAttack() {
        return cancelAttack;
    }

    public ActiveEternalCard setCancelAttack(boolean cancelAttack) {
        this.cancelAttack = cancelAttack;
        return this;
    }

    public boolean isAttackAgain() {
        return attackAgain;
    }

    public ActiveEternalCard setAttackAgain(boolean attackAgain) {
        this.attackAgain = attackAgain;
        return this;
    }

    public boolean isPreventDeath() {
        return preventDeath;
    }

    public ActiveEternalCard setPreventDeath(boolean preventDeath) {
        this.preventDeath = preventDeath;
        return this;
    }

    public boolean isEndTurn() {
        return endTurn;
    }

    public ActiveEternalCard setEndTurn(boolean endTurn) {
        this.endTurn = endTurn;
        return this;
    }

    public boolean isWoodenNickel() {
        return woodenNickel;
    }

    public ActiveEternalCard setWoodenNickel(boolean woodenNickel) {
        this.woodenNickel = woodenNickel;
        return this;
    }

    public boolean isVoidFlag() {
        return voidFlag;
    }

    public ActiveEternalCard setVoidFlag(boolean voidFlag) {
        this.voidFlag = voidFlag;
        return this;
    }

    public byte getLootAmount() {
        return lootAmount;
    }

    public ActiveEternalCard setLootAmount(byte lootAmount) {
        this.lootAmount = lootAmount;
        return this;
    }

    public byte getDiscardAmount() {
        return discardAmount;
    }

    public ActiveEternalCard setDiscardAmount(byte discardAmount) {
        this.discardAmount = discardAmount;
        return this;
    }

    public boolean isSiblingRivalry() {
        return siblingRivalry;
    }

    public ActiveEternalCard setSiblingRivalry(boolean siblingRivalry) {
        this.siblingRivalry = siblingRivalry;
        return this;
    }

    public boolean isSpindownDice() {
        return spindownDice;
    }

    public ActiveEternalCard setSpindownDice(boolean spindownDice) {
        this.spindownDice = spindownDice;
        return this;
    }

    public boolean isCeremonialBlade() {
        return ceremonialBlade;
    }

    public ActiveEternalCard setCeremonialBlade(boolean ceremonialBlade) {
        this.ceremonialBlade = ceremonialBlade;
        return this;
    }

    public boolean isHemoptysis() {
        return hemoptysis;
    }

    public ActiveEternalCard setHemoptysis(boolean hemoptysis) {
        this.hemoptysis = hemoptysis;
        return this;
    }

    public boolean isGello() {
        return gello;
    }

    public ActiveEternalCard setGello(boolean gello) {
        this.gello = gello;
        return this;
    }

    public boolean isKeepersBargin() {
        return keepersBargin;
    }

    public ActiveEternalCard setKeepersBargin(boolean keepersBargin) {
        this.keepersBargin = keepersBargin;
        return this;
    }

    public boolean isAbyss() {
        return abyss;
    }

    public ActiveEternalCard setAbyss(boolean abyss) {
        this.abyss = abyss;
        return this;
    }

    public boolean isDeadWeight() {
        return deadWeight;
    }

    public ActiveEternalCard setDeadWeight(boolean deadWeight) {
        this.deadWeight = deadWeight;
        return this;
    }

    public boolean isLemegeton() {
        return lemegeton;
    }

    public ActiveEternalCard setLemegeton(boolean lemegeton) {
        this.lemegeton = lemegeton;
        return this;
    }

    public boolean isAnimaSola() {
        return animaSola;
    }

    public ActiveEternalCard setAnimaSola(boolean animaSola) {
        this.animaSola = animaSola;
        return this;
    }

    public boolean isClassicRoller() {
        return classicRoller;
    }

    public ActiveEternalCard setClassicRoller(boolean classicRoller) {
        this.classicRoller = classicRoller;
        return this;
    }

    public boolean isTheRealLeftHand() {
        return theRealLeftHand;
    }

    public ActiveEternalCard setTheRealLeftHand(boolean theRealLeftHand) {
        this.theRealLeftHand = theRealLeftHand;
        return this;
    }

    public byte getDamage() {
        return damage;
    }

    public ActiveEternalCard setDamage(byte damage) {
        this.damage = damage;
        return this;
    }

    public EntityTarget getDamageTo() {
        return damageTo;
    }

    public ActiveEternalCard setDamageTo(EntityTarget damageTo) {
        this.damageTo = damageTo;
        return this;
    }

    public boolean isBowAndArrow() {
        return bowAndArrow;
    }

    public ActiveEternalCard setBowAndArrow(boolean bowAndArrow) {
        this.bowAndArrow = bowAndArrow;
        return this;
    }

    public boolean isGirlFriend() {
        return girlFriend;
    }

    public ActiveEternalCard setGirlFriend(boolean girlFriend) {
        this.girlFriend = girlFriend;
        return this;
    }

    public boolean isGravity() {
        return gravity;
    }

    public ActiveEternalCard setGravity(boolean gravity) {
        this.gravity = gravity;
        return this;
    }

    public boolean isEmergencyMeeting() {
        return emergencyMeeting;
    }

    public ActiveEternalCard setEmergencyMeeting(boolean emergencyMeeting) {
        this.emergencyMeeting = emergencyMeeting;
        return this;
    }

    public boolean isFootball() {
        return football;
    }

    public ActiveEternalCard setFootball(boolean football) {
        this.football = football;
        return this;
    }

    public boolean isPolarStar() {
        return polarStar;
    }

    public ActiveEternalCard setPolarStar(boolean polarStar) {
        this.polarStar = polarStar;
        return this;
    }

    public boolean isRustySpoons() {
        return rustySpoons;
    }

    public ActiveEternalCard setRustySpoons(boolean rustySpoons) {
        this.rustySpoons = rustySpoons;
        return this;
    }

    public boolean isPopPop() {
        return popPop;
    }

    public ActiveEternalCard setPopPop(boolean popPop) {
        this.popPop = popPop;
        return this;
    }

    public boolean isPinkProglottid() {
        return pinkProglottid;
    }

    public ActiveEternalCard setPinkProglottid(boolean pinkProglottid) {
        this.pinkProglottid = pinkProglottid;
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
