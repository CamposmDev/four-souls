package org.camposmdev.model.card.extra;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.camposmdev.model.card.BaseCard;
import org.camposmdev.model.card.attribute.CardType;

public class ExtraCard extends BaseCard {
    private boolean forceAttack, theHarbingers, indomitable, theBeast;

    public ExtraCard() {
        super.setCardType(CardType.OUTSIDE);
    }

    public boolean isForceAttack() {
        return forceAttack;
    }

    public ExtraCard setForceAttack(boolean forceAttack) {
        this.forceAttack = forceAttack;
        return this;
    }

    public boolean isTheHarbingers() {
        return theHarbingers;
    }

    public ExtraCard setTheHarbingers(boolean theHarbingers) {
        this.theHarbingers = theHarbingers;
        return this;
    }

    public boolean isIndomitable() {
        return indomitable;
    }

    public ExtraCard setIndomitable(boolean indomitable) {
        this.indomitable = indomitable;
        return this;
    }

    public boolean isTheBeast() {
        return theBeast;
    }

    public ExtraCard setTheBeast(boolean theBeast) {
        this.theBeast = theBeast;
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
