package org.camposmdev.model.card.loot;

import org.camposmdev.model.card.attribute.CardType;

public class SackCard extends LootCard {
    private byte loot;

    public SackCard() {
        super.setCardType(CardType.SACK);
    }

    public byte loot() {
        return loot;
    }

    public SackCard setLoot(byte loot) {
        this.loot = loot;
        return this;
    }
}
