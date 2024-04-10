package org.camposmdev.model.atlas;

import org.camposmdev.model.card.treasure.*;

import java.util.HashMap;
import java.util.Map;

public class TreasureCardAtlas {
    private final Map<String, ActiveTreasureCard> atreasure;
    private final Map<String, OneTimeUseTreasureCard> otreasure;
    private final Map<String, PaidTreasureCard> paidtreasure;
    private final Map<String, PassiveTreasureCard> ptreasure;
    private final Map<String, SoulTreasureCard> streasure;

    public TreasureCardAtlas() {
        this.atreasure = new HashMap<>();
        this.otreasure = new HashMap<>();
        this.paidtreasure = new HashMap<>();
        this.ptreasure = new HashMap<>();
        this.streasure = new HashMap<>();
    }

    public void add(TreasureCard card) {
        switch (card.cardType()) {
            case ATREASURE -> atreasure.put(card.id(), (ActiveTreasureCard) card);
            case OTREASURE -> otreasure.put(card.id(), (OneTimeUseTreasureCard) card);
            case PAIDTREASURE -> paidtreasure.put(card.id(), (PaidTreasureCard) card);
            case PTREASURE -> ptreasure.put(card.id(), (PassiveTreasureCard) card);
            case STREASURE -> streasure.put(card.id(), (SoulTreasureCard) card);
        }
    }
}
