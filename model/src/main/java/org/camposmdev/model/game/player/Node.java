package org.camposmdev.model.game.player;

public class Node<T> {
    T data;
    Node<T> next;
    Node<T> prev;

    public Node() {
        this(null);
    }

    public Node(T data) {
        this.data = data;
        this.next = null;
        this.prev = null;
    }
}
