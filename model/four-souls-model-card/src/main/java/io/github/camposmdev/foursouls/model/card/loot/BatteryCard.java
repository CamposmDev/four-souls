package io.github.camposmdev.foursouls.model.card.loot;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.camposmdev.foursouls.model.card.attribute.loot.BatteryType;
import io.github.camposmdev.foursouls.model.card.attribute.CardType;

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
