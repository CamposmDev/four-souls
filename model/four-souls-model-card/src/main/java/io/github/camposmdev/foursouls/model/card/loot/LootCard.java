package io.github.camposmdev.foursouls.model.card.loot;

import io.github.camposmdev.foursouls.model.card.BaseCard;
import io.github.camposmdev.foursouls.model.card.attribute.CardType;

public abstract class LootCard extends BaseCard {
    public LootCard() {
        super.setCardType(CardType.LOOT);
    }
}
