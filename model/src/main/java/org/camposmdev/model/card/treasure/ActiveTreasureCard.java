package org.camposmdev.model.card.treasure;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.camposmdev.model.card.BaseCard;
import org.camposmdev.model.card.attribute.CardType;
import org.camposmdev.model.card.attribute.treasure.ActiveItem;

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
