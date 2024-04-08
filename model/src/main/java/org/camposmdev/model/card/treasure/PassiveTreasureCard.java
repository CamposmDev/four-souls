package org.camposmdev.model.card.treasure;

import org.camposmdev.model.card.attribute.CardType;

public class PassiveTreasureCard extends TreasureCard {
    private PassiveItem item;

    public PassiveTreasureCard() {
        super.setCardType(CardType.PTREASURE);
    }

    public PassiveItem item() {
        return item;
    }

    public PassiveTreasureCard setItem(PassiveItem item) {
        this.item = item;
        return this;
    }
}
