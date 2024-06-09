package io.github.camposmdev.foursouls.core.atlas;

import io.github.camposmdev.foursouls.core.card.attribute.CardType;
import io.github.camposmdev.foursouls.core.card.loot.*;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class LootCardAtlas implements ICardAtlas<LootCard> {
    protected Map<String, BatteryCard> batteries;
    protected Map<String, BlackHeartCard> bheart;
    protected Map<String, BombCard> bombs;
    protected Map<String, ButterBeanCard> butter;
    protected Map<String, TarotCard> cards;
    protected Map<String, DiceShardCard> dice;
    protected Map<String, KeyCard> keys;
    protected Map<String, LostSoulCard> lsoul;
    protected Map<String, PillCard> pills;
    protected Map<String, RuneCard> runes;
    protected Map<String, SackCard> sack;
    protected Map<String, SoulHeartCard> sheart;
    protected Map<String, TrinketCard> trinket;
    protected Map<String, WildCard> wildcard;
    protected MoneyCardAtlas money;

    public LootCardAtlas() {
        this.batteries = new HashMap<>();
        this.bheart = new HashMap<>();
        this.bombs = new HashMap<>(); /* TODO - update */
        this.butter = new HashMap<>();
        this.cards = new HashMap<>();
        this.dice = new HashMap<>();
        this.keys = new HashMap<>();
        this.lsoul = new HashMap<>();
        this.pills = new HashMap<>(); /* TODO - update */
        this.runes = new HashMap<>(); /* TODO - update */
        this.sack = new HashMap<>();
        this.sheart = new HashMap<>();
        this.trinket = new HashMap<>();
        this.wildcard = new HashMap<>();    /* TODO - update */
        this.money = new MoneyCardAtlas();
    }

    @Override
    public void add(LootCard card) {
        switch (card.getCardType()) {
            case BATTERIES -> batteries.put(card.getId(), (BatteryCard) card);
            case BHEART -> bheart.put(card.getId(), (BlackHeartCard) card);
            case BOMBS -> bombs.put(card.getId(), (BombCard) card);
            case BUTTER -> butter.put(card.getId(), (ButterBeanCard) card);
            case CARDS -> cards.put(card.getId(), (TarotCard) card);
            case DICE -> dice.put(card.getId(), (DiceShardCard) card);
            case KEYS -> keys.put(card.getId(), (KeyCard) card);
            case LSOUL -> lsoul.put(card.getId(), (LostSoulCard) card);
            case PILLS -> pills.put(card.getId(), (PillCard) card);
            case RUNES -> runes.put(card.getId(), (RuneCard) card);
            case SACK -> sack.put(card.getId(), (SackCard) card);
            case SHEART -> sheart.put(card.getId(), (SoulHeartCard) card);
            case TRINKETS -> trinket.put(card.getId(), (TrinketCard) card);
            case WILDCARD -> wildcard.put(card.getId(), (WildCard) card);
            case MONEY1C, MONEY2C, MONEY3C, MONEY4C, MONEY5C, MONEY10C -> money.add((MoneyCard) card);
        }
    }

    @Override
    public boolean contains(CardType cardType, String id) {
        return switch (cardType) {
            case BATTERIES -> batteries.containsKey(id);
            case BHEART -> bheart.containsKey(id);
            case BOMBS -> bombs.containsKey(id);
            case BUTTER -> butter.containsKey(id);
            case CARDS -> cards.containsKey(id);
            case DICE -> dice.containsKey(id);
            case KEYS -> keys.containsKey(id);
            case LSOUL -> lsoul.containsKey(id);
            case PILLS -> pills.containsKey(id);
            case RUNES -> runes.containsKey(id);
            case SACK -> sack.containsKey(id);
            case SHEART -> sheart.containsKey(id);
            case TRINKETS -> trinket.containsKey(id);
            case WILDCARD -> wildcard.containsKey(id);
            case MONEY1C, MONEY2C, MONEY3C, MONEY4C, MONEY5C, MONEY10C -> money.contains(cardType, id);
            default -> false;
        };
    }

    @Override
    public List<LootCard> cards() {
        List<LootCard> lst = new LinkedList<>();
        // Add values from each map to the list
        lst.addAll(batteries.values());
        lst.addAll(bheart.values());
        lst.addAll(bombs.values());
        lst.addAll(butter.values());
        lst.addAll(cards.values());
        lst.addAll(dice.values());
        lst.addAll(keys.values());
        lst.addAll(lsoul.values());
        lst.addAll(pills.values());
        lst.addAll(runes.values());
        lst.addAll(sack.values());
        lst.addAll(sheart.values());
        lst.addAll(trinket.values());
        lst.addAll(wildcard.values());
        // Add money cards
        lst.addAll(money.cards());
        return lst;
    }
}
