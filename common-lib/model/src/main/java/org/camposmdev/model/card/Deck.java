package org.camposmdev.model.card;

import java.io.Serializable;
import java.util.Collections;
import java.util.LinkedList;

public class Deck<T extends BaseCard> implements Serializable {
    private LinkedList<T> list;

    public Deck() {
        this.list = new LinkedList<>();
    }

    public void shuffle() {
        Collections.shuffle(list);
    }

    public void add(int i, T card) {
        list.add(i, card);
    }

    public void add(T card) {
        list.addFirst(card);
    }

    public T draw() {
        return list.removeFirst();
    }
}
