package org.camposmdev.model.card.treasure;

import org.camposmdev.model.card.BaseCard;
import org.camposmdev.model.card.attribute.CardType;

public abstract class TreasureCard extends BaseCard {
    private boolean guppy;

    public TreasureCard() {
        super.setCardType(CardType.TREASURE);
    }

    public boolean isGuppy() {
        return guppy;
    }

    public TreasureCard setGuppy(boolean guppy) {
        this.guppy = guppy;
        return this;
    }
}
