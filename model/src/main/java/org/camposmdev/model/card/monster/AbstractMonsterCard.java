package org.camposmdev.model.card.monster;

import org.camposmdev.model.card.BaseCard;
import org.camposmdev.model.card.attribute.CardType;

public class AbstractMonsterCard extends BaseCard {
    public AbstractMonsterCard() {
        super.setCardType(CardType.MONSTER);
    }
}
