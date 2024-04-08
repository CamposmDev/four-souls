package org.camposmdev.model.card.eternal;

import org.camposmdev.model.card.attribute.CardType;
import org.camposmdev.model.card.attribute.CardVersionType;

public class SoulEternalCard extends EternalCard {
    private boolean isTheBone;
    private CardVersionType theBone;

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

    public CardVersionType theBone() {
        return theBone;
    }

    public SoulEternalCard setTheBone(CardVersionType theBone) {
        this.theBone = theBone;
        return this;
    }
}
