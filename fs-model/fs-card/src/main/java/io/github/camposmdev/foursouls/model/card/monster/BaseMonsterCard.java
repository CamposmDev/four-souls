package io.github.camposmdev.foursouls.model.card.monster;

import io.github.camposmdev.foursouls.model.card.BaseCard;
import io.github.camposmdev.foursouls.model.card.attribute.CardType;

public class BaseMonsterCard extends BaseCard {
    public BaseMonsterCard() {
        super.setCardType(CardType.MONSTER);
    }
}
