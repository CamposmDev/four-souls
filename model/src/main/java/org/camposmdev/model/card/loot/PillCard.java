package org.camposmdev.model.card.loot;

import org.camposmdev.model.card.attribute.CardType;
import org.camposmdev.model.card.attribute.CardVersion;
import org.camposmdev.model.card.attribute.loot.PillEvent;

public class PillCard extends LootCard {
    private PillEvent[] events;
    private byte repeat;
    private boolean share;
    private CardVersion version;

    public PillCard() {
        super.setCardType(CardType.PILLS);
    }

    public PillEvent[] events() {
        return events;
    }

    public PillCard setEvents(PillEvent[] events) {
        this.events = events;
        return this;
    }

    public byte repeat() {
        return repeat;
    }

    public PillCard setRepeat(byte repeat) {
        this.repeat = repeat;
        return this;
    }

    public boolean share() {
        return share;
    }

    public PillCard setShare(boolean share) {
        this.share = share;
        return this;
    }

    public CardVersion version() {
        return version;
    }

    public PillCard setVersion(CardVersion version) {
        this.version = version;
        return this;
    }
}
