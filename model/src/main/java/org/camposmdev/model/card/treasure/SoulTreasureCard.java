package org.camposmdev.model.card.treasure;

import org.camposmdev.model.card.attribute.CardType;
import org.camposmdev.model.card.attribute.treasure.SoulItem;

public class SoulTreasureCard extends TreasureCard {
    private SoulItem item;

    public SoulTreasureCard() {
        super.setCardType(CardType.STREASURE);
    }

    public SoulItem item() {
        return item;
    }

    public SoulTreasureCard setItem(SoulItem item) {
        this.item = item;
        return this;
    }
}
