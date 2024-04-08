package org.camposmdev.model.card.treasure;

import org.camposmdev.model.card.BaseCard;
import org.camposmdev.model.card.attribute.CardType;

public class PaidTreasureCard extends BaseCard {
    private PaidItem item;

    public PaidTreasureCard() {
        super.setCardType(CardType.PAIDTREASURE);
    }

    public PaidItem item() {
        return item;
    }

    public PaidTreasureCard setItem(PaidItem item) {
        this.item = item;
        return this;
    }
}
