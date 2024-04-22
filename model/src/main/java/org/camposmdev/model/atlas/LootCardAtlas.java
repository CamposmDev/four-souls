package org.camposmdev.model.atlas;

import org.camposmdev.model.card.attribute.CardType;
import org.camposmdev.model.card.loot.*;

import java.util.HashMap;
import java.util.Map;

public class LootCardAtlas implements CardAtlas<LootCard> {
    protected Map<String, BatteryCard> batteries;
    protected Map<String, BlackHeartCard> bheart;
    protected Map<String, BombCard> bomb;
    protected Map<String, ButterBeanCard> butter;
    protected Map<String, TarotCard> cards;
    protected Map<String, DiceShardCard> dice;
    protected Map<String, KeyCard> key;
    protected Map<String, LostSoulCard> lsoul;
    protected Map<String, PillCard> pill;
    protected Map<String, RuneCard> rune;
    protected Map<String, SackCard> sack;
    protected Map<String, SoulHeartCard> sheart;
    protected Map<String, TrinketCard> trinket;
    protected Map<String, WildCard> wild;
    protected MoneyCardAtlas money;

    public LootCardAtlas() {
        this.batteries = new HashMap<>();
        this.bheart = new HashMap<>();
        this.bomb = new HashMap<>();
        this.butter = new HashMap<>();
        this.cards = new HashMap<>();
        this.dice = new HashMap<>();
        this.key = new HashMap<>();
        this.lsoul = new HashMap<>();
        this.pill = new HashMap<>();
        this.rune = new HashMap<>();
        this.sack = new HashMap<>();
        this.sheart = new HashMap<>();
        this.trinket = new HashMap<>();
        this.wild = new HashMap<>();
        this.money = new MoneyCardAtlas();
    }

    @Override
    public void add(LootCard card) {
        switch (card.getCardType()) {
            case BATTERIES -> batteries.put(card.getId(), (BatteryCard) card);
            case BHEART -> bheart.put(card.getId(), (BlackHeartCard) card);
            case BOMBS -> bomb.put(card.getId(), (BombCard) card);
            case BUTTER -> butter.put(card.getId(), (ButterBeanCard) card);
            case CARDS -> cards.put(card.getId(), (TarotCard) card);
            case DICE -> dice.put(card.getId(), (DiceShardCard) card);
            case KEYS -> key.put(card.getId(), (KeyCard) card);
            case LSOUL -> lsoul.put(card.getId(), (LostSoulCard) card);
            case PILLS -> pill.put(card.getId(), (PillCard) card);
            case RUNES -> rune.put(card.getId(), (RuneCard) card);
            case SACK -> sack.put(card.getId(), (SackCard) card);
            case SHEART -> sheart.put(card.getId(), (SoulHeartCard) card);
            case TRINKETS -> trinket.put(card.getId(), (TrinketCard) card);
            case WILDCARD -> wild.put(card.getId(), (WildCard) card);
            case MONEY1C, MONEY2C, MONEY3C, MONEY4C, MONEY5C, MONEY10C -> money.add((MoneyCard) card);
        }
    }

    @Override
    public boolean contains(CardType cardType, String id) {
        return switch (cardType) {
            case BATTERIES -> batteries.containsKey(id);
            case BHEART -> bheart.containsKey(id);
            case BOMBS -> bomb.containsKey(id);
            case BUTTER -> butter.containsKey(id);
            case CARDS -> cards.containsKey(id);
            case DICE -> dice.containsKey(id);
            case KEYS -> key.containsKey(id);
            case LSOUL -> lsoul.containsKey(id);
            case PILLS -> pill.containsKey(id);
            case RUNES -> rune.containsKey(id);
            case SACK -> sack.containsKey(id);
            case SHEART -> sheart.containsKey(id);
            case TRINKETS -> trinket.containsKey(id);
            case WILDCARD -> wild.containsKey(id);
            case MONEY1C, MONEY2C, MONEY3C, MONEY4C, MONEY5C, MONEY10C -> money.contains(cardType, id);
            default -> false;
        };
    }
}
