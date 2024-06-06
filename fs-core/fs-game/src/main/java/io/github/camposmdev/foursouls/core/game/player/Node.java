package io.github.camposmdev.foursouls.core.game.player;

public class Node<T> {
    protected T data;
    protected Node<T> next;
    protected Node<T> prev;

    public Node() {
        this(null);
    }

    public Node(T data) {
        this.data = data;
        this.next = null;
        this.prev = null;
    }
}
