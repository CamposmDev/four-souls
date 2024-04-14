package org.camposmdev.model.card.loot;

import org.camposmdev.model.card.attribute.CardType;
import org.camposmdev.model.card.attribute.loot.RuneType;

public class RuneCard extends LootCard {
    private RuneType runeType;

    public RuneCard() {
        super.setCardType(CardType.RUNES);
    }

    public RuneType getRuneType() {
        return runeType;
    }

    public RuneCard setRuneType(RuneType runeType) {
        this.runeType = runeType;
        return this;
    }
}
