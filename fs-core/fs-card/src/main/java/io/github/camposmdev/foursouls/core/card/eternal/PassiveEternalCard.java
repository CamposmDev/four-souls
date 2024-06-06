package io.github.camposmdev.foursouls.core.card.eternal;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.camposmdev.foursouls.core.card.attribute.CardType;
import io.github.camposmdev.foursouls.core.card.attribute.eternal.PassiveItem;

public class PassiveEternalCard extends EternalCard {
    private PassiveItem item;

    public PassiveEternalCard() {
        super.setCardType(CardType.PETERNAL);
    }

    public PassiveItem getItem() {
        return item;
    }

    public PassiveEternalCard setItem(PassiveItem item) {
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
