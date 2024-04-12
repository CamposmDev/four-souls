package org.camposmdev.model.card.loot;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.camposmdev.model.card.attribute.CardType;
import org.camposmdev.model.card.attribute.CardVersion;

public class BlackHeartCard extends LootCard {
    private CardVersion version;

    public BlackHeartCard() {
        super.setCardType(CardType.BHEART);
    }

    public CardVersion getVersion() {
        return version;
    }

    public BlackHeartCard setVersion(CardVersion version) {
        this.version = version;
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
