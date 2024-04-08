package org.camposmdev.model.card.loot;

import org.camposmdev.model.card.attribute.CardType;

public class KeyCard extends LootCard {
    private boolean goldKey;
    private OptionEvent[] options;

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

    public OptionEvent[] options() {
        return options;
    }

    public KeyCard setOptions(OptionEvent[] options) {
        this.options = options;
        return this;
    }
}
