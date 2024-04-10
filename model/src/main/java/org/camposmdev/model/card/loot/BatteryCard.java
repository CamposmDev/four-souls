package org.camposmdev.model.card.loot;

import org.camposmdev.model.card.attribute.CardType;
import org.camposmdev.model.card.attribute.CardVersion;

public class BatteryCard extends LootCard {
    private CardVersion version;

    public BatteryCard() {
        super.setCardType(CardType.BATTERIES);
    }

    public CardVersion version() {
        return version;
    }

    public BatteryCard setVersion(CardVersion version) {
        this.version = version;
        return this;
    }
}
