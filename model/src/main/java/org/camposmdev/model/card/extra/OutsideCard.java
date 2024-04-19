package org.camposmdev.model.card.extra;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.camposmdev.model.card.BaseCard;
import org.camposmdev.model.card.attribute.CardType;
import org.camposmdev.model.card.attribute.outside.OutsideType.OutsideType;

public class OutsideCard extends BaseCard {
    private OutsideType outsideType;
    public OutsideCard() {
        super.setCardType(CardType.OUTSIDE);
    }

    public OutsideType getOutsideType() {
        return outsideType;
    }

    public OutsideCard setOutsideType(OutsideType outsideType) {
        this.outsideType = outsideType;
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
