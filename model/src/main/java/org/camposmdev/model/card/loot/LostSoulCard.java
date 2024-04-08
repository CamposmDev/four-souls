package org.camposmdev.model.card.loot;

import org.camposmdev.model.card.attribute.CardType;

public class LostSoulCard extends LootCard {
    private byte version;

    public LostSoulCard() {
        super.setCardType(CardType.LSOUL);
    }

    public byte version() {
        return version;
    }

    public LostSoulCard setVersion(byte version) {
        this.version = version;
        return this;
    }
}
