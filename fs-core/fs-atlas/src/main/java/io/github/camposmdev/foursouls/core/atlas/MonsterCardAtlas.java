package io.github.camposmdev.foursouls.core.atlas;

import io.github.camposmdev.foursouls.core.card.attribute.CardType;
import io.github.camposmdev.foursouls.core.card.monster.*;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class MonsterCardAtlas implements CardAtlas<BaseMonsterCard> {
    protected Map<String, MonsterCard> bmonster;
    protected Map<String, MonsterCard> cmonster;
    protected Map<String, MonsterCard> hmonster;
    protected Map<String, MonsterCard> chamonster;
    protected Map<String, GoodEventCard> gevent;
    protected Map<String, BadEventCard> bevent;
    protected Map<String, CurseCard> curse;
    protected Map<String, MonsterCard> boss;
    protected Map<String, MonsterCard> epic;

    public MonsterCardAtlas() {
        this.bmonster = new HashMap<>();
        this.cmonster = new HashMap<>();
        this.hmonster = new HashMap<>();
        this.chamonster = new HashMap<>();
        this.gevent = new HashMap<>();
        this.bevent = new HashMap<>();
        this.curse = new HashMap<>();
        this.boss = new HashMap<>();
        this.epic = new HashMap<>();
    }

    @Override
    public void add(BaseMonsterCard card) {
        switch (card.getCardType()) {
            case BMONSTER -> bmonster.put(card.getId(), (MonsterCard) card);
            case CMONSTER -> cmonster.put(card.getId(), (MonsterCard) card);
            case HMONSTER -> hmonster.put(card.getId(), (MonsterCard) card);
            case CHAMONSTER -> chamonster.put(card.getId(), (MonsterCard) card);
            case GEVENT -> gevent.put(card.getId(), (GoodEventCard) card);
            case BEVENT -> bevent.put(card.getId(), (BadEventCard) card);
            case CURSE -> curse.put(card.getId(), (CurseCard) card);
            case BOSS -> boss.put(card.getId(), (MonsterCard) card);
            case EPIC -> epic.put(card.getId(), (MonsterCard) card);
        }

    }

    @Override
    public boolean contains(CardType cardType, String id) {
        return switch (cardType) {
            case BMONSTER -> bmonster.containsKey(id);
            case CMONSTER -> cmonster.containsKey(id);
            case HMONSTER -> hmonster.containsKey(id);
            case CHAMONSTER -> chamonster.containsKey(id);
            case GEVENT -> gevent.containsKey(id);
            case BEVENT -> bevent.containsKey(id);
            case CURSE -> curse.containsKey(id);
            case BOSS -> boss.containsKey(id);
            case EPIC -> epic.containsKey(id);
            default -> false;
        };
    }

    @Override
    public List<BaseMonsterCard> cards() {
        List<BaseMonsterCard> lst = new LinkedList<>();
        lst.addAll(bmonster.values());
        lst.addAll(cmonster.values());
        lst.addAll(hmonster.values());
        lst.addAll(chamonster.values());
        lst.addAll(gevent.values());
        lst.addAll(bevent.values());
        lst.addAll(curse.values());
        lst.addAll(boss.values());
        lst.addAll(epic.values());
        return lst;
    }
}
