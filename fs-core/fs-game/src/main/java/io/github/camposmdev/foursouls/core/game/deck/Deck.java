package io.github.camposmdev.foursouls.core.game.deck;

import io.github.camposmdev.foursouls.core.card.BaseCard;

import java.util.*;
import java.util.function.Predicate;

/**
 * Represents a deck of cards. This class provides functionality to manage a deck of cards,
 * including shuffling, drawing, adding, and discarding cards.
 *
 * @param <T> the type of cards this deck will hold, extending {@link BaseCard}
 */
public class Deck<T extends BaseCard> {
    private final List<T> cards;

    /**
     * Constructs an empty deck.
     */
    public Deck() {
        this.cards = new LinkedList<>();
    }

    /**
     * Constructs a deck with a given list of cards.
     *
     * @param lst the list of cards to initialize the deck with
     */
    public Deck(List<T> lst) {
        this.cards = new LinkedList<>(lst);
    }

    /**
     * Shuffles the deck using the specified random number generator.
     *
     * @param rand the random number generator to use for shuffling
     */
    public void shuffle(Random rand) {
        Collections.shuffle(cards, rand);
    }

    /**
     * Draws the top card from the deck.
     *
     * @return the drawn card, or null if the deck is empty
     */
    public T draw() {
        if (cards.isEmpty()) return null;
        return cards.removeFirst();
    }

    /**
     * Draws the specified number of cards from the top of the deck.
     *
     * @param n the number of cards to draw
     * @return a list of drawn cards
     */
    public List<T> draw(int n) {
        List<T> lst = new LinkedList<>();
        for (int i = 0; i < n && !cards.isEmpty(); i++) {
            lst.add(cards.remove(0));
        }
        return lst;
    }

    /**
     * Pushes a card onto the top of the deck.
     *
     * @param card the card to push onto the deck
     */
    public void push(T card) {
        cards.add(0, card);
    }

    /**
     * Appends a card to the bottom of the deck.
     *
     * @param card the card to append to the deck
     */
    public void append(T card) {
        cards.add(card);
    }

    /**
     * Checks if the deck contains a card that matches the specified predicate.
     *
     * @param p the predicate to test the cards
     * @return true if a matching card is found, otherwise false
     */
    public boolean contains(Predicate<T> p) {
        for (T card : cards) {
            if (p.test(card)) return true;
        }
        return false;
    }

    /**
     * Finds the first card in the deck that matches the specified predicate.
     *
     * @param p the predicate to test the cards
     * @return the first matching card, or null if no matching card is found
     * @throws NoSuchElementException If no element found.
     */
    public T find(Predicate<T> p) throws NoSuchElementException {
        return cards.stream().filter(p).findFirst().orElseThrow();
    }

    /**
     * Peeks at the specified number of cards from the top of the deck without removing them.
     *
     * @param amount the number of cards to peek at
     * @return a list of the top cards
     */
    public List<T> peek(int amount) {
        if (amount > cards.size()) {
            return new LinkedList<>(cards);
        }
        return new LinkedList<>(cards.subList(0, amount));
    }

    /**
     * Returns the list of cards currently in the deck.
     *
     * @return the list of cards in the deck
     */
    public List<T> cards() {
        return cards;
    }
}
