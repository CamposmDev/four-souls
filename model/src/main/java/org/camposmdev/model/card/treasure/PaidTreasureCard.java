package org.camposmdev.model.card.treasure;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.camposmdev.model.card.attribute.CardType;
import org.camposmdev.model.card.attribute.treasure.PaidItem;

public class PaidTreasureCard extends TreasureCard {
    private PaidItem item;

    public PaidTreasureCard() {
        super.setCardType(CardType.PAIDTREASURE);
    }

    public PaidItem item() {
        return item;
    }

    public PaidTreasureCard setItem(PaidItem item) {
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
