package io.github.camposmdev.foursouls.core.api.message.payload

import io.github.camposmdev.foursouls.core.api.basement.BasementUser
import io.vertx.core.json.JsonObject
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
@DisplayName("Basement Payload Tests")
class BasementPayloadTest {
    @Test
    @DisplayName("Map: BasementGreeting <-> JSON")
    fun testBasementGreetingJSON() {
        val testUserId = "343"
        val testHost = true
        val testUsername = "Guest"
        val testUsers = arrayListOf(BasementUser("id", testUserId))
        val g1 = BasementGreeting(testHost, testUsername, testUsers)
        /* map g1 object to JSON */
        val payload = JsonObject.mapFrom(g1)
        /* map it back to a g2 object */
        val g2 = payload.mapTo(BasementGreeting::class.java)
        assertEquals(g1.host, g2.host)
        assertEquals(g1.users.size, g2.users.size)
        assertEquals(g1.message, g2.message)
    }
}