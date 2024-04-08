package org.camposmdev.model.card.loot;

import org.camposmdev.model.card.attribute.CardType;

public class PillCard extends LootCard {
    private PillEvent[] events;
    private byte repeat;
    private boolean share;
    private byte version;

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

    public byte version() {
        return version;
    }

    public PillCard setVersion(byte version) {
        this.version = version;
        return this;
    }
}
