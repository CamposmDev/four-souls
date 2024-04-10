package org.camposmdev.model.card.eternal;

import io.vertx.core.json.JsonObject;
import org.camposmdev.model.card.attribute.CardType;
import org.camposmdev.model.card.attribute.*;
import org.camposmdev.model.card.attribute.Reward;

import java.util.List;

public class PassiveEternalCard extends EternalCard {
    private boolean hasCounter;
    private byte treasureAfterDeath;
    private List<RollListener> rollListeners;
    private List<DeathListener> deathListeners;
    private DamageTakenOptions damageTaken;
    private DamageDealthRollForEffect damageDealt;
    private List<KillListener> killListeners;
    private boolean counterIsHitPoints;
    private byte counterLimit;
    private byte counterLimitResetTo;
    private Reward counterExceededReward;
    private boolean isAllowedToAttackAgain;
    /* special case for the enigma */
    private boolean isAllowedToFlipCharacter;
    private boolean isAllowedToCopyShopItem;
    private boolean isCopiedShopItemForever;
    private boolean isDeathPenaltyMoneyOn;
    private boolean isDeathPenaltyLootOn;
    /* special case for ball of tumors card */
    private boolean isBallOfTumors;
    private boolean isDeathPenaltyTreasureOn;
    /* special case for strange marble card */
    private boolean peekLootDeckAtStartToPublic;
    private boolean isAllowedToPlayMatchingLootCard;
    /* special case for possession card */
    private boolean isPossesion;
    /* special case for is you card */
    private boolean isAllowedToCopyEternalItemAtStart;
    /* special case for lollypop card */
    private boolean isLollypop;
    /* special case for hunky boys card */
    private boolean isHunkyBoys;
    /* special case for focus card */
    private boolean isFocus;
    /* special case for ring of snake */
    private boolean isRingOfSnake;

    public PassiveEternalCard() {
        super.setCardType(CardType.PETERNAL);
    }

    public boolean hasCounter() {
        return hasCounter;
    }

    public PassiveEternalCard setHasCounter(boolean hasCounter) {
        this.hasCounter = hasCounter;
        return this;
    }

    public byte treasureAfterDeath() {
        return treasureAfterDeath;
    }

    public PassiveEternalCard setTreasureAfterDeath(byte treasureAfterDeath) {
        this.treasureAfterDeath = treasureAfterDeath;
        return this;
    }

    public List<RollListener> rollListeners() {
        return rollListeners;
    }

    public PassiveEternalCard setRollListeners(List<RollListener> rollListeners) {
        this.rollListeners = rollListeners;
        return this;
    }

    public List<DeathListener> deathListeners() {
        return deathListeners;
    }

    public PassiveEternalCard setDeathListeners(List<DeathListener> deathListeners) {
        this.deathListeners = deathListeners;
        return this;
    }

    public DamageTakenOptions damageTaken() {
        return damageTaken;
    }

    public PassiveEternalCard setDamageTaken(DamageTakenOptions damageTaken) {
        this.damageTaken = damageTaken;
        return this;
    }

    public DamageDealthRollForEffect damageDealt() {
        return damageDealt;
    }

    public PassiveEternalCard setDamageDealt(DamageDealthRollForEffect damageDealt) {
        this.damageDealt = damageDealt;
        return this;
    }

    public List<KillListener> killListener() {
        return killListeners;
    }

    public PassiveEternalCard setKillListeners(List<KillListener> killListeners) {
        this.killListeners = killListeners;
        return this;
    }

    public boolean counterIsHitPoints() {
        return counterIsHitPoints;
    }

    public PassiveEternalCard setCounterIsHitPoints(boolean counterIsHitPoints) {
        this.counterIsHitPoints = counterIsHitPoints;
        return this;
    }

    public byte counterLimit() {
        return counterLimit;
    }

    public PassiveEternalCard setCounterLimit(byte counterLimit) {
        this.counterLimit = counterLimit;
        return this;
    }

    public byte counterLimitResetTo() {
        return counterLimitResetTo;
    }

    public PassiveEternalCard setCounterLimitResetTo(byte counterLimitResetTo) {
        this.counterLimitResetTo = counterLimitResetTo;
        return this;
    }

    public Reward counterExceededReward() {
        return counterExceededReward;
    }

    public PassiveEternalCard setCounterExceededReward(Reward counterExceededReward) {
        this.counterExceededReward = counterExceededReward;
        return this;
    }

    public boolean isAllowedToAttackAgain() {
        return isAllowedToAttackAgain;
    }

    public PassiveEternalCard setAllowedToAttackAgain(boolean allowedToAttackAgain) {
        isAllowedToAttackAgain = allowedToAttackAgain;
        return this;
    }

    public boolean isAllowedToFlipCharacter() {
        return isAllowedToFlipCharacter;
    }

    public PassiveEternalCard setAllowedToFlipCharacter(boolean allowedToFlipCharacter) {
        isAllowedToFlipCharacter = allowedToFlipCharacter;
        return this;
    }

    public boolean isAllowedToCopyShopItem() {
        return isAllowedToCopyShopItem;
    }

    public PassiveEternalCard setAllowedToCopyShopItem(boolean allowedToCopyShopItem) {
        isAllowedToCopyShopItem = allowedToCopyShopItem;
        return this;
    }

    public boolean isCopiedShopItemForever() {
        return isCopiedShopItemForever;
    }

    public PassiveEternalCard setCopiedShopItemForever(boolean copiedShopItemForever) {
        isCopiedShopItemForever = copiedShopItemForever;
        return this;
    }

    public boolean isDeathPenaltyMoneyOn() {
        return isDeathPenaltyMoneyOn;
    }

    public PassiveEternalCard setDeathPenaltyMoneyOn(boolean deathPenaltyMoneyOn) {
        isDeathPenaltyMoneyOn = deathPenaltyMoneyOn;
        return this;
    }

    public boolean isDeathPenaltyLootOn() {
        return isDeathPenaltyLootOn;
    }

    public PassiveEternalCard setDeathPenaltyLootOn(boolean deathPenaltyLootOn) {
        isDeathPenaltyLootOn = deathPenaltyLootOn;
        return this;
    }

    public boolean isBallOfTumors() {
        return isBallOfTumors;
    }

    public PassiveEternalCard setBallOfTumors(boolean ballOfTumors) {
        isBallOfTumors = ballOfTumors;
        return this;
    }

    public boolean isDeathPenaltyTreasureOn() {
        return isDeathPenaltyTreasureOn;
    }

    public PassiveEternalCard setDeathPenaltyTreasureOn(boolean deathPenaltyTreasureOn) {
        isDeathPenaltyTreasureOn = deathPenaltyTreasureOn;
        return this;
    }

    public boolean peekLootDeckAtStartToPublic() {
        return peekLootDeckAtStartToPublic;
    }

    public PassiveEternalCard setPeekLootDeckAtStartToPublic(boolean peekLootDeckAtStartToPublic) {
        this.peekLootDeckAtStartToPublic = peekLootDeckAtStartToPublic;
        return this;
    }

    public boolean isAllowedToPlayMatchingLootCard() {
        return isAllowedToPlayMatchingLootCard;
    }

    public PassiveEternalCard setAllowedToPlayMatchingLootCard(boolean allowedToPlayMatchingLootCard) {
        isAllowedToPlayMatchingLootCard = allowedToPlayMatchingLootCard;
        return this;
    }

    public boolean isPossesion() {
        return isPossesion;
    }

    public PassiveEternalCard setPossesion(boolean possesion) {
        isPossesion = possesion;
        return this;
    }

    public boolean isAllowedToCopyEternalItemAtStart() {
        return isAllowedToCopyEternalItemAtStart;
    }

    public PassiveEternalCard setAllowedToCopyEternalItemAtStart(boolean allowedToCopyEternalItemAtStart) {
        isAllowedToCopyEternalItemAtStart = allowedToCopyEternalItemAtStart;
        return this;
    }

    public boolean isLollypop() {
        return isLollypop;
    }

    public PassiveEternalCard setLollypop(boolean lollypop) {
        isLollypop = lollypop;
        return this;
    }

    public boolean isHunkyBoys() {
        return isHunkyBoys;
    }

    public PassiveEternalCard setHunkyBoys(boolean hunkyBoys) {
        isHunkyBoys = hunkyBoys;
        return this;
    }

    public boolean isFocus() {
        return isFocus;
    }

    public PassiveEternalCard setFocus(boolean focus) {
        isFocus = focus;
        return this;
    }

    public boolean isRingOfSnake() {
        return isRingOfSnake;
    }

    public PassiveEternalCard setRingOfSnake(boolean ringOfSnake) {
        isRingOfSnake = ringOfSnake;
        return this;
    }

    public JsonObject toJSON() {
        return super.toJSON()
            .put("hasCounter", hasCounter)
            .put("treasureAfterDeath", treasureAfterDeath)
            .put("rollListeners", rollListeners)
            .put("deathListeners", deathListeners)
            .put("damageTaken", damageTaken)
            .put("damageDealt", damageDealt)
            .put("killListeners", killListeners)
            .put("counterIsHitPoints", counterIsHitPoints)
            .put("counterLimit", counterLimit)
            .put("counterLimitResetTo", counterLimit)
            .put("counterExceededReward", counterExceededReward)
            .put("isAllowedToAttackAgain", isAllowedToAttackAgain)
            .put("isAllowedToFlipCharacter", isAllowedToFlipCharacter)
            .put("isAllowedToCopyShopItem", isAllowedToCopyShopItem)
            .put("isCopiedShopItemForever", isCopiedShopItemForever)
            .put("isDeathPenaltyMoneyOn", isDeathPenaltyMoneyOn)
            .put("isDeathPenaltyLootOn", isDeathPenaltyLootOn)
            .put("isBallOfTumors", isBallOfTumors)
            .put("isDeathPenaltyTreasureOn", isDeathPenaltyTreasureOn)
            .put("peekLootDeckAtStartToPublic", peekLootDeckAtStartToPublic)
            .put("isAllowedToPlayMatchingLootCard", isAllowedToPlayMatchingLootCard)
            .put("isPossesion", isPossesion)
            .put("isAllowedToCopyEternalItemAtStart", isAllowedToCopyEternalItemAtStart)
            .put("isLollypop", isLollypop)
            .put("isHunkyBoys", isHunkyBoys)
            .put("isFocus", isFocus)
            .put("isRingOfSnake", isRingOfSnake);
    }
}
