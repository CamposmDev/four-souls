package org.camposmdev.model.card.treasure;

import org.camposmdev.model.card.BaseCard;
import org.camposmdev.model.card.attribute.CardType;
import org.camposmdev.model.card.attribute.treasure.ActiveItem;

public class ActiveTreasureCard extends TreasureCard {
    private ActiveItem item;

    public ActiveTreasureCard() {
        super.setCardType(CardType.ATREASURE);
    }

    public ActiveItem item() {
        return item;
    }

    public ActiveTreasureCard setItem(ActiveItem item) {
        this.item = item;
        return this;
    }
}
