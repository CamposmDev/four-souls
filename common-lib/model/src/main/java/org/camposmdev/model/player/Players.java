package org.camposmdev.model.player;

public class Players {
    protected Node head;
    protected Node current;

    public Players() {
        this.head = new Node();
        this.head.next = head;
        this.head.prev = head;
        this.current = head;
    }

    public void addLast(Player p) {
        var node = new Node(p);
        if (isEmpty()) {
            head.next = node;
            head.prev = node;
            node.next = head;
            node.prev = head;
        } else {
            /* find the last node of the list that points to {head} */
            var nodeX = head;
            while (nodeX.next != head) {
                nodeX = nodeX.next;
            }
            /* update pointers */
            nodeX.next = node;
            node.prev = nodeX;
            node.next = head;
            head.prev = node;
        }
    }

    public boolean isEmpty() {
        return this.head == this.head.next;
    }

    @Override
    public String toString() {
        String s = "[";
        var node = head.next;
        while (node != head) {
            s += node.p.getId().toString();
            node = node.next;
            if (node != head) {
                s += ", ";
            }
        }
        return s + ']';
    }
}
