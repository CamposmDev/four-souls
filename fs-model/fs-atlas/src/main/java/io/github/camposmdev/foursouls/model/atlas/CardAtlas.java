package io.github.camposmdev.foursouls.model.atlas;

import io.github.camposmdev.foursouls.model.card.BaseCard;
import io.github.camposmdev.foursouls.model.card.attribute.CardType;

import java.util.List;

public interface CardAtlas<T extends BaseCard> {
    void add(T card);

    boolean contains(CardType cardType, String id);

    /**
     * @return List of {@link T} cards contained in {@link CardAtlas}
     */
    List<T> cards();
}
