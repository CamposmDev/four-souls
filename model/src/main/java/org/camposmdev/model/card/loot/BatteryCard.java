package org.camposmdev.model.card.loot;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.camposmdev.model.card.attribute.CardType;
import org.camposmdev.model.card.attribute.loot.BatteryType;

public class BatteryCard extends LootCard {
    private BatteryType type;

    public BatteryCard() {
        super.setCardType(CardType.BATTERIES);
    }

    public BatteryType getType() {
        return type;
    }

    public BatteryCard setType(BatteryType type) {
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
