package org.camposmdev.model.card.extra;

import org.camposmdev.model.card.BaseCard;
import org.camposmdev.model.card.attribute.CardType;

public class ExtraCard extends BaseCard {
    private boolean forceAttack, theHarbingers, indomitable, theBeast;

    public boolean forceAttack() {
        return forceAttack;
    }

    public ExtraCard() {
        super.setCardType(CardType.EXTRA);
    }

    public ExtraCard setForceAttack(boolean forceAttack) {
        this.forceAttack = forceAttack;
        return this;
    }

    public boolean theHarbingers() {
        return theHarbingers;
    }

    public ExtraCard setTheHarbingers(boolean theHarbingers) {
        this.theHarbingers = theHarbingers;
        return this;
    }

    public boolean indomitable() {
        return indomitable;
    }

    public ExtraCard setIndomitable(boolean indomitable) {
        this.indomitable = indomitable;
        return this;
    }

    public boolean theBeast() {
        return theBeast;
    }

    public ExtraCard setTheBeast(boolean theBeast) {
        this.theBeast = theBeast;
        return this;
    }
}
