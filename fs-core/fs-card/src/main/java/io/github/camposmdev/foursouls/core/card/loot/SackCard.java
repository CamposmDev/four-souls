package io.github.camposmdev.foursouls.core.card.loot;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.camposmdev.foursouls.core.card.attribute.CardType;

public class SackCard extends LootCard {
    private byte loot;

    public SackCard() {
        super.setCardType(CardType.SACK);
    }

    public byte getLoot() {
        return loot;
    }

    public SackCard setLoot(byte loot) {
        this.loot = loot;
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
