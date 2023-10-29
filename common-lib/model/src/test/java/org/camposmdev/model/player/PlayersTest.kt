package org.camposmdev.model.player

import org.junit.jupiter.api.Test

class PlayersTest {
    @Test
    fun add_to_empty() {
        var p1 = Player(null)
        var list = Players()
        list.addLast(p1)
        var node = list.head
        assert(node.next.p == p1)
        assert(node.prev.p == p1)
    }

    @Test
    fun add_many() {
        var list = Players()
        var p1 = Player(null)
        var p2 = Player(null)
        var p3 = Player(null)

        var str = '[' + p1.getId().toString() + ", " + p2.getId().toString() + ", " + p3.getId().toString() + ']'

        list.addLast(p1)
        list.addLast(p2)
        list.addLast(p3)

        var node = list.head
        assert(node.next.p == p1)
        assert(node.next.next.p == p2)
        assert(node.next.next.next.p == p3)
        assert(node.prev.p == p3)
        assert(node.prev.prev.p == p2)
        assert(node.prev.prev.prev.p == p1)
        assert(str.equals(list.toString()))
    }
}