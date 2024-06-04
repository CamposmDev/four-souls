package io.github.camposmdev.foursouls.model.card.loot;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.camposmdev.foursouls.model.card.attribute.CardType;

public class ButterBeanCard extends LootCard {
    private boolean cancelActiveItemEffect, cancelLootEffect, cancelPaidItemEffect;

    public ButterBeanCard() {
        super.setCardType(CardType.BUTTER);
    }

    public boolean isCancelActiveItemEffect() {
        return cancelActiveItemEffect;
    }

    public ButterBeanCard setCancelActiveItemEffect(boolean cancelActiveItemEffect) {
        this.cancelActiveItemEffect = cancelActiveItemEffect;
        return this;
    }

    public boolean isCancelLootEffect() {
        return cancelLootEffect;
    }

    public ButterBeanCard setCancelLootEffect(boolean cancelLootEffect) {
        this.cancelLootEffect = cancelLootEffect;
        return this;
    }

    public boolean isCancelPaidItemEffect() {
        return cancelPaidItemEffect;
    }

    public ButterBeanCard setCancelPaidItemEffect(boolean cancelPaidItemEffect) {
        this.cancelPaidItemEffect = cancelPaidItemEffect;
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
