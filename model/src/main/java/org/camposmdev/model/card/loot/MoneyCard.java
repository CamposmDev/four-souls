package org.camposmdev.model.card.loot;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.camposmdev.model.card.attribute.CardType;

public class MoneyCard extends LootCard {
    private Byte value;
    private boolean rechargeItem, doubleReward, sticky, butt;

    public MoneyCard() {
        super.setCardType(CardType.MONEY);
    }

    public Byte getValue() {
        return value;
    }

    public MoneyCard setValue(Byte value) {
        this.value = value;
        return this;
    }

    public boolean isRechargeItem() {
        return rechargeItem;
    }

    public MoneyCard setRechargeItem(boolean rechargeItem) {
        this.rechargeItem = rechargeItem;
        return this;
    }

    public boolean isDoubleReward() {
        return doubleReward;
    }

    public MoneyCard setDoubleReward(boolean doubleReward) {
        this.doubleReward = doubleReward;
        return this;
    }

    public boolean isSticky() {
        return sticky;
    }

    public MoneyCard setSticky(boolean sticky) {
        this.sticky = sticky;
        return this;
    }

    public boolean isButt() {
        return butt;
    }

    public MoneyCard setButt(boolean butt) {
        this.butt = butt;
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
