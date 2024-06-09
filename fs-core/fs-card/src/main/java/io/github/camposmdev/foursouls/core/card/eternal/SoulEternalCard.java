package io.github.camposmdev.foursouls.core.card.eternal;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.camposmdev.foursouls.core.card.attribute.CardType;
import io.github.camposmdev.foursouls.core.card.attribute.eternal.SoulItem;

public class SoulEternalCard extends EternalCard {
    private SoulItem item;

    public SoulEternalCard() {
        super.setCardType(CardType.SETERNAL);
    }

    public SoulItem getItem() {
        return item;
    }

    public SoulEternalCard setItem(SoulItem item) {
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
