package io.github.camposmdev.foursouls.core.card.loot;

import io.github.camposmdev.foursouls.core.card.BaseCard;
import io.github.camposmdev.foursouls.core.card.attribute.CardType;

public abstract class LootCard extends BaseCard {
    public LootCard() {
        super.setCardType(CardType.LOOT);
    }
}
