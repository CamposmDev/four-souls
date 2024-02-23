package org.camposmdev.model.game.player;

public class Node {
    Player p;
    Node next;
    Node prev;

    public Node() {
        this(null);
    }

    public Node(Player p) {
        this.p = p;
        this.next = null;
        this.prev = null;
    }
}
