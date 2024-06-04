package io.github.camposmdev.foursouls.model.card.loot;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.camposmdev.foursouls.model.card.attribute.CardType;
import io.github.camposmdev.foursouls.model.card.attribute.loot.CardKind;

public class WildCard extends LootCard {
    protected CardKind kind;

    public WildCard() {
        super.setCardType(CardType.WILDCARD);
    }

    public CardKind getKind() {
        return kind;
    }

    public WildCard setKind(CardKind kind) {
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

