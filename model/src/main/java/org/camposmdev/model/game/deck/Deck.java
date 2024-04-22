package org.camposmdev.model.game.deck;

import org.camposmdev.model.card.BaseCard;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.function.Predicate;

public class Deck<T extends BaseCard> {
    private List<T> cards;

    public Deck() {
        this.cards = new LinkedList<>();
    }

    public Deck(List<T> lst) {
        this.cards = new LinkedList<>(lst);
    }

    public void shuffle(Random rand) {
        Collections.shuffle(cards, rand);
    }

    public T draw() {
        if (cards.isEmpty()) return null;
        return cards.removeFirst();
    }

    public void push(T card) {
        cards.addFirst(card);
    }

    public void append(T card) {
        cards.addLast(card);
    }

    public boolean contains(Predicate<T> p) {
        for (T card : cards) {
            if (p.test(card)) return true;
        }
        return false;
    }

    public List<T> peek(int amount) {
        if (amount > cards.size())
            return cards;
        return cards.subList(0, amount);
    }
}
