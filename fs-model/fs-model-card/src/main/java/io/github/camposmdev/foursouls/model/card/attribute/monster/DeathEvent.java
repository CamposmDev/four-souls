package io.github.camposmdev.foursouls.model.card.attribute.monster;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.camposmdev.foursouls.model.card.attribute.DeckType;
import io.github.camposmdev.foursouls.model.card.attribute.EntityTarget;
import io.github.camposmdev.foursouls.model.card.attribute.RollEvent;
import io.github.camposmdev.foursouls.model.card.attribute.Reward;

import java.util.List;

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
    private List<RollEvent> deathRollEvents;
    private List<RollEvent> killRollEvents;
    private byte expandAny;
    private boolean tapeWorm;
    private boolean greedlingRush;
    private Reward reward;
    private EntityTarget rewardTo;
    private boolean giveHeartCounter;
    private boolean heartItem;
    private byte putInDeck;
    private byte stealSoul;
    private boolean summonHarbingers;
    private boolean beastAlt;
    private boolean deathLink;
    private boolean skipPlayersNextTurn;
    private boolean peep;
    private boolean sloth;
    private boolean attackMonsterDeck;
    private boolean secondChance;
    private List<Byte> secondChanceAttributes;
    private boolean giftSoul;
    private boolean clog;
    private boolean summonCurse;
    private boolean balrog;
    private boolean discardHand;
    private boolean pestilenceAlt;
    private boolean stealItemWithGoldCounter;
    private boolean skipChosenPlayersTurn;
    private boolean spiderCounterDistributeDamage;
    private boolean butItsReallyTheBloat;

    public boolean isAttackAgain() {
        return attackAgain;
    }

    public DeathEvent setAttackAgain(boolean attackAgain) {
        this.attackAgain = attackAgain;
        return this;
    }

    public byte getDamage() {
        return damage;
    }

    public DeathEvent setDamage(byte damage) {
        this.damage = damage;
        return this;
    }

    public EntityTarget getDamageTo() {
        return damageTo;
    }

    public DeathEvent setDamageTo(EntityTarget damageTo) {
        this.damageTo = damageTo;
        return this;
    }

    public byte getDiscardLoot() {
        return discardLoot;
    }

    public DeathEvent setDiscardLoot(byte discardLoot) {
        this.discardLoot = discardLoot;
        return this;
    }

    public EntityTarget getDiscardLootTo() {
        return discardLootTo;
    }

    public DeathEvent setDiscardLootTo(EntityTarget discardLootTo) {
        this.discardLootTo = discardLootTo;
        return this;
    }

    public byte getLoseCents() {
        return loseCents;
    }

    public DeathEvent setLoseCents(byte loseCents) {
        this.loseCents = loseCents;
        return this;
    }

    public EntityTarget getLoseCentsTo() {
        return loseCentsTo;
    }

    public DeathEvent setLoseCentsTo(EntityTarget loseCentsTo) {
        this.loseCentsTo = loseCentsTo;
        return this;
    }

    public byte getExpandShop() {
        return expandShop;
    }

    public DeathEvent setExpandShop(byte expandShop) {
        this.expandShop = expandShop;
        return this;
    }

    public boolean isStealPlayerItem() {
        return stealPlayerItem;
    }

    public DeathEvent setStealPlayerItem(boolean stealPlayerItem) {
        this.stealPlayerItem = stealPlayerItem;
        return this;
    }

    public boolean isPeekPlayerHand() {
        return peekPlayerHand;
    }

    public DeathEvent setPeekPlayerHand(boolean peekPlayerHand) {
        this.peekPlayerHand = peekPlayerHand;
        return this;
    }

    public byte getExpandMonster() {
        return expandMonster;
    }

    public DeathEvent setExpandMonster(byte expandMonster) {
        this.expandMonster = expandMonster;
        return this;
    }

    public boolean isForceAttackAgain() {
        return forceAttackAgain;
    }

    public DeathEvent setForceAttackAgain(boolean forceAttackAgain) {
        this.forceAttackAgain = forceAttackAgain;
        return this;
    }

    public boolean isRechargeAllItems() {
        return rechargeAllItems;
    }

    public DeathEvent setRechargeAllItems(boolean rechargeAllItems) {
        this.rechargeAllItems = rechargeAllItems;
        return this;
    }

    public byte getDiscardSoul() {
        return discardSoul;
    }

    public DeathEvent setDiscardSoul(byte discardSoul) {
        this.discardSoul = discardSoul;
        return this;
    }

    public DeckType getPeekDeck() {
        return peekDeck;
    }

    public DeathEvent setPeekDeck(DeckType peekDeck) {
        this.peekDeck = peekDeck;
        return this;
    }

    public byte getPeekDeckAmount() {
        return peekDeckAmount;
    }

    public DeathEvent setPeekDeckAmount(byte peekDeckAmount) {
        this.peekDeckAmount = peekDeckAmount;
        return this;
    }

    public boolean isPeekDeckSort() {
        return peekDeckSort;
    }

    public DeathEvent setPeekDeckSort(boolean peekDeckSort) {
        this.peekDeckSort = peekDeckSort;
        return this;
    }

    public EntityTarget getKill() {
        return kill;
    }

    public DeathEvent setKill(EntityTarget kill) {
        this.kill = kill;
        return this;
    }

    public boolean isCancelIfDamageDealt() {
        return cancelIfDamageDealt;
    }

    public DeathEvent setCancelIfDamageDealt(boolean cancelIfDamageDealt) {
        this.cancelIfDamageDealt = cancelIfDamageDealt;
        return this;
    }

    public boolean isForceAttackAgainOnSameSlot() {
        return forceAttackAgainOnSameSlot;
    }

    public DeathEvent setForceAttackAgainOnSameSlot(boolean forceAttackAgainOnSameSlot) {
        this.forceAttackAgainOnSameSlot = forceAttackAgainOnSameSlot;
        return this;
    }

    public List<RollEvent> getDeathRollEvents() {
        return deathRollEvents;
    }

    public DeathEvent setDeathRollEvents(List<RollEvent> deathRollEvents) {
        this.deathRollEvents = deathRollEvents;
        return this;
    }

    public List<RollEvent> getKillRollEvents() {
        return killRollEvents;
    }

    public DeathEvent setKillRollEvents(List<RollEvent> killRollEvents) {
        this.killRollEvents = killRollEvents;
        return this;
    }

    public byte getExpandAny() {
        return expandAny;
    }

    public DeathEvent setExpandAny(byte expandAny) {
        this.expandAny = expandAny;
        return this;
    }

    public boolean isTapeWorm() {
        return tapeWorm;
    }

    public DeathEvent setTapeWorm(boolean tapeWorm) {
        this.tapeWorm = tapeWorm;
        return this;
    }

    public boolean isGreedlingRush() {
        return greedlingRush;
    }

    public DeathEvent setGreedlingRush(boolean greedlingRush) {
        this.greedlingRush = greedlingRush;
        return this;
    }

    public Reward getReward() {
        return reward;
    }

    public DeathEvent setReward(Reward reward) {
        this.reward = reward;
        return this;
    }

    public EntityTarget getRewardTo() {
        return rewardTo;
    }

    public DeathEvent setRewardTo(EntityTarget rewardTo) {
        this.rewardTo = rewardTo;
        return this;
    }

    public boolean isGiveHeartCounter() {
        return giveHeartCounter;
    }

    public DeathEvent setGiveHeartCounter(boolean giveHeartCounter) {
        this.giveHeartCounter = giveHeartCounter;
        return this;
    }

    public boolean isHeartItem() {
        return heartItem;
    }

    public DeathEvent setHeartItem(boolean heartItem) {
        this.heartItem = heartItem;
        return this;
    }

    public byte getPutInDeck() {
        return putInDeck;
    }

    public DeathEvent setPutInDeck(byte putInDeck) {
        this.putInDeck = putInDeck;
        return this;
    }

    public byte getStealSoul() {
        return stealSoul;
    }

    public DeathEvent setStealSoul(byte stealSoul) {
        this.stealSoul = stealSoul;
        return this;
    }

    public boolean isSummonHarbingers() {
        return summonHarbingers;
    }

    public DeathEvent setSummonHarbingers(boolean summonHarbingers) {
        this.summonHarbingers = summonHarbingers;
        return this;
    }

    public boolean isBeastAlt() {
        return beastAlt;
    }

    public DeathEvent setBeastAlt(boolean beastAlt) {
        this.beastAlt = beastAlt;
        return this;
    }

    public boolean isDeathLink() {
        return deathLink;
    }

    public DeathEvent setDeathLink(boolean deathLink) {
        this.deathLink = deathLink;
        return this;
    }

    public boolean isSkipPlayersNextTurn() {
        return skipPlayersNextTurn;
    }

    public DeathEvent setSkipPlayersNextTurn(boolean skipPlayersNextTurn) {
        this.skipPlayersNextTurn = skipPlayersNextTurn;
        return this;
    }

    public boolean isPeep() {
        return peep;
    }

    public DeathEvent setPeep(boolean peep) {
        this.peep = peep;
        return this;
    }

    public boolean isSloth() {
        return sloth;
    }

    public DeathEvent setSloth(boolean sloth) {
        this.sloth = sloth;
        return this;
    }

    public boolean isAttackMonsterDeck() {
        return attackMonsterDeck;
    }

    public DeathEvent setAttackMonsterDeck(boolean attackMonsterDeck) {
        this.attackMonsterDeck = attackMonsterDeck;
        return this;
    }

    public boolean isSecondChance() {
        return secondChance;
    }

    public DeathEvent setSecondChance(boolean secondChance) {
        this.secondChance = secondChance;
        return this;
    }

    public List<Byte> getSecondChanceAttributes() {
        return secondChanceAttributes;
    }

    public DeathEvent setSecondChanceAttributes(List<Byte> secondChanceAttributes) {
        this.secondChanceAttributes = secondChanceAttributes;
        return this;
    }

    public boolean isGiftSoul() {
        return giftSoul;
    }

    public DeathEvent setGiftSoul(boolean giftSoul) {
        this.giftSoul = giftSoul;
        return this;
    }

    public boolean isClog() {
        return clog;
    }

    public DeathEvent setClog(boolean clog) {
        this.clog = clog;
        return this;
    }

    public boolean isSummonCurse() {
        return summonCurse;
    }

    public DeathEvent setSummonCurse(boolean summonCurse) {
        this.summonCurse = summonCurse;
        return this;
    }

    public boolean isBalrog() {
        return balrog;
    }

    public DeathEvent setBalrog(boolean balrog) {
        this.balrog = balrog;
        return this;
    }

    public boolean isDiscardHand() {
        return discardHand;
    }

    public DeathEvent setDiscardHand(boolean discardHand) {
        this.discardHand = discardHand;
        return this;
    }

    public boolean isPestilenceAlt() {
        return pestilenceAlt;
    }

    public DeathEvent setPestilenceAlt(boolean pestilenceAlt) {
        this.pestilenceAlt = pestilenceAlt;
        return this;
    }

    public boolean isStealItemWithGoldCounter() {
        return stealItemWithGoldCounter;
    }

    public DeathEvent setStealItemWithGoldCounter(boolean stealItemWithGoldCounter) {
        this.stealItemWithGoldCounter = stealItemWithGoldCounter;
        return this;
    }

    public boolean isSkipChosenPlayersTurn() {
        return skipChosenPlayersTurn;
    }

    public DeathEvent setSkipChosenPlayersTurn(boolean skipChosenPlayersTurn) {
        this.skipChosenPlayersTurn = skipChosenPlayersTurn;
        return this;
    }

    public boolean isSpiderCounterDistributeDamage() {
        return spiderCounterDistributeDamage;
    }

    public DeathEvent setSpiderCounterDistributeDamage(boolean spiderCounterDistributeDamage) {
        this.spiderCounterDistributeDamage = spiderCounterDistributeDamage;
        return this;
    }

    public boolean isButItsReallyTheBloat() {
        return butItsReallyTheBloat;
    }

    public DeathEvent setButItsReallyTheBloat(boolean butItsReallyTheBloat) {
        this.butItsReallyTheBloat = butItsReallyTheBloat;
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
