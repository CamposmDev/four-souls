package io.github.camposmdev.foursouls.model.card.treasure;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.camposmdev.foursouls.model.card.attribute.treasure.SoulItem;
import io.github.camposmdev.foursouls.model.card.attribute.CardType;

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
