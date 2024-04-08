package org.camposmdev.model.card.eternal;

import org.camposmdev.model.card.BaseCard;
import org.camposmdev.model.card.attribute.CardType;

public abstract class EternalCard extends BaseCard {
    public EternalCard() {
        super.setCardType(CardType.ETERNAL);
    }
}
