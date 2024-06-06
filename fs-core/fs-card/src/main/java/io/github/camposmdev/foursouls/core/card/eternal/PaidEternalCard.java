package io.github.camposmdev.foursouls.core.card.eternal;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.camposmdev.foursouls.core.card.attribute.CardType;
import io.github.camposmdev.foursouls.core.card.attribute.eternal.PaidItem;

public class PaidEternalCard extends EternalCard {
    private PaidItem item;

    public PaidEternalCard() {
        super.setCardType(CardType.PAIDETERNAL);
    }

    public PaidItem getItem() {
        return item;
    }

    public PaidEternalCard setItem(PaidItem item) {
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
