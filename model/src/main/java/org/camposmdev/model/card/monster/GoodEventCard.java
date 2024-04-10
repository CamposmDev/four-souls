package org.camposmdev.model.card.monster;

import org.camposmdev.model.card.attribute.CardType;
import org.camposmdev.model.card.attribute.DeckType;
import org.camposmdev.model.card.attribute.EntityTarget;
import org.camposmdev.model.card.attribute.RollEvent;
import org.camposmdev.model.card.attribute.monster.MonsterOptionEvent;

public class GoodEventCard extends AbstractMonsterCard {
    private byte ambush;
    private RollEvent[] rollEvents;
    private MonsterOptionEvent[] optionEvents;
    private DeckType peekDeck;
    private byte peekDeckAmount;
    private boolean peekDeckSort;
    private byte loot;
    private byte expandShop;
    private boolean attackAgain;
    private boolean weNeedToGoDeeper;
    private byte expandMonster;
    private boolean weNeedToGoDeeperNonEvent;
    private byte bossRush;
    private boolean doubleTreasure;
    private boolean rerollItems;
    private byte damage;
    private EntityTarget damageTo;
    private byte putInDeck;
    private boolean lustForBlood;
    private boolean isGoldenIdol;
    private boolean isNightmareTick;
    private boolean isQwop;
    private byte expandRoom;
    private boolean isTVStatic;

    public GoodEventCard() {
        super.setCardType(CardType.GEVENT);
    }

    public byte ambush() {
        return ambush;
    }

    public GoodEventCard setAmbush(byte ambush) {
        this.ambush = ambush;
        return this;
    }

    public RollEvent[] rollEvents() {
        return rollEvents;
    }

    public GoodEventCard setRollEvents(RollEvent[] rollEvents) {
        this.rollEvents = rollEvents;
        return this;
    }

    public MonsterOptionEvent[] optionEvents() {
        return optionEvents;
    }

    public GoodEventCard setOptionEvents(MonsterOptionEvent[] optionEvents) {
        this.optionEvents = optionEvents;
        return this;
    }

    public DeckType peekDeck() {
        return peekDeck;
    }

    public GoodEventCard setPeekDeck(DeckType peekDeck) {
        this.peekDeck = peekDeck;
        return this;
    }

    public byte peekDeckAmount() {
        return peekDeckAmount;
    }

    public GoodEventCard setPeekDeckAmount(byte peekDeckAmount) {
        this.peekDeckAmount = peekDeckAmount;
        return this;
    }

    public boolean peekDeckSort() {
        return peekDeckSort;
    }

    public GoodEventCard setPeekDeckSort(boolean peekDeckSort) {
        this.peekDeckSort = peekDeckSort;
        return this;
    }

    public byte loot() {
        return loot;
    }

    public GoodEventCard setLoot(byte loot) {
        this.loot = loot;
        return this;
    }

    public byte expandShop() {
        return expandShop;
    }

    public GoodEventCard setExpandShop(byte expandShop) {
        this.expandShop = expandShop;
        return this;
    }

    public boolean attackAgain() {
        return attackAgain;
    }

    public GoodEventCard setAttackAgain(boolean attackAgain) {
        this.attackAgain = attackAgain;
        return this;
    }

    public boolean weNeedToGoDeeper() {
        return weNeedToGoDeeper;
    }

    public GoodEventCard setWeNeedToGoDeeper(boolean weNeedToGoDeeper) {
        this.weNeedToGoDeeper = weNeedToGoDeeper;
        return this;
    }

    public byte expandMonster() {
        return expandMonster;
    }

    public GoodEventCard setExpandMonster(byte expandMonster) {
        this.expandMonster = expandMonster;
        return this;
    }

    public boolean weNeedToGoDeeperNonEvent() {
        return weNeedToGoDeeperNonEvent;
    }

    public GoodEventCard setWeNeedToGoDeeperNonEvent(boolean weNeedToGoDeeperNonEvent) {
        this.weNeedToGoDeeperNonEvent = weNeedToGoDeeperNonEvent;
        return this;
    }

    public byte bossRush() {
        return bossRush;
    }

    public GoodEventCard setBossRush(byte bossRush) {
        this.bossRush = bossRush;
        return this;
    }

    public boolean doubleTreasure() {
        return doubleTreasure;
    }

    public GoodEventCard setDoubleTreasure(boolean doubleTreasure) {
        this.doubleTreasure = doubleTreasure;
        return this;
    }

    public boolean rerollItems() {
        return rerollItems;
    }

    public GoodEventCard setRerollItems(boolean rerollItems) {
        this.rerollItems = rerollItems;
        return this;
    }

    public byte damage() {
        return damage;
    }

    public GoodEventCard setDamage(byte damage) {
        this.damage = damage;
        return this;
    }

    public EntityTarget damageTo() {
        return damageTo;
    }

    public GoodEventCard setDamageTo(EntityTarget damageTo) {
        this.damageTo = damageTo;
        return this;
    }

    public byte putInDeck() {
        return putInDeck;
    }

    public GoodEventCard setPutInDeck(byte putInDeck) {
        this.putInDeck = putInDeck;
        return this;
    }

    public boolean lustForBlood() {
        return lustForBlood;
    }

    public GoodEventCard setLustForBlood(boolean lustForBlood) {
        this.lustForBlood = lustForBlood;
        return this;
    }

    public boolean isGoldenIdol() {
        return isGoldenIdol;
    }

    public GoodEventCard setGoldenIdol(boolean goldenIdol) {
        isGoldenIdol = goldenIdol;
        return this;
    }

    public boolean isNightmareTick() {
        return isNightmareTick;
    }

    public GoodEventCard setNightmareTick(boolean nightmareTick) {
        isNightmareTick = nightmareTick;
        return this;
    }

    public boolean isQwop() {
        return isQwop;
    }

    public GoodEventCard setQwop(boolean qwop) {
        isQwop = qwop;
        return this;
    }

    public byte expandRoom() {
        return expandRoom;
    }

    public GoodEventCard setExpandRoom(byte expandRoom) {
        this.expandRoom = expandRoom;
        return this;
    }

    public boolean isTVStatic() {
        return isTVStatic;
    }

    public GoodEventCard setTVStatic(boolean TVStatic) {
        isTVStatic = TVStatic;
        return this;
    }
}
