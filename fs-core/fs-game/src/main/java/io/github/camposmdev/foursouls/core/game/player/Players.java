package io.github.camposmdev.foursouls.core.game.player;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;

/**
 * The {@code Players} class represents a circular doubly linked list of player with a sentinel node
 * to simplify list operations. It provides methods for adding players, checking turns, and navigating
 * through the players.
 */
public class Players {
    protected Node<Player> sentinel;
    protected Node<Player> current;
    protected int turns;
    protected int size;

    public Players() {
        this.sentinel = new Node<>();
        this.sentinel.next = sentinel;
        this.sentinel.prev = sentinel;
        this.current = sentinel;
        this.turns = 1;
        this.size = 0;
    }

    public void add(Player p) {
        Node<Player> node = new Node<>(p);
        if (isEmpty()) { /* add a player for the first time */
            sentinel.next = node;
            sentinel.prev = node;
            node.next = sentinel;
            node.prev = sentinel;
            current = node;
        } else {
            /* update pointers */
            var tmp = sentinel.prev;
            tmp.next = node;
            sentinel.prev = node;
            node.prev = tmp;
            node.next = sentinel;
        }
        size++;
    }

    public void add(Player... players) {
        for (Player p : players) {
            add(p);
        }
    }

    /**
     * Returns players that match given predicate.
     * @param p Predicate to filter out players
     * @return List of player ids
     */
    public List<String> get(Predicate<Player> p) {
        List<String> lst = new LinkedList<>();
        var tmp = sentinel.next;
        while (tmp != sentinel) {
            if (p.test(tmp.data))
                lst.add(tmp.data.id());
            tmp = tmp.next;
        }
        return lst;
    }

    /**
     * Returns all the players
     * @return List of player ids
     */
    public List<String> get() {
        return get(p -> true);
    }

    /**
     * Set the player who goes first by their id
     * @param id ID of player to go first
     */
    public void setFirstPlayer(String id) {
        if (isEmpty()) return;
        var tmp = sentinel.next;
        while (tmp != sentinel) {
            if (tmp.data.id().equals(id)) {
                /* remove sentinel node  */
                sentinel.next.prev = sentinel.prev;
                sentinel.prev.next = sentinel.next;
                /* insert sentinel node */
                sentinel.prev = tmp.prev;
                tmp.prev.next = sentinel;
                sentinel.next = tmp;
                tmp.prev = sentinel;
                /* update current */
                current = tmp;
                break;
            }
            tmp = tmp.next;
        }
    }

    /**
     * Removes a player from the list by their id.
     * @param id Player ID to be removed.
     * @return true if player is removed. Otherwise, false.
     */
    public boolean remove(String id) {
        if (isEmpty()) return false;
        for (var tmp = sentinel.next; tmp != sentinel; tmp = tmp.next) {
            if (tmp.data.id().equals(id)) {
                /* update pointers, size, and curr */
                tmp.prev.next = tmp.next;
                tmp.next.prev = tmp.prev;
                /* if the current is tmp, then set curr to tmp.next */
                if (tmp == current)
                    current = tmp.next;
                size--;
                return true;
            }
        }
        return false;
    }

    /**
     * Returns the current player without advancing
     * @return Player object stored in {@link Players#current}
     */
    public Player peek() {
        return current.data;
    }

    /**
     * Checks if a player turn is in progress
     * @param id Checks if given player id is their turn
     * @return true if the Player ids match, otherwise false
     */
    public boolean isTheirTurn(String id) {
        if (current == sentinel) return false;
        return (current.data.id().equals(id));
    }

    /**
     * Returns the player whose on the left of the current player
     * @return Player object if the size is greater than 1, otherwise null
     */
    public Player left() {
        if (size <= 1) return null;
        if (current.prev == sentinel) return current.prev.prev.data;
        return current.prev.data;
    }

    /**
     * Returns the player whose on the right of the current player
     * @return Player object if the size is greater than 1, otherwise null.
     */
    public Player right() {
        /* if it's only one player, then return null. */
        if (size <= 1) return null;
        /* if the {curr.next} is the sentinel node, then return the sentinel's next */
        if (current.next == sentinel) return current.next.next.data;
        return current.next.data;
    }

    /**
     * Returns the next player whose next to take their turn. If everyone has had a turn, then the turn
     * counter is incremented by 1.
     * @return Player object if the list is not empty. Null if the list is empty. If the list size is 1, then {@link Players#current}
     */
    public Player next() {
        if (isEmpty()) return null;
        if (current.next == sentinel) {
            turns++;
            return (current = current.next.next).data;
        }
        return (current = current.next).data;
    }

    public int turns() {
        return turns;
    }

    public boolean isEmpty() {
        return size <= 0;
    }

    public int size() {
        return size;
    }

    @Override
    public String toString() {
        var sb = new StringBuilder("[");
        var node = sentinel.next;
        while (node != sentinel) {
            sb.append(node.data);
            node = node.next;
            if (node != sentinel) {
                sb.append(", ");
            }
        }
        return sb.append("]").toString();
    }
}
