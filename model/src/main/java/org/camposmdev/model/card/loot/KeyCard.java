package org.camposmdev.model.card.loot;

import org.camposmdev.model.card.attribute.CardType;
import org.camposmdev.model.card.attribute.loot.LootOptionEvent;

public class KeyCard extends LootCard {
    private boolean goldKey;
    private LootOptionEvent[] options;

    public KeyCard() {
        super.setCardType(CardType.KEYS);
    }

    public boolean goldKey() {
        return goldKey;
    }

    public KeyCard setGoldKey(boolean goldKey) {
        this.goldKey = goldKey;
        return this;
    }

    public LootOptionEvent[] options() {
        return options;
    }

    public KeyCard setOptions(LootOptionEvent[] options) {
        this.options = options;
        return this;
    }
}
