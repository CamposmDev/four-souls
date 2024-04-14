package org.camposmdev.model.card.loot;

import org.camposmdev.model.card.attribute.CardType;
import org.camposmdev.model.card.attribute.loot.CardKind;

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
