package io.github.camposmdev.foursouls.model.card.loot;

import io.github.camposmdev.foursouls.model.card.attribute.loot.RuneType;
import io.github.camposmdev.foursouls.model.card.attribute.CardType;

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
