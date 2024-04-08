package org.camposmdev.model.card.loot;

import org.camposmdev.model.card.attribute.CardType;

public class SoulHeartCard extends LootCard {
    private byte version;
    private byte value;

    public SoulHeartCard() {
        super.setCardType(CardType.SHEART);
    }

    public byte version() {
        return version;
    }

    public SoulHeartCard setVersion(byte version) {
        this.version = version;
        return this;
    }

    public byte value() {
        return value;
    }

    public SoulHeartCard setValue(byte value) {
        this.value = value;
        return this;
    }
}
