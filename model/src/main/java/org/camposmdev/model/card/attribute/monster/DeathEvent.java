package org.camposmdev.model.card.attribute.monster;

import org.camposmdev.model.card.attribute.DeckType;
import org.camposmdev.model.card.attribute.EntityTarget;
import org.camposmdev.model.card.attribute.RollEvent;
import org.camposmdev.model.card.attribute.Reward;

public class DeathEvent {
    private boolean attackAgain;
    private byte damage;
    private EntityTarget damageTo;
    private byte discardLoot;
    private EntityTarget discardLootTo;
    private byte loseCents;
    private EntityTarget loseCentsTo;
    private byte expandShop;
    private boolean stealPlayerItem;
    private boolean peekPlayerHand;
    private byte expandMonster;
    private boolean forceAttackAgain;
    private boolean rechargeAllItems;
    private byte discardSoul;
    private DeckType peekDeck;
    private byte peekDeckAmount;
    private boolean peekDeckSort;
    private EntityTarget kill;
    private boolean cancelIfDamageDealt;
    private boolean forceAttackAgainOnSameSlot;
    private RollEvent[] deathRollEvents;
    private RollEvent[] killRollEvents;
    private byte expandAny;
    private boolean isTapeWorm;
    private boolean greedlingRush;
    private Reward reward;
    private EntityTarget rewardTo;
    private boolean giveHeartCounter;
    private boolean isHeartItem;
    private byte putInDeck;
    private byte stealSoul;
    private boolean summonHarbingers;
    private boolean isBeastAlt;
    private boolean deathLink;
    private boolean skipPlayersNextTurn;
    private boolean isPeep;
    private boolean isSloth;
    private boolean attackMonsterDeck;
    private boolean secondChance;
    private byte[] secondChanceAttributes;
    private boolean giftSoul;
    private boolean isClog;
    private boolean summonCurse;
    private boolean isBalrog;
    private boolean discardHand;
    private boolean isPestilenceAlt;
    private boolean stealItemWithGoldCounter;
    private boolean skipChosenPlayersTurn;
    private boolean spiderCounterDistributeDamage;
    private boolean butItsReallyTheBloat;

    public boolean attackAgain() {
        return attackAgain;
    }

    public DeathEvent setAttackAgain(boolean attackAgain) {
        this.attackAgain = attackAgain;
        return this;
    }

    public byte damage() {
        return damage;
    }

    public DeathEvent setDamage(byte damage) {
        this.damage = damage;
        return this;
    }

    public EntityTarget damageTo() {
        return damageTo;
    }

    public DeathEvent setDamageTo(EntityTarget damageTo) {
        this.damageTo = damageTo;
        return this;
    }

    public byte discardLoot() {
        return discardLoot;
    }

    public DeathEvent setDiscardLoot(byte discardLoot) {
        this.discardLoot = discardLoot;
        return this;
    }

    public EntityTarget discardLootTo() {
        return discardLootTo;
    }

    public DeathEvent setDiscardLootTo(EntityTarget discardLootTo) {
        this.discardLootTo = discardLootTo;
        return this;
    }

    public byte loseCents() {
        return loseCents;
    }

    public DeathEvent setLoseCents(byte loseCents) {
        this.loseCents = loseCents;
        return this;
    }

    public EntityTarget loseCentsTo() {
        return loseCentsTo;
    }

    public DeathEvent setLoseCentsTo(EntityTarget loseCentsTo) {
        this.loseCentsTo = loseCentsTo;
        return this;
    }

    public byte expandShop() {
        return expandShop;
    }

    public DeathEvent setExpandShop(byte expandShop) {
        this.expandShop = expandShop;
        return this;
    }

    public boolean stealPlayerItem() {
        return stealPlayerItem;
    }

    public DeathEvent setStealPlayerItem(boolean stealPlayerItem) {
        this.stealPlayerItem = stealPlayerItem;
        return this;
    }

    public boolean peekPlayerHand() {
        return peekPlayerHand;
    }

    public DeathEvent setPeekPlayerHand(boolean peekPlayerHand) {
        this.peekPlayerHand = peekPlayerHand;
        return this;
    }

    public byte expandMonster() {
        return expandMonster;
    }

    public DeathEvent setExpandMonster(byte expandMonster) {
        this.expandMonster = expandMonster;
        return this;
    }

    public boolean forceAttackAgain() {
        return forceAttackAgain;
    }

    public DeathEvent setForceAttackAgain(boolean forceAttackAgain) {
        this.forceAttackAgain = forceAttackAgain;
        return this;
    }

    public boolean rechargeAllItems() {
        return rechargeAllItems;
    }

    public DeathEvent setRechargeAllItems(boolean rechargeAllItems) {
        this.rechargeAllItems = rechargeAllItems;
        return this;
    }

    public byte discardSoul() {
        return discardSoul;
    }

    public DeathEvent setDiscardSoul(byte discardSoul) {
        this.discardSoul = discardSoul;
        return this;
    }

    public DeckType peekDeck() {
        return peekDeck;
    }

    public DeathEvent setPeekDeck(DeckType peekDeck) {
        this.peekDeck = peekDeck;
        return this;
    }

    public byte peekDeckAmount() {
        return peekDeckAmount;
    }

    public DeathEvent setPeekDeckAmount(byte peekDeckAmount) {
        this.peekDeckAmount = peekDeckAmount;
        return this;
    }

    public boolean peekDeckSort() {
        return peekDeckSort;
    }

    public DeathEvent setPeekDeckSort(boolean peekDeckSort) {
        this.peekDeckSort = peekDeckSort;
        return this;
    }

    public EntityTarget kill() {
        return kill;
    }

    public DeathEvent setKill(EntityTarget kill) {
        this.kill = kill;
        return this;
    }

    public boolean cancelIfDamageDealt() {
        return cancelIfDamageDealt;
    }

    public DeathEvent setCancelIfDamageDealt(boolean cancelIfDamageDealt) {
        this.cancelIfDamageDealt = cancelIfDamageDealt;
        return this;
    }

    public boolean forceAttackAgainOnSameSlot() {
        return forceAttackAgainOnSameSlot;
    }

    public DeathEvent setForceAttackAgainOnSameSlot(boolean forceAttackAgainOnSameSlot) {
        this.forceAttackAgainOnSameSlot = forceAttackAgainOnSameSlot;
        return this;
    }

    public RollEvent[] deathRollEvent() {
        return deathRollEvents;
    }

    public DeathEvent setDeathRollEvents(RollEvent[] deathRollEvents) {
        this.deathRollEvents = deathRollEvents;
        return this;
    }

    public RollEvent[] killRollEvent() {
        return killRollEvents;
    }

    public DeathEvent setKillRollEvents(RollEvent[] killRollEvents) {
        this.killRollEvents = killRollEvents;
        return this;
    }

    public byte expandAny() {
        return expandAny;
    }

    public DeathEvent setExpandAny(byte expandAny) {
        this.expandAny = expandAny;
        return this;
    }

    public boolean isTapeWorm() {
        return isTapeWorm;
    }

    public DeathEvent setTapeWorm(boolean tapeWorm) {
        isTapeWorm = tapeWorm;
        return this;
    }

    public boolean greedlingRush() {
        return greedlingRush;
    }

    public DeathEvent setGreedlingRush(boolean greedlingRush) {
        this.greedlingRush = greedlingRush;
        return this;
    }

    public Reward reward() {
        return reward;
    }

    public DeathEvent setReward(Reward reward) {
        this.reward = reward;
        return this;
    }

    public EntityTarget rewardTo() {
        return rewardTo;
    }

    public DeathEvent setRewardTo(EntityTarget rewardTo) {
        this.rewardTo = rewardTo;
        return this;
    }

    public boolean giveHeartCounter() {
        return giveHeartCounter;
    }

    public DeathEvent setGiveHeartCounter(boolean giveHeartCounter) {
        this.giveHeartCounter = giveHeartCounter;
        return this;
    }

    public boolean isHeartItem() {
        return isHeartItem;
    }

    public DeathEvent setHeartItem(boolean heartItem) {
        isHeartItem = heartItem;
        return this;
    }

    public byte putInDeck() {
        return putInDeck;
    }

    public DeathEvent setPutInDeck(byte putInDeck) {
        this.putInDeck = putInDeck;
        return this;
    }

    public byte stealSoul() {
        return stealSoul;
    }

    public DeathEvent setStealSoul(byte stealSoul) {
        this.stealSoul = stealSoul;
        return this;
    }

    public boolean summonHarbingers() {
        return summonHarbingers;
    }

    public DeathEvent setSummonHarbingers(boolean summonHarbingers) {
        this.summonHarbingers = summonHarbingers;
        return this;
    }

    public boolean isBeastAlt() {
        return isBeastAlt;
    }

    public DeathEvent setBeastAlt(boolean beastAlt) {
        isBeastAlt = beastAlt;
        return this;
    }

    public boolean deathLink() {
        return deathLink;
    }

    public DeathEvent setDeathLink(boolean deathLink) {
        this.deathLink = deathLink;
        return this;
    }

    public boolean skipPlayersNextTurn() {
        return skipPlayersNextTurn;
    }

    public DeathEvent setSkipPlayersNextTurn(boolean skipPlayersNextTurn) {
        this.skipPlayersNextTurn = skipPlayersNextTurn;
        return this;
    }

    public boolean isPeep() {
        return isPeep;
    }

    public DeathEvent setPeep(boolean peep) {
        isPeep = peep;
        return this;
    }

    public boolean isSloth() {
        return isSloth;
    }

    public DeathEvent setSloth(boolean sloth) {
        isSloth = sloth;
        return this;
    }

    public boolean attackMonsterDeck() {
        return attackMonsterDeck;
    }

    public DeathEvent setAttackMonsterDeck(boolean attackMonsterDeck) {
        this.attackMonsterDeck = attackMonsterDeck;
        return this;
    }

    public boolean secondChance() {
        return secondChance;
    }

    public DeathEvent setSecondChance(boolean secondChance) {
        this.secondChance = secondChance;
        return this;
    }

    public byte[] secondChanceAttributes() {
        return secondChanceAttributes;
    }

    public DeathEvent setSecondChanceAttributes(byte[] secondChanceAttributes) {
        this.secondChanceAttributes = secondChanceAttributes;
        return this;
    }

    public boolean giftSoul() {
        return giftSoul;
    }

    public DeathEvent setGiftSoul(boolean giftSoul) {
        this.giftSoul = giftSoul;
        return this;
    }

    public boolean isClog() {
        return isClog;
    }

    public DeathEvent setClog(boolean clog) {
        isClog = clog;
        return this;
    }

    public boolean summonCurse() {
        return summonCurse;
    }

    public DeathEvent setSummonCurse(boolean summonCurse) {
        this.summonCurse = summonCurse;
        return this;
    }

    public boolean isBalrog() {
        return isBalrog;
    }

    public DeathEvent setBalrog(boolean balrog) {
        isBalrog = balrog;
        return this;
    }

    public boolean discardHand() {
        return discardHand;
    }

    public DeathEvent setDiscardHand(boolean discardHand) {
        this.discardHand = discardHand;
        return this;
    }

    public boolean isPestilenceAlt() {
        return isPestilenceAlt;
    }

    public DeathEvent setPestilenceAlt(boolean pestilenceAlt) {
        isPestilenceAlt = pestilenceAlt;
        return this;
    }

    public boolean stealItemWithGoldCounter() {
        return stealItemWithGoldCounter;
    }

    public DeathEvent setStealItemWithGoldCounter(boolean stealItemWithGoldCounter) {
        this.stealItemWithGoldCounter = stealItemWithGoldCounter;
        return this;
    }

    public boolean skipChosenPlayersTurn() {
        return skipChosenPlayersTurn;
    }

    public DeathEvent setSkipChosenPlayersTurn(boolean skipChosenPlayersTurn) {
        this.skipChosenPlayersTurn = skipChosenPlayersTurn;
        return this;
    }

    public boolean spiderCounterDistributeDamage() {
        return spiderCounterDistributeDamage;
    }

    public DeathEvent setSpiderCounterDistributeDamage(boolean spiderCounterDistributeDamage) {
        this.spiderCounterDistributeDamage = spiderCounterDistributeDamage;
        return this;
    }

    public boolean butItsReallyTheBloat() {
        return butItsReallyTheBloat;
    }

    public DeathEvent setButItsReallyTheBloat(boolean butItsReallyTheBloat) {
        this.butItsReallyTheBloat = butItsReallyTheBloat;
        return this;
    }
}
