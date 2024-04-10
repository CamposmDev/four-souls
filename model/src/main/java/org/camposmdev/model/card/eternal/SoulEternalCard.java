package org.camposmdev.model.card.eternal;

import org.camposmdev.model.card.attribute.CardType;
import org.camposmdev.model.card.attribute.CardVersion;

public class SoulEternalCard extends EternalCard {
    private boolean isTheBone;
    private CardVersion theBone;

    public SoulEternalCard() {
        super.setCardType(CardType.SETERNAL);
    }

    public boolean isTheBone() {
        return isTheBone;
    }

    public SoulEternalCard setTheBone(boolean theBone) {
        isTheBone = theBone;
        return this;
    }

    public CardVersion theBone() {
        return theBone;
    }

    public SoulEternalCard setTheBone(CardVersion theBone) {
        this.theBone = theBone;
        return this;
    }
}
