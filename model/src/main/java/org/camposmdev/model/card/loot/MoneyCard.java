package org.camposmdev.model.card.loot;

import io.vertx.core.json.JsonObject;
import org.camposmdev.model.card.attribute.CardType;

public class MoneyCard extends LootCard {
    private Byte value;
    private boolean rechargeItem, doubleReward, isSticky, isButt;

    public MoneyCard() {
        super.setCardType(CardType.MONEY);
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

    public boolean isButt() {
        return isButt;
    }

    public MoneyCard setButt(boolean butt) {
        isButt = butt;
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
