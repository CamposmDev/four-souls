package org.camposmdev.model.card.loot;

import org.camposmdev.model.card.attribute.CardType;

public class TrinketCard extends LootCard {
    private TrinketType type;

    public TrinketCard() {
        super.setCardType(CardType.TRINKETS);
    }

    public TrinketType type() {
        return type;
    }

    public TrinketCard setType(TrinketType type) {
        this.type = type;
        return this;
    }
}
