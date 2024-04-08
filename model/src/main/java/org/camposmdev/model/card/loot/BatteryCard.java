package org.camposmdev.model.card.loot;

import org.camposmdev.model.card.attribute.CardType;

public class BatteryCard extends LootCard {
    private byte version;

    public BatteryCard() {
        super.setCardType(CardType.BATTERIES);
    }

    public byte version() {
        return version;
    }

    public BatteryCard setVersion(byte version) {
        this.version = version;
        return this;
    }
}
