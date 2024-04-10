package org.camposmdev.model.card.treasure;

import org.camposmdev.model.card.BaseCard;
import org.camposmdev.model.card.attribute.CardType;
import org.camposmdev.model.card.attribute.treasure.PaidItem;

public class PaidTreasureCard extends TreasureCard {
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
