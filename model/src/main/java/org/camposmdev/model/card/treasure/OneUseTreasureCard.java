package org.camposmdev.model.card.treasure;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.camposmdev.model.card.attribute.CardType;
import org.camposmdev.model.card.attribute.treasure.OneUseItem;

public class OneUseTreasureCard extends TreasureCard {
    private OneUseItem item;

    public OneUseTreasureCard() {
        super.setCardType(CardType.OTREASURE);
    }

    public OneUseItem item() {
        return item;
    }

    public OneUseTreasureCard setItem(OneUseItem item) {
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
