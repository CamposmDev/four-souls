package org.camposmdev.model.card.eternal;

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
    private boolean isForeverAlone, recoverCardAtStartOfTurn, recoverCard;
    private byte recoverCardAmount;
    private DeckType DeckType;
    private boolean modifyAttack;
    private byte modifyAttackAmount;
    private EntityTarget modifyAttackTo;
    private boolean isIncubus, rechargeAtEndOfTurn, cancelAttack, attackAgain;
    private boolean preventDeath, endTurn, isWoodenNickel, isVoid;
    private byte lootAmount;
    private byte discardAmount;
    private boolean isSiblingRivalry, isSpindownDice, isCeremonialBlade, isHemoptysis;
    private boolean isGello, isKeepersBargin, isAbyss, isDeadWeight;
    private boolean isLemegeton, isAnimaSola, isClassicRoller, isTheRealLeftHand;
    private byte damage;
    private EntityTarget damageTo;
    private boolean isBowAndArrow, isGirlfriend, isGravity, isEmergencyMeeting;
    private boolean isFootball, isPolarStar, isRustySpoons, isPopPop, isPinkProglottid;

    public ActiveEternalCard() {
        super.setCardType(CardType.AETERNAL);
    }

    public RerollType reroll() {
        return reroll;
    }

    public ActiveEternalCard setReroll(RerollType reroll) {
        this.reroll = reroll;
        return this;
    }

    public boolean preventDamage() {
        return preventDamage;
    }

    public ActiveEternalCard setPreventDamage(boolean preventDamage) {
        this.preventDamage = preventDamage;
        return this;
    }

    public byte preventDamageAmount() {
        return preventDamageAmount;
    }

    public ActiveEternalCard setPreventDamageAmount(byte preventDamageAmount) {
        this.preventDamageAmount = preventDamageAmount;
        return this;
    }

    public EntityTarget preventDamageFor() {
        return preventDamageFor;
    }

    public ActiveEternalCard setPreventDamageFor(EntityTarget preventDamageFor) {
        this.preventDamageFor = preventDamageFor;
        return this;
    }

    public org.camposmdev.model.card.attribute.DeckType peekDeck() {
        return peekDeck;
    }

    public ActiveEternalCard setPeekDeck(org.camposmdev.model.card.attribute.DeckType peekDeck) {
        this.peekDeck = peekDeck;
        return this;
    }

    public byte peekDeckAmount() {
        return peekDeckAmount;
    }

    public ActiveEternalCard setPeekDeckAmount(byte peekDeckAmount) {
        this.peekDeckAmount = peekDeckAmount;
        return this;
    }

    public RollType modifyDiceRoll() {
        return modifyDiceRoll;
    }

    public ActiveEternalCard setModifyDiceRoll(RollType modifyDiceRoll) {
        this.modifyDiceRoll = modifyDiceRoll;
        return this;
    }

    public byte modifyDiceRollOffset() {
        return modifyDiceRollOffset;
    }

    public ActiveEternalCard setModifyDiceRollOffset(byte modifyDiceRollOffset) {
        this.modifyDiceRollOffset = modifyDiceRollOffset;
        return this;
    }

    public boolean isForeverAlone() {
        return isForeverAlone;
    }

    public ActiveEternalCard setForeverAlone(boolean foreverAlone) {
        isForeverAlone = foreverAlone;
        return this;
    }

    public boolean recoverCardAtStartOfTurn() {
        return recoverCardAtStartOfTurn;
    }

    public ActiveEternalCard setRecoverCardAtStartOfTurn(boolean recoverCardAtStartOfTurn) {
        this.recoverCardAtStartOfTurn = recoverCardAtStartOfTurn;
        return this;
    }

    public boolean recoverCard() {
        return recoverCard;
    }

    public ActiveEternalCard setRecoverCard(boolean recoverCard) {
        this.recoverCard = recoverCard;
        return this;
    }

    public byte recoverCardAmount() {
        return recoverCardAmount;
    }

    public ActiveEternalCard setRecoverCardAmount(byte recoverCardAmount) {
        this.recoverCardAmount = recoverCardAmount;
        return this;
    }

    public org.camposmdev.model.card.attribute.DeckType DeckType() {
        return DeckType;
    }

    public ActiveEternalCard setDeckType(org.camposmdev.model.card.attribute.DeckType deckType) {
        DeckType = deckType;
        return this;
    }

    public boolean modifyAttack() {
        return modifyAttack;
    }

    public ActiveEternalCard setModifyAttack(boolean modifyAttack) {
        this.modifyAttack = modifyAttack;
        return this;
    }

    public byte modifyAttackAmount() {
        return modifyAttackAmount;
    }

    public ActiveEternalCard setModifyAttackAmount(byte modifyAttackAmount) {
        this.modifyAttackAmount = modifyAttackAmount;
        return this;
    }

    public EntityTarget modifyAttackTo() {
        return modifyAttackTo;
    }

    public ActiveEternalCard setModifyAttackTo(EntityTarget modifyAttackTo) {
        this.modifyAttackTo = modifyAttackTo;
        return this;
    }

    public boolean isIncubus() {
        return isIncubus;
    }

    public ActiveEternalCard setIncubus(boolean incubus) {
        isIncubus = incubus;
        return this;
    }

    public boolean rechargeAtEndOfTurn() {
        return rechargeAtEndOfTurn;
    }

    public ActiveEternalCard setRechargeAtEndOfTurn(boolean rechargeAtEndOfTurn) {
        this.rechargeAtEndOfTurn = rechargeAtEndOfTurn;
        return this;
    }

    public boolean cancelAttack() {
        return cancelAttack;
    }

    public ActiveEternalCard setCancelAttack(boolean cancelAttack) {
        this.cancelAttack = cancelAttack;
        return this;
    }

    public boolean attackAgain() {
        return attackAgain;
    }

    public ActiveEternalCard setAttackAgain(boolean attackAgain) {
        this.attackAgain = attackAgain;
        return this;
    }

    public boolean preventDeath() {
        return preventDeath;
    }

    public ActiveEternalCard setPreventDeath(boolean preventDeath) {
        this.preventDeath = preventDeath;
        return this;
    }

    public boolean endTurn() {
        return endTurn;
    }

    public ActiveEternalCard setEndTurn(boolean endTurn) {
        this.endTurn = endTurn;
        return this;
    }

    public boolean isWoodenNickel() {
        return isWoodenNickel;
    }

    public ActiveEternalCard setWoodenNickel(boolean woodenNickel) {
        isWoodenNickel = woodenNickel;
        return this;
    }

    public boolean isVoid() {
        return isVoid;
    }

    public ActiveEternalCard setVoid(boolean aVoid) {
        isVoid = aVoid;
        return this;
    }

    public byte lootAmount() {
        return lootAmount;
    }

    public ActiveEternalCard setLootAmount(byte lootAmount) {
        this.lootAmount = lootAmount;
        return this;
    }

    public byte discardAmount() {
        return discardAmount;
    }

    public ActiveEternalCard setDiscardAmount(byte discardAmount) {
        this.discardAmount = discardAmount;
        return this;
    }

    public boolean isSiblingRivalry() {
        return isSiblingRivalry;
    }

    public ActiveEternalCard setSiblingRivalry(boolean siblingRivalry) {
        isSiblingRivalry = siblingRivalry;
        return this;
    }

    public boolean isSpindownDice() {
        return isSpindownDice;
    }

    public ActiveEternalCard setSpindownDice(boolean spindownDice) {
        isSpindownDice = spindownDice;
        return this;
    }

    public boolean isCeremonialBlade() {
        return isCeremonialBlade;
    }

    public ActiveEternalCard setCeremonialBlade(boolean ceremonialBlade) {
        isCeremonialBlade = ceremonialBlade;
        return this;
    }

    public boolean isHemoptysis() {
        return isHemoptysis;
    }

    public ActiveEternalCard setHemoptysis(boolean hemoptysis) {
        isHemoptysis = hemoptysis;
        return this;
    }

    public boolean isGello() {
        return isGello;
    }

    public ActiveEternalCard setGello(boolean gello) {
        isGello = gello;
        return this;
    }

    public boolean isKeepersBargin() {
        return isKeepersBargin;
    }

    public ActiveEternalCard setKeepersBargin(boolean keepersBargin) {
        isKeepersBargin = keepersBargin;
        return this;
    }

    public boolean isAbyss() {
        return isAbyss;
    }

    public ActiveEternalCard setAbyss(boolean abyss) {
        isAbyss = abyss;
        return this;
    }

    public boolean isDeadWeight() {
        return isDeadWeight;
    }

    public ActiveEternalCard setDeadWeight(boolean deadWeight) {
        isDeadWeight = deadWeight;
        return this;
    }

    public boolean isLemegeton() {
        return isLemegeton;
    }

    public ActiveEternalCard setLemegeton(boolean lemegeton) {
        isLemegeton = lemegeton;
        return this;
    }

    public boolean isAnimaSola() {
        return isAnimaSola;
    }

    public ActiveEternalCard setAnimaSola(boolean animaSola) {
        isAnimaSola = animaSola;
        return this;
    }

    public boolean isClassicRoller() {
        return isClassicRoller;
    }

    public ActiveEternalCard setClassicRoller(boolean classicRoller) {
        isClassicRoller = classicRoller;
        return this;
    }

    public boolean isTheRealLeftHand() {
        return isTheRealLeftHand;
    }

    public ActiveEternalCard setTheRealLeftHand(boolean theRealLeftHand) {
        isTheRealLeftHand = theRealLeftHand;
        return this;
    }

    public byte damage() {
        return damage;
    }

    public ActiveEternalCard setDamage(byte damage) {
        this.damage = damage;
        return this;
    }

    public EntityTarget damageTo() {
        return damageTo;
    }

    public ActiveEternalCard setDamageTo(EntityTarget damageTo) {
        this.damageTo = damageTo;
        return this;
    }

    public boolean isBowAndArrow() {
        return isBowAndArrow;
    }

    public ActiveEternalCard setBowAndArrow(boolean bowAndArrow) {
        isBowAndArrow = bowAndArrow;
        return this;
    }

    public boolean isGirlfriend() {
        return isGirlfriend;
    }

    public ActiveEternalCard setGirlfriend(boolean girlfriend) {
        isGirlfriend = girlfriend;
        return this;
    }

    public boolean isGravity() {
        return isGravity;
    }

    public ActiveEternalCard setGravity(boolean gravity) {
        isGravity = gravity;
        return this;
    }

    public boolean isEmergencyMeeting() {
        return isEmergencyMeeting;
    }

    public ActiveEternalCard setEmergencyMeeting(boolean emergencyMeeting) {
        isEmergencyMeeting = emergencyMeeting;
        return this;
    }

    public boolean isFootball() {
        return isFootball;
    }

    public ActiveEternalCard setFootball(boolean football) {
        isFootball = football;
        return this;
    }

    public boolean isPolarStar() {
        return isPolarStar;
    }

    public ActiveEternalCard setPolarStar(boolean polarStar) {
        isPolarStar = polarStar;
        return this;
    }

    public boolean isRustySpoons() {
        return isRustySpoons;
    }

    public ActiveEternalCard setRustySpoons(boolean rustySpoons) {
        isRustySpoons = rustySpoons;
        return this;
    }

    public boolean isPopPop() {
        return isPopPop;
    }

    public ActiveEternalCard setPopPop(boolean popPop) {
        isPopPop = popPop;
        return this;
    }

    public boolean isPinkProglottid() {
        return isPinkProglottid;
    }

    public ActiveEternalCard setPinkProglottid(boolean pinkProglottid) {
        isPinkProglottid = pinkProglottid;
        return this;
    }
}
