package org.camposmdev.model.atlas;

import org.camposmdev.model.card.loot.*;

import java.util.HashMap;
import java.util.Map;

public class LootCardAtlas {
    private final Map<String, BatteryCard> battery;
    private final Map<String, BlackHeartCard> bheart;
    private final Map<String, BombCard> bomb;
    private final Map<String, ButterBeanCard> butter;
    private final Map<String, DiceShardCard> diceShard;
    private final Map<String, KeyCard> key;
    private final Map<String, LostSoulCard> lsoul;
    private final Map<String, PillCard> pill;
    private final Map<String, RuneCard> rune;
    private final Map<String, SackCard> sack;
    private final Map<String, SoulHeartCard> sheart;
    private final Map<String, TrinketCard> trinket;
    private final Map<String, WildCard> wild;
    private final Map<String, MoneyCard> money;

    public LootCardAtlas() {
        this.battery = new HashMap<>();
        this.bheart = new HashMap<>();
        this.bomb = new HashMap<>();
        this.butter = new HashMap<>();
        this.diceShard = new HashMap<>();
        this.key = new HashMap<>();
        this.lsoul = new HashMap<>();
        this.pill = new HashMap<>();
        this.rune = new HashMap<>();
        this.sack = new HashMap<>();
        this.sheart = new HashMap<>();
        this.trinket = new HashMap<>();
        this.wild = new HashMap<>();
        this.money = new HashMap<>();
    }

    public void add(LootCard card) {
        switch (card.cardType()) {
            case BATTERIES -> battery.put(card.id(), (BatteryCard) card);
            case BHEART -> bheart.put(card.id(), (BlackHeartCard) card);
            case BOMBS -> bomb.put(card.id(), (BombCard) card);
            case BUTTER -> butter.put(card.id(), (ButterBeanCard) card);
            case DICE -> diceShard.put(card.id(), (DiceShardCard) card);
            case KEYS -> key.put(card.id(), (KeyCard) card);
            case LSOUL -> lsoul.put(card.id(), (LostSoulCard) card);
            case PILLS -> pill.put(card.id(), (PillCard) card);
            case RUNES -> rune.put(card.id(), (RuneCard) card);
            case SACK -> sack.put(card.id(), (SackCard) card);
            case SHEART -> sheart.put(card.id(), (SoulHeartCard) card);
            case TRINKETS -> trinket.put(card.id(), (TrinketCard) card);
            case WILDCARD -> wild.put(card.id(), (WildCard) card);
            case MONEY1C, MONEY2C, MONEY3C, MONEY4C, MONEY5C, MONEY10C -> money.put(card.id(), (MoneyCard) card);
        }
    }
}
