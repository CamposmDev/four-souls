package org.camposmdev.model.card.monster;

import org.camposmdev.model.card.BaseCard;
import org.camposmdev.model.card.attribute.CardType;

public class BaseMonsterCard extends BaseCard {
    public BaseMonsterCard() {
        super.setCardType(CardType.MONSTER);
    }
}
