package org.camposmdev.model.card.loot;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.camposmdev.model.card.attribute.CardType;
import org.camposmdev.model.card.attribute.loot.LootOptionEvent;

public class KeyCard extends LootCard {
    private boolean goldKey;
    private LootOptionEvent[] options;

    public KeyCard() {
        super.setCardType(CardType.KEYS);
    }

    public boolean isGoldKey() {
        return goldKey;
    }

    public KeyCard setGoldKey(boolean goldKey) {
        this.goldKey = goldKey;
        return this;
    }

    public LootOptionEvent[] getOptions() {
        return options;
    }

    public KeyCard setOptions(LootOptionEvent[] options) {
        this.options = options;
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
