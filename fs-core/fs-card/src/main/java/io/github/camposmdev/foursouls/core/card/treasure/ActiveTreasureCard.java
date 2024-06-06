package io.github.camposmdev.foursouls.core.card.treasure;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.camposmdev.foursouls.core.card.attribute.treasure.ActiveItem;
import io.github.camposmdev.foursouls.core.card.attribute.CardType;

public class ActiveTreasureCard extends TreasureCard {
    private ActiveItem item;

    public ActiveTreasureCard() {
        super.setCardType(CardType.ATREASURE);
    }

    public ActiveItem item() {
        return item;
    }

    public ActiveTreasureCard setItem(ActiveItem item) {
        this.item = item;
        return this;
    }

    @Override
    public String toString() {
        try {
            return new ObjectMapper().writeValueAsString(this);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
