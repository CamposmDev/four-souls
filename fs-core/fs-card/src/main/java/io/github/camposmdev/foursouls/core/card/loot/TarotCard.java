package io.github.camposmdev.foursouls.core.card.loot;

import io.github.camposmdev.foursouls.core.card.attribute.CardType;
import io.github.camposmdev.foursouls.core.card.attribute.loot.CardKind;

public class TarotCard extends LootCard {
    private CardKind kind;

    public TarotCard() {
        this.setCardType(CardType.CARDS);
    }

    public CardKind getKind() {
        return kind;
    }

    public TarotCard setKind(CardKind kind) {
        this.kind = kind;
        return this;
    }
}
