package io.github.camposmdev.foursouls.core.card.monster;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.camposmdev.foursouls.core.card.attribute.monster.MonsterOptionEvent;
import io.github.camposmdev.foursouls.core.card.attribute.CardType;
import io.github.camposmdev.foursouls.core.card.attribute.DeckType;
import io.github.camposmdev.foursouls.core.card.attribute.EntityTarget;
import io.github.camposmdev.foursouls.core.card.attribute.RollEvent;

import java.util.List;

public class GoodEventCard extends BaseMonsterCard {
    private byte ambush;
    private List<RollEvent> rollEvents;
    private List<MonsterOptionEvent> optionEvents;
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
    private boolean goldenIdol;
    private boolean nightmareTick;
    private boolean qwop;
    private byte expandRoom;
    private boolean tvStatic;

    public GoodEventCard() {
        super.setCardType(CardType.GEVENT);
    }

    public byte getAmbush() {
        return ambush;
    }

    public GoodEventCard setAmbush(byte ambush) {
        this.ambush = ambush;
        return this;
    }

    public List<RollEvent> getRollEvents() {
        return rollEvents;
    }

    public GoodEventCard setRollEvents(List<RollEvent> rollEvents) {
        this.rollEvents = rollEvents;
        return this;
    }

    public List<MonsterOptionEvent> getOptionEvents() {
        return optionEvents;
    }

    public GoodEventCard setOptionEvents(List<MonsterOptionEvent> optionEvents) {
        this.optionEvents = optionEvents;
        return this;
    }

    public DeckType getPeekDeck() {
        return peekDeck;
    }

    public GoodEventCard setPeekDeck(DeckType peekDeck) {
        this.peekDeck = peekDeck;
        return this;
    }

    public byte getPeekDeckAmount() {
        return peekDeckAmount;
    }

    public GoodEventCard setPeekDeckAmount(byte peekDeckAmount) {
        this.peekDeckAmount = peekDeckAmount;
        return this;
    }

    public boolean isPeekDeckSort() {
        return peekDeckSort;
    }

    public GoodEventCard setPeekDeckSort(boolean peekDeckSort) {
        this.peekDeckSort = peekDeckSort;
        return this;
    }

    public byte getLoot() {
        return loot;
    }

    public GoodEventCard setLoot(byte loot) {
        this.loot = loot;
        return this;
    }

    public byte getExpandShop() {
        return expandShop;
    }

    public GoodEventCard setExpandShop(byte expandShop) {
        this.expandShop = expandShop;
        return this;
    }

    public boolean isAttackAgain() {
        return attackAgain;
    }

    public GoodEventCard setAttackAgain(boolean attackAgain) {
        this.attackAgain = attackAgain;
        return this;
    }

    public boolean isWeNeedToGoDeeper() {
        return weNeedToGoDeeper;
    }

    public GoodEventCard setWeNeedToGoDeeper(boolean weNeedToGoDeeper) {
        this.weNeedToGoDeeper = weNeedToGoDeeper;
        return this;
    }

    public byte getExpandMonster() {
        return expandMonster;
    }

    public GoodEventCard setExpandMonster(byte expandMonster) {
        this.expandMonster = expandMonster;
        return this;
    }

    public boolean isWeNeedToGoDeeperNonEvent() {
        return weNeedToGoDeeperNonEvent;
    }

    public GoodEventCard setWeNeedToGoDeeperNonEvent(boolean weNeedToGoDeeperNonEvent) {
        this.weNeedToGoDeeperNonEvent = weNeedToGoDeeperNonEvent;
        return this;
    }

    public byte getBossRush() {
        return bossRush;
    }

    public GoodEventCard setBossRush(byte bossRush) {
        this.bossRush = bossRush;
        return this;
    }

    public boolean isDoubleTreasure() {
        return doubleTreasure;
    }

    public GoodEventCard setDoubleTreasure(boolean doubleTreasure) {
        this.doubleTreasure = doubleTreasure;
        return this;
    }

    public boolean isRerollItems() {
        return rerollItems;
    }

    public GoodEventCard setRerollItems(boolean rerollItems) {
        this.rerollItems = rerollItems;
        return this;
    }

    public byte getDamage() {
        return damage;
    }

    public GoodEventCard setDamage(byte damage) {
        this.damage = damage;
        return this;
    }

    public EntityTarget getDamageTo() {
        return damageTo;
    }

    public GoodEventCard setDamageTo(EntityTarget damageTo) {
        this.damageTo = damageTo;
        return this;
    }

    public byte getPutInDeck() {
        return putInDeck;
    }

    public GoodEventCard setPutInDeck(byte putInDeck) {
        this.putInDeck = putInDeck;
        return this;
    }

    public boolean isLustForBlood() {
        return lustForBlood;
    }

    public GoodEventCard setLustForBlood(boolean lustForBlood) {
        this.lustForBlood = lustForBlood;
        return this;
    }

    public boolean isGoldenIdol() {
        return goldenIdol;
    }

    public GoodEventCard setGoldenIdol(boolean goldenIdol) {
        this.goldenIdol = goldenIdol;
        return this;
    }

    public boolean isNightmareTick() {
        return nightmareTick;
    }

    public GoodEventCard setNightmareTick(boolean nightmareTick) {
        this.nightmareTick = nightmareTick;
        return this;
    }

    public boolean isQwop() {
        return qwop;
    }

    public GoodEventCard setQwop(boolean qwop) {
        this.qwop = qwop;
        return this;
    }

    public byte getExpandRoom() {
        return expandRoom;
    }

    public GoodEventCard setExpandRoom(byte expandRoom) {
        this.expandRoom = expandRoom;
        return this;
    }

    public boolean isTvStatic() {
        return tvStatic;
    }

    public GoodEventCard setTvStatic(boolean tvStatic) {
        this.tvStatic = tvStatic;
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
