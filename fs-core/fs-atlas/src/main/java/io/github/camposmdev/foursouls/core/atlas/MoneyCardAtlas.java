package io.github.camposmdev.foursouls.core.atlas;

import io.github.camposmdev.foursouls.core.card.attribute.CardType;
import io.github.camposmdev.foursouls.core.card.loot.MoneyCard;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class MoneyCardAtlas implements ICardAtlas<MoneyCard> {
    protected Map<String, MoneyCard> money1c;
    protected Map<String, MoneyCard> money2c;
    protected Map<String, MoneyCard> money3c;
    protected Map<String, MoneyCard> money4c;
    protected Map<String, MoneyCard> money5c;
    protected Map<String, MoneyCard> money10c;

    public MoneyCardAtlas() {
        this.money1c = new HashMap<>();
        this.money2c = new HashMap<>();
        this.money3c = new HashMap<>();
        this.money4c = new HashMap<>();
        this.money5c = new HashMap<>();
        this.money10c = new HashMap<>();
    }

    @Override
    public void add(MoneyCard card) {
        switch (card.getCardType()) {
            case MONEY1C -> money1c.put(card.getId(), card);
            case MONEY2C -> money2c.put(card.getId(), card);
            case MONEY3C -> money3c.put(card.getId(), card);
            case MONEY4C -> money4c.put(card.getId(), card);
            case MONEY5C -> money5c.put(card.getId(), card);
            case MONEY10C -> money10c.put(card.getId(), card);
        }
    }

    @Override
    public boolean contains(CardType cardType, String id) {
        return switch (cardType) {
            case MONEY1C -> money1c.containsKey(id);
            case MONEY2C -> money2c.containsKey(id);
            case MONEY3C -> money3c.containsKey(id);
            case MONEY4C -> money4c.containsKey(id);
            case MONEY5C -> money5c.containsKey(id);
            case MONEY10C -> money10c.containsKey(id);
            default -> false;
        };
    }

    @Override
    public List<MoneyCard> cards() {
        List<MoneyCard> lst = new LinkedList<>();
        // Add values from each map to the list
        lst.addAll(money1c.values());
        lst.addAll(money2c.values());
        lst.addAll(money3c.values());
        lst.addAll(money4c.values());
        lst.addAll(money5c.values());
        lst.addAll(money10c.values());
        return lst;
    }
}
