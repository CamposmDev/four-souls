package io.github.camposmdev.foursouls.core.card.eternal;

import io.github.camposmdev.foursouls.core.card.BaseCard;
import io.github.camposmdev.foursouls.core.card.attribute.CardType;

public abstract class EternalCard extends BaseCard {
    public EternalCard() {
        super.setCardType(CardType.ETERNAL);
    }
}
