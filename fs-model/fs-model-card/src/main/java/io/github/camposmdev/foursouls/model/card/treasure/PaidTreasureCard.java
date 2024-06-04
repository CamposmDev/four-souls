package io.github.camposmdev.foursouls.model.card.treasure;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.camposmdev.foursouls.model.card.attribute.treasure.PaidItem;
import io.github.camposmdev.foursouls.model.card.attribute.CardType;

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
