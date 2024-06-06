package io.github.camposmdev.foursouls.core.card.monster;

import io.github.camposmdev.foursouls.core.card.BaseCard;
import io.github.camposmdev.foursouls.core.card.attribute.CardType;

public class BaseMonsterCard extends BaseCard {
    public BaseMonsterCard() {
        super.setCardType(CardType.MONSTER);
    }
}
