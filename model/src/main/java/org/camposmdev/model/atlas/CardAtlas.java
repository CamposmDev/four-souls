package org.camposmdev.model.atlas;

import org.camposmdev.model.card.BaseCard;
import org.camposmdev.model.card.attribute.CardType;

import java.util.List;

public interface CardAtlas<T extends BaseCard> {
    void add(T card);

    boolean contains(CardType cardType, String id);

    /**
     * @return List of {@link T} cards contained in {@link CardAtlas}
     */
    List<T> cards();
}
