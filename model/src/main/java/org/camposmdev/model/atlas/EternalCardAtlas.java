package org.camposmdev.model.atlas;

import org.camposmdev.model.card.eternal.*;

import java.util.HashMap;
import java.util.Map;

public class EternalCardAtlas {
    private Map<String, ActiveEternalCard> aeternal;
    private Map<String, PaidEternalCard> paideternal;
    private Map<String, PassiveEternalCard> peternal;
    private Map<String, SoulEternalCard> seternal;


    public EternalCardAtlas() {
        this.aeternal = new HashMap<>();
        this.paideternal = new HashMap<>();
        this.peternal = new HashMap<>();
        this.seternal = new HashMap<>();
    }

    public void add(EternalCard card) {
        switch (card.cardType()) {
            case AETERNAL -> aeternal.put(card.id(), (ActiveEternalCard) card);
            case PAIDETERNAL -> paideternal.put(card.id(), (PaidEternalCard) card);
            case PETERNAL -> peternal.put(card.id(), (PassiveEternalCard) card);
            case SETERNAL -> seternal.put(card.id(), (SoulEternalCard) card);
        }
    }
}
