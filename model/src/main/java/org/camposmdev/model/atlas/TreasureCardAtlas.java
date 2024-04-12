package org.camposmdev.model.atlas;

import org.camposmdev.model.card.attribute.CardType;
import org.camposmdev.model.card.treasure.*;

import java.util.HashMap;
import java.util.Map;

public class TreasureCardAtlas implements CardAtlas<TreasureCard> {
    protected final Map<String, ActiveTreasureCard> atreasure;
    protected final Map<String, OneUseTreasureCard> otreasure;
    protected final Map<String, PaidTreasureCard> paidtreasure;
    protected final Map<String, PassiveTreasureCard> ptreasure;
    protected final Map<String, SoulTreasureCard> streasure;

    public TreasureCardAtlas() {
        this.atreasure = new HashMap<>();
        this.otreasure = new HashMap<>();
        this.paidtreasure = new HashMap<>();
        this.ptreasure = new HashMap<>();
        this.streasure = new HashMap<>();
    }

    @Override
    public void add(TreasureCard card) {
        switch (card.getCardType()) {
            case ATREASURE -> atreasure.put(card.getId(), (ActiveTreasureCard) card);
            case OTREASURE -> otreasure.put(card.getId(), (OneUseTreasureCard) card);
            case PAIDTREASURE -> paidtreasure.put(card.getId(), (PaidTreasureCard) card);
            case PTREASURE -> ptreasure.put(card.getId(), (PassiveTreasureCard) card);
            case STREASURE -> streasure.put(card.getId(), (SoulTreasureCard) card);
        }
    }

    @Override
    public boolean contains(CardType cardType, String id) {
        return switch (cardType) {
            case ATREASURE -> atreasure.containsKey(id);
            case OTREASURE -> otreasure.containsKey(id);
            case PAIDTREASURE -> paidtreasure.containsKey(id);
            case PTREASURE -> ptreasure.containsKey(id);
            case STREASURE -> streasure.containsKey(id);
            default -> false;
        };
    }
}
