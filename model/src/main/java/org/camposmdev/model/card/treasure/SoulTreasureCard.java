package org.camposmdev.model.card.treasure;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.camposmdev.model.card.attribute.CardType;
import org.camposmdev.model.card.attribute.treasure.SoulItem;

public class SoulTreasureCard extends TreasureCard {
    private SoulItem item;

    public SoulTreasureCard() {
        super.setCardType(CardType.STREASURE);
    }

    public SoulItem getItem() {
        return item;
    }

    public SoulTreasureCard setItem(SoulItem item) {
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
