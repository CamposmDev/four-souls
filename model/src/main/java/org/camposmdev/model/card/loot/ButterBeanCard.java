package org.camposmdev.model.card.loot;

import org.camposmdev.model.card.attribute.CardType;

public class ButterBeanCard extends LootCard {
    private boolean cancelActiveItemEffect, cancelLootEffect, cancelPaidItemEffect;

    public ButterBeanCard() {
        super.setCardType(CardType.BUTTER);
    }

    public boolean cancelActiveItemEffect() {
        return cancelActiveItemEffect;
    }

    public ButterBeanCard setCancelActiveItemEffect(boolean cancelActiveItemEffect) {
        this.cancelActiveItemEffect = cancelActiveItemEffect;
        return this;
    }

    public boolean cancelLootEffect() {
        return cancelLootEffect;
    }

    public ButterBeanCard setCancelLootEffect(boolean cancelLootEffect) {
        this.cancelLootEffect = cancelLootEffect;
        return this;
    }

    public boolean cancelPaidItemEffect() {
        return cancelPaidItemEffect;
    }

    public ButterBeanCard setCancelPaidItemEffect(boolean cancelPaidItemEffect) {
        this.cancelPaidItemEffect = cancelPaidItemEffect;
        return this;
    }
}
