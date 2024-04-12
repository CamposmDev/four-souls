package org.camposmdev.model.atlas;

import org.camposmdev.model.card.attribute.CardType;
import org.camposmdev.model.card.loot.MoneyCard;

import java.util.HashMap;
import java.util.Map;

public class MoneyCardAtlas implements CardAtlas<MoneyCard> {
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
}
