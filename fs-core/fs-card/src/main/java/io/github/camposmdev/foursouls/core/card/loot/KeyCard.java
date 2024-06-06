package io.github.camposmdev.foursouls.core.card.loot;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.camposmdev.foursouls.core.card.attribute.CardType;
import io.github.camposmdev.foursouls.core.card.attribute.loot.CardKind;

public class
KeyCard extends LootCard {
    private CardKind kind;

    public KeyCard() {
        super.setCardType(CardType.KEYS);
    }

    public CardKind getKind() {
        return kind;
    }

    public KeyCard setKind(CardKind kind) {
        this.kind = kind;
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
