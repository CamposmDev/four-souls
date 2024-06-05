package io.github.camposmdev.foursouls.model.card.bsoul;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.camposmdev.foursouls.model.card.BaseCard;
import io.github.camposmdev.foursouls.model.card.attribute.CardType;
import io.github.camposmdev.foursouls.model.card.attribute.CounterType;

public class BonusSoulCard extends BaseCard {
    private byte loot, money, guppyItems;
    private boolean counter;
    private byte counterLimit;
    private CounterType counterType;
    private boolean envy;
    private boolean sloth;
    private boolean strawberry;

    public BonusSoulCard() {
        super.setCardType(CardType.BSOUL);
    }

    public byte getLoot() {
        return loot;
    }

    public BonusSoulCard setLoot(byte loot) {
        this.loot = loot;
        return this;
    }

    public byte getMoney() {
        return money;
    }

    public BonusSoulCard setMoney(byte money) {
        this.money = money;
        return this;
    }

    public byte getGuppyItems() {
        return guppyItems;
    }

    public BonusSoulCard setGuppyItems(byte guppyItems) {
        this.guppyItems = guppyItems;
        return this;
    }

    public boolean isCounter() {
        return counter;
    }

    public BonusSoulCard setCounter(boolean counter) {
        this.counter = counter;
        return this;
    }

    public byte getCounterLimit() {
        return counterLimit;
    }

    public BonusSoulCard setCounterLimit(byte counterLimit) {
        this.counterLimit = counterLimit;
        return this;
    }

    public CounterType getCounterType() {
        return counterType;
    }

    public BonusSoulCard setCounterType(CounterType counterType) {
        this.counterType = counterType;
        return this;
    }

    public boolean isEnvy() {
        return envy;
    }

    public BonusSoulCard setEnvy(boolean envy) {
        this.envy = envy;
        return this;
    }

    public boolean isSloth() {
        return sloth;
    }

    public BonusSoulCard setSloth(boolean sloth) {
        this.sloth = sloth;
        return this;
    }

    public boolean isStrawberry() {
        return strawberry;
    }

    public BonusSoulCard setStrawberry(boolean strawberry) {
        this.strawberry = strawberry;
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
