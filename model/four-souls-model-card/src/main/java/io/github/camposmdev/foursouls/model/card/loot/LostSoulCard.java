package io.github.camposmdev.foursouls.model.card.loot;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.camposmdev.foursouls.model.card.attribute.CardVersion;
import io.github.camposmdev.foursouls.model.card.attribute.CardType;

public class LostSoulCard extends LootCard {
    private CardVersion version;

    public LostSoulCard() {
        super.setCardType(CardType.LSOUL);
    }

    public CardVersion getVersion() {
        return version;
    }

    public LostSoulCard setVersion(CardVersion version) {
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
