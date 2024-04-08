package org.camposmdev.model.card.bonus_soul;

import io.vertx.core.json.JsonObject;
import org.camposmdev.model.card.BaseCard;
import org.camposmdev.model.card.attribute.CounterType;

public class BonusSoulCard extends BaseCard {
    private Byte loot, money, guppyItems;
    private boolean hasCounter;
    private Byte counterLimit;
    private CounterType counterType;
    private boolean isEnvy;
    private boolean isSloth;
    private boolean isStrawBerry;


    public BonusSoulCard() {
        super();
    }

    public Byte loot() {
        return loot;
    }

    public BonusSoulCard setLoot(Byte loot) {
        this.loot = loot;
        return this;
    }

    public Byte money() {
        return money;
    }

    public BonusSoulCard setMoney(Byte money) {
        this.money = money;
        return this;
    }

    public Byte guppyItems() {
        return guppyItems;
    }

    public BonusSoulCard setGuppyItems(Byte guppyItems) {
        this.guppyItems = guppyItems;
        return this;
    }

    public boolean hasCounter() {
        return hasCounter;
    }

    public BonusSoulCard setHasCounter(boolean hasCounter) {
        this.hasCounter = hasCounter;
        return this;
    }

    public Byte counterLimit() {
        return counterLimit;
    }

    public BonusSoulCard setCounterLimit(Byte counterLimit) {
        this.counterLimit = counterLimit;
        return this;
    }

    public CounterType counterType() {
        return counterType;
    }

    public BonusSoulCard setCounterType(CounterType counterType) {
        this.counterType = counterType;
        return this;
    }

    public boolean isEnvy() {
        return isEnvy;
    }

    public BonusSoulCard setEnvy(boolean envy) {
        isEnvy = envy;
        return this;
    }

    public boolean isSloth() {
        return isSloth;
    }

    public BonusSoulCard setSloth(boolean sloth) {
        isSloth = sloth;
        return this;
    }

    public boolean isStrawBerry() {
        return isStrawBerry;
    }

    public BonusSoulCard setStrawBerry(boolean strawBerry) {
        isStrawBerry = strawBerry;
        return this;
    }

    @Override
    public JsonObject toJSON() {
        return super.toJSON()
                .put("loot", loot)
                .put("money", money)
                .put("guppyItems", guppyItems)
                .put("hasCounter", hasCounter)
                .put("counterLimit", counterLimit)
                .put("counterType", counterType)
                .put("isEnvy", isEnvy)
                .put("isSloth", isSloth)
                .put("isStrawBerry", isStrawBerry);
    }

    @Override
    public String toString() {
        return toJSON().encode();
    }
}
