package org.camposmdev.model.card.treasure;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.camposmdev.model.card.attribute.CardType;
import org.camposmdev.model.card.attribute.treasure.PassiveItem;

public class PassiveTreasureCard extends TreasureCard {
    private PassiveItem item;

    public PassiveTreasureCard() {
        super.setCardType(CardType.PTREASURE);
    }

    public PassiveItem item() {
        return item;
    }

    public PassiveTreasureCard setItem(PassiveItem item) {
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
