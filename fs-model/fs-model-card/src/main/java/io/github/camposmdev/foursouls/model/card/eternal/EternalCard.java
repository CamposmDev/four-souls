package io.github.camposmdev.foursouls.model.card.eternal;

import io.github.camposmdev.foursouls.model.card.BaseCard;
import io.github.camposmdev.foursouls.model.card.attribute.CardType;

public abstract class EternalCard extends BaseCard {
    public EternalCard() {
        super.setCardType(CardType.ETERNAL);
    }
}
