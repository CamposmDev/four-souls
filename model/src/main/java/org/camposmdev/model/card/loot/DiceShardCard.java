package org.camposmdev.model.card.loot;

import org.camposmdev.model.card.attribute.CardType;

public class DiceShardCard extends LootCard {
    private boolean reroll;

    public DiceShardCard() {
        super.setCardType(CardType.DICE);
    }

    public boolean reroll() {
        return reroll;
    }

    public DiceShardCard setReroll(boolean reroll) {
        this.reroll = reroll;
        return this;
    }
}
