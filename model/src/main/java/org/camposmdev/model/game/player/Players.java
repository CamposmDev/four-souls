package org.camposmdev.model.game.player;

public class Players {
    Node<Player> head;
    protected Node<Player> curr;
    protected int size;

    public Players() {
        this.head = new Node<>();
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
        return (curr.data.compareTo(p)) == 0;
    }

    /**
     * Returns the player whose on the left of the current player
     * @return Player if the size of the list > 1, otherwise null
     */
    public Player left() {
        if (size <= 1) return null;
        if (curr.prev == head) return curr.prev.prev.data;
        return curr.prev.data;
    }

    /**
     * Returns the player whose on the right of the current player
     * @return Player if the size of the list > 1, otherwise null
     */
    public Player right() {
        if (size <= 1) return null;
        if (curr.next == head) return curr.next.next.data;
        return curr.next.data;
    }

    /**
     * Returns the next player whose next to take their turn
     * @return Player object if the list is not empty. Null if the list is empty. If the list size is 1, then {@link Players#curr}
     */
    public Player next() {
        if (isEmpty()) return null;
        if (curr == head) return (curr = curr.next).data;
        return (curr = curr.next).data;
    }

    /**
     * @return Player object stored in {@link Players#curr}
     */
    public Player peek() {
        return curr.data;
    }

    public void add(Player p) {
        var node = new Node<>(p);
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
            sb.append(node.data.getId());
            node = node.next;
            if (node != head) {
                sb.append(", ");
            }
        }
        return sb.append("]").toString();
    }
}
