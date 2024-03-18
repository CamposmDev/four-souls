package org.camposmdev.model.game.player;

public class Players {
    Node head;
    protected Node curr;
    protected int size;

    public Players() {
        this.head = new Node();
        this.head.next = head;
        this.head.prev = head;
        this.curr = head;
        this.size = 0;
    }

    /**
     * Checks if a player turn is in progress
     * @param p Checks if given player turn is ready
     * @return true if the Player ids match, otherwise false
     */
    public boolean isPlayerTurn(Player p) {
        if (curr == head) return false;
        return (curr.p.compareTo(p)) == 0;
    }

    /**
     * Returns the player whose on the left of the current player
     * @return Player if the size of the list > 1, otherwise null
     */
    public Player left() {
        if (size <= 1) return null;
        if (curr.prev == head) return curr.prev.prev.p;
        return curr.prev.p;
    }

    /**
     * Returns the player whose on the right of the current player
     * @return Player if the size of the list > 1, otherwise null
     */
    public Player right() {
        if (size <= 1) return null;
        if (curr.next == head) return curr.next.next.p;
        return curr.next.p;
    }

    /**
     * Returns the next player whose in line for their turn
     * @return Player if the list is not empty
     */
    public Player next() {
        if (isEmpty()) return null;
        if (curr == head) return (curr = curr.next).p;
        return (curr = curr.next).p;
    }

    /**
     * Returns the current player's turn
     * @return Player object stored in the current node
     */
    public Player peek() {
        return curr.p;
    }

    public void addPlayer(Player p) {
        var node = new Node(p);
        if (isEmpty()) {
            head.next = node;
            head.prev = node;
            node.next = head;
            node.prev = head;
            curr = node;
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
        this.size++;
    }

    public boolean isEmpty() {
        return this.size <= 0;
    }

    @Override
    public String toString() {
        var sb = new StringBuilder("[");
        var node = head.next;
        while (node != head) {
            sb.append(node.p.getId());
            node = node.next;
            if (node != head) {
                sb.append(", ");
            }
        }
        return sb.append("]").toString();
    }
}
