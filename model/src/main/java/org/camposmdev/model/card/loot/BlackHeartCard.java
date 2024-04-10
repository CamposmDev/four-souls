package org.camposmdev.model.card.loot;

import org.camposmdev.model.card.attribute.CardType;
import org.camposmdev.model.card.attribute.CardVersion;

public class BlackHeartCard extends LootCard {
    private CardVersion version;

    public BlackHeartCard() {
        super.setCardType(CardType.BHEART);
    }

    public CardVersion version() {
        return version;
    }

    public BlackHeartCard setVersion(CardVersion version) {
        this.version = version;
        return this;
    }
}
