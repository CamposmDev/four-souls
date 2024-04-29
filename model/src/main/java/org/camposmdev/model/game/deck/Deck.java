package org.camposmdev.model.game.deck;

import org.camposmdev.model.card.BaseCard;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.function.Predicate;

public class Deck<T extends BaseCard> {
    private final List<T> cards;
    private List<T> discards;

    public Deck() {
        this.cards = new LinkedList<>();
        this.discards = new LinkedList<>();
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

    public List<T> draw(int n) {
        List<T> lst = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            lst.add(cards.removeFirst());
        }
        return lst;
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

    public T find(Predicate<T> p) {
        for (int i = 0; i < cards.size(); i++) {
            if (p.test(cards.get(i)))
                return cards.remove(i);
        }
        return null;
    }

    public List<T> peek(int amount) {
        if (amount > cards.size())
            return cards;
        return cards.subList(0, amount);
    }

    public List<T> cards() {
        return cards;
    }

    public void discard(T card) {
        discards.addFirst(card);
    }
}
