package org.camposmdev.model.card.loot;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.camposmdev.model.card.attribute.CardType;
import org.camposmdev.model.card.attribute.loot.TrinketType;

public class TrinketCard extends LootCard {
    private TrinketType type;

    public TrinketCard() {
        super.setCardType(CardType.TRINKETS);
    }

    public TrinketType getType() {
        return type;
    }

    public TrinketCard setType(TrinketType type) {
        this.type = type;
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
