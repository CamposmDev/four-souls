package org.camposmdev.model.card;

import io.vertx.core.json.JsonObject;

public class MoneyCard extends LootCard {
    private Byte value;
    private boolean rechargeItem, doubleReward, isSticky;
    public MoneyCard(String id, String image, CardType type, Byte value, boolean rechargeItem, boolean doubleReward, boolean isSticky) {
        super(id, image, type);
        this.value = value;
        this.rechargeItem = rechargeItem;
        this.doubleReward = doubleReward;
        this.isSticky = isSticky;
    }

    public Byte value() {
        return value;
    }

    public MoneyCard setValue(Byte value) {
        this.value = value;
        return this;
    }

    public boolean rechargeItem() {
        return rechargeItem;
    }

    public MoneyCard setRechargeItem(boolean rechargeItem) {
        this.rechargeItem = rechargeItem;
        return this;
    }

    public boolean doubleReward() {
        return doubleReward;
    }

    public MoneyCard setDoubleReward(boolean doubleReward) {
        this.doubleReward = doubleReward;
        return this;
    }

    public boolean isSticky() {
        return isSticky;
    }

    public MoneyCard setSticky(boolean sticky) {
        isSticky = sticky;
        return this;
    }

    @Override
    public JsonObject toJSON() {
        return super.toJSON()
                .put("value", value)
                .put("rechargeItem", rechargeItem)
                .put("doubleReward", doubleReward)
                .put("isSticky", isSticky);
    }

    @Override
    public String toString() {
        return toJSON().encode();
    }
}
