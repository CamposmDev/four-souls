package org.camposmdev.model.card.eternal;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.camposmdev.model.card.attribute.CardType;
import org.camposmdev.model.card.attribute.DeckType;
import org.camposmdev.model.card.attribute.EntityTarget;
import org.camposmdev.model.card.attribute.RerollType;
import org.camposmdev.model.card.attribute.RollType;
import org.camposmdev.model.card.attribute.eternal.ActiveItem;

public class ActiveEternalCard extends EternalCard {
    private ActiveItem item;

    public ActiveEternalCard() {
        super.setCardType(CardType.AETERNAL);
    }

    public ActiveItem getItem() {
        return item;
    }

    public ActiveEternalCard setItem(ActiveItem item) {
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
