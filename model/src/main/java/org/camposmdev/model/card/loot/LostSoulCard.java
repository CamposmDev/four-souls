package org.camposmdev.model.card.loot;

import org.camposmdev.model.card.attribute.CardType;
import org.camposmdev.model.card.attribute.CardVersion;

public class LostSoulCard extends LootCard {
    private CardVersion version;

    public LostSoulCard() {
        super.setCardType(CardType.LSOUL);
    }

    public CardVersion version() {
        return version;
    }

    public LostSoulCard setVersion(CardVersion version) {
        this.version = version;
        return this;
    }
}
