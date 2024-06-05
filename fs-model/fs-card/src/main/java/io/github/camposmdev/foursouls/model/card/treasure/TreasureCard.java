package io.github.camposmdev.foursouls.model.card.treasure;

import io.github.camposmdev.foursouls.model.card.BaseCard;
import io.github.camposmdev.foursouls.model.card.attribute.CardType;

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
