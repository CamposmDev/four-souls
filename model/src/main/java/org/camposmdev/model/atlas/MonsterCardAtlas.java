package org.camposmdev.model.atlas;

import org.camposmdev.model.card.monster.*;

import java.util.HashMap;
import java.util.Map;

public class MonsterCardAtlas {
    private final Map<String, MonsterCard> bmonster;
    private final Map<String, MonsterCard> cmonster;
    private final Map<String, MonsterCard> hmonster;
    private final Map<String, MonsterCard> chamonster;
    private final Map<String, ChallengeMonsterCard> challenge;
    private final Map<String, GoodEventCard> gevent;
    private final Map<String, BadEventCard> bevent;
    private final Map<String, CurseCard> curse;
    private final Map<String, MonsterCard> boss;
    private final Map<String, MonsterCard> epic;

    public MonsterCardAtlas() {
        this.bmonster = new HashMap<>();
        this.cmonster = new HashMap<>();
        this.hmonster = new HashMap<>();
        this.chamonster = new HashMap<>();
        this.challenge = new HashMap<>();
        this.gevent = new HashMap<>();
        this.bevent = new HashMap<>();
        this.curse = new HashMap<>();
        this.boss = new HashMap<>();
        this.epic = new HashMap<>();
    }

    public void add(AbstractMonsterCard card) {
        switch (card.cardType()) {
            case BMONSTER -> bmonster.put(card.id(), (MonsterCard) card);
            case CMONSTER -> cmonster.put(card.id(), (MonsterCard) card);
            case HMONSTER -> hmonster.put(card.id(), (MonsterCard) card);
            case CHAMONSTER -> chamonster.put(card.id(), (MonsterCard) card);
            case GEVENT -> gevent.put(card.id(), (GoodEventCard) card);
            case BEVENT -> bevent.put(card.id(), (BadEventCard) card);
            case CURSE -> curse.put(card.id(), (CurseCard) card);
            case BOSS -> boss.put(card.id(), (MonsterCard) card);
            case EPIC -> epic.put(card.id(), (MonsterCard) card);
        }

    }
}
