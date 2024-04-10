package org.camposmdev.model.card.loot;

import org.camposmdev.model.card.attribute.CardType;
import org.camposmdev.model.card.attribute.CardVersion;

public class SoulHeartCard extends LootCard {
    private CardVersion version;

    public SoulHeartCard() {
        super.setCardType(CardType.SHEART);
    }

    public CardVersion version() {
        return version;
    }

    public SoulHeartCard setVersion(CardVersion version) {
        this.version = version;
        return this;
    }
}
