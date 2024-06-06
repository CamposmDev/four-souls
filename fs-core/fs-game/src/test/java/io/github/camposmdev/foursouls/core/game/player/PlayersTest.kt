package io.github.camposmdev.foursouls.core.game.player

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class PlayersTest {
    @Test
    fun testAdd() {
        val lst = Players()
        val p1 = Player()
        val p2 = Player()
        lst.add(p1, p2)
        /* ensure lst size is 2 */
        assertEquals(2, lst.size())
    }
    
    @Test
    fun testRemove() {
        val lst = Players()
        val p1 = Player()
        val p2 = Player()
        val p3 = Player()
        lst.add(p1, p2)
        /* ensure calling remove() returns false for player not in lst */
        assertEquals(false, lst.remove(p3.id()))
        lst.remove(p1.id())
        /* ensure lst size is 1 after removing p1 */
        assertEquals(1, lst.size())
        /* ensure current node data is p2 */
        assert(p2 == lst.peek())
        lst.remove(p2.id())
        /* ensure lst is empty */
        assert(lst.isEmpty)
        /* ensure current node data is not p1 and not p2 */
        assert(lst.peek() != p1 && lst.peek() != p2)
    }

    @Test
    fun testPeekAndNext() {
        val lst = Players()
        val p1 = Player()
        val p2 = Player()
        /* ensure lst.next() is null, when it's empty */
        assert(lst.next() == null)
        /* ensure turn count is 1 */
        assertEquals(1, lst.turns())
        lst.add(p1, p2)
        /* ensure current is p1 after adding p1 and p2 */
        assert(p1 == lst.peek())
        /* ensure current is p2 after calling next() */
        assert(p2 == lst.next())
        /* ensure current is p1 after calling next() */
        assert(p1 == lst.next())
        /* ensure turn count is 2 after every player had a turn */
        assertEquals(2, lst.turns())
    }

    @Test
    fun testIsTheirTurn() {
        val lst = Players()
        val p1 = Player()
        val p2 = Player()
        lst.add(p1, p2)
        /* ensure current is p1 */
        assert(lst.isTheirTurn(p1.id()))
        lst.next()
        /* ensure current is p2 after calling next() */
        assert(lst.isTheirTurn(p2.id()))
    }

    @Test
    fun testLeftAndRight() {
        val lst = Players()
        val p1 = Player()
        val p2 = Player()
        val p3 = Player()
        lst.add(p1, p2, p3)
        assert(lst.left() == p3)
        assert(lst.right() == p2)
        lst.next()
        assert(lst.left() == p1)
        assert(lst.right() == p3)
        lst.next()
        assert(lst.left() == p2)
        assert(lst.right() == p1)
    }

    @Test
    fun testGet() {
        val lst = Players()
        val p1 = Player()
        val p2 = Player()
        val p3 = Player()
        lst.add(p1, p2, p3)
        val ids = lst.get()
        /* ensure ids size is 3 */
        assertEquals(3, ids.size)
        /* ensure indexes in ids matches all player ids */
        assertEquals(p1.id(), ids[0])
        assertEquals(p2.id(), ids[1])
        assertEquals(p3.id(), ids[2])
    }

    @Test
    fun testGetPredicate() {
        val lst = Players()
        val p1 = Player()
        val p2 = Player()
        val p3 = Player()
        lst.add(p1, p2, p3)
        var id1 = p1.id();
        /* get all players that is not equal to id1 */
        val filterLst = lst.get { p -> !p.id().equals(id1) }
        assertEquals(2, filterLst.size)
    }

    @Test
    fun testSetFirstPlayer() {
        val lst = Players()
        val p1 = Player()
        val p2 = Player()
        val p3 = Player()
        lst.add(p1, p2, p3)
        assert(p1 == lst.peek())
        /* update first player p2 */
        lst.setFirstPlayer(p2.id())
        /* ensure first player updates are made correctly */
        assert(p2 == lst.peek())
        assert(p1 == lst.left())
        assert(p3 == lst.right())
        /* update first player to p3 */
        lst.setFirstPlayer(p3.id())
        /* ensure first player updates are made correctly */
        assert(p3 == lst.peek())
        assert(p2 == lst.left())
        assert(p1 == lst.right())
    }
}