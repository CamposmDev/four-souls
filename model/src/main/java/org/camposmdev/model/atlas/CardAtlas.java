package org.camposmdev.model.atlas;

import org.camposmdev.model.card.BaseCard;
import org.camposmdev.model.card.attribute.CardType;

public interface CardAtlas<T extends BaseCard> {
    void add(T card);

    boolean contains(CardType cardType, String id);
}
