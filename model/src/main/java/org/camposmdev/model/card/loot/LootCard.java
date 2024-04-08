package org.camposmdev.model.card.loot;

import org.camposmdev.model.card.BaseCard;
import org.camposmdev.model.card.attribute.CardType;

public abstract class LootCard extends BaseCard {
    public LootCard() {
        super.setCardType(CardType.LOOT);
    }
}
