package io.github.camposmdev.foursouls.core.atlas;

import io.github.camposmdev.foursouls.core.card.attribute.CardType;
import io.github.camposmdev.foursouls.core.card.eternal.*;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class EternalCardAtlas implements CardAtlas<EternalCard> {
    protected Map<String, ActiveEternalCard> aeternal;
    protected Map<String, PaidEternalCard> paideternal;
    protected Map<String, PassiveEternalCard> peternal;
    protected Map<String, SoulEternalCard> seternal;


    public EternalCardAtlas() {
        this.aeternal = new HashMap<>();
        this.paideternal = new HashMap<>();
        this.peternal = new HashMap<>();
        this.seternal = new HashMap<>();
    }

    @Override
    public void add(EternalCard card) {
        switch (card.getCardType()) {
            case AETERNAL -> aeternal.put(card.getId(), (ActiveEternalCard) card);
            case PAIDETERNAL -> paideternal.put(card.getId(), (PaidEternalCard) card);
            case PETERNAL -> peternal.put(card.getId(), (PassiveEternalCard) card);
            case SETERNAL -> seternal.put(card.getId(), (SoulEternalCard) card);
        }
    }

    @Override
    public boolean contains(CardType cardType, String id) {
        return switch (cardType) {
            case AETERNAL -> aeternal.containsKey(id);
            case PAIDETERNAL -> paideternal.containsKey(id);
            case PETERNAL -> peternal.containsKey(id);
            case SETERNAL -> seternal.containsKey(id);
            default -> false;
        };
    }

    @Override
    public List<EternalCard> cards() {
        List<EternalCard> lst = new LinkedList<>();
        lst.addAll(aeternal.values());
        lst.addAll(paideternal.values());
        lst.addAll(peternal.values());
        lst.addAll(seternal.values());
        return lst;
    }
}
