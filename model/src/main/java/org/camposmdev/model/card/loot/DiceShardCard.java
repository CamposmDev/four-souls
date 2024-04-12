package org.camposmdev.model.card.loot;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.camposmdev.model.card.attribute.CardType;

public class DiceShardCard extends LootCard {
    private boolean reroll;

    public DiceShardCard() {
        super.setCardType(CardType.DICE);
    }

    public boolean isReroll() {
        return reroll;
    }

    public DiceShardCard setReroll(boolean reroll) {
        this.reroll = reroll;
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
