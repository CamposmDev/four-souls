package org.camposmdev.model.card.treasure;

import org.camposmdev.model.card.BaseCard;
import org.camposmdev.model.card.attribute.CardType;

public class OneTimeUseTreasureCard extends BaseCard {
    private OneTimeUseItem item;

    public OneTimeUseTreasureCard() {
        super.setCardType(CardType.OTREASURE);
    }

    public OneTimeUseItem item() {
        return item;
    }

    public OneTimeUseTreasureCard setItem(OneTimeUseItem item) {
        this.item = item;
        return this;
    }
}
