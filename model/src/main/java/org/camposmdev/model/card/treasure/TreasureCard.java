package org.camposmdev.model.card.treasure;

import org.camposmdev.model.card.BaseCard;
import org.camposmdev.model.card.attribute.CardType;

public abstract class TreasureCard extends BaseCard {
    private boolean isGuppy;

    public TreasureCard() {
        super.setCardType(CardType.TREASURE);
    }

    public boolean isGuppy() {
        return isGuppy;
    }

    public TreasureCard setGuppy(boolean guppy) {
        isGuppy = guppy;
        return this;
    }
}
