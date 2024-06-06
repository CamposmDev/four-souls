package io.github.camposmdev.foursouls.core.card.loot;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.camposmdev.foursouls.core.card.attribute.CardType;
import io.github.camposmdev.foursouls.core.card.attribute.CardVersion;

public class SoulHeartCard extends LootCard {
    private CardVersion version;

    public SoulHeartCard() {
        super.setCardType(CardType.SHEART);
    }

    public CardVersion getVersion() {
        return version;
    }

    public SoulHeartCard setVersion(CardVersion version) {
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
