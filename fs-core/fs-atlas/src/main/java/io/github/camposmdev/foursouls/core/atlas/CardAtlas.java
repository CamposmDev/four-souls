package io.github.camposmdev.foursouls.core.atlas;

import io.github.camposmdev.foursouls.core.card.BaseCard;
import io.github.camposmdev.foursouls.core.card.attribute.CardType;

import java.util.List;

public interface CardAtlas<T extends BaseCard> {
    void add(T card);

    boolean contains(CardType cardType, String id);

    /**
     * @return List of {@link T} cards contained in {@link CardAtlas}
     */
    List<T> cards();
}
