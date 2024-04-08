package org.camposmdev.model.card.loot;

import org.camposmdev.model.card.attribute.CardType;

public class BlackHeartCard extends LootCard {
    private byte version;

    public BlackHeartCard() {
        super.setCardType(CardType.BHEART);
    }

    public byte version() {
        return version;
    }

    public BlackHeartCard setVersion(byte version) {
        this.version = version;
        return this;
    }
}
