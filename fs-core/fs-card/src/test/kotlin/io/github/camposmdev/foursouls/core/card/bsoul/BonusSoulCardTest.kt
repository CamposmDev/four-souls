package io.github.camposmdev.foursouls.core.card.bsoul

import io.github.camposmdev.foursouls.core.card.attribute.CardSet
import io.github.camposmdev.foursouls.core.card.attribute.CardType
import io.vertx.core.json.JsonObject
import kotlin.test.Test
import kotlin.test.assertEquals

class BonusSoulCardTest {
    @Test
    fun testJSONEncode() {
        val testId = "card-id"
        val testCardSet = CardSet.RETRO
        val testCardType = CardType.BSOUL
        val testLoot: Byte = 10
        val testMoney: Byte = 25
        val testGuppyItems: Byte = 3
        val testEnvy = true
        val testSloth = false
        val testStrawberry = true
        val testCard = BonusSoulCard().apply {
            id = testId
            cardSet = testCardSet
            cardType = testCardType
            loot = testLoot
            money = testMoney
            guppyItems = testGuppyItems
            isEnvy = testEnvy
            isSloth = testSloth
            isStrawberry = testStrawberry
        }
        val job = JsonObject.mapFrom(testCard)
        assertEquals(testId, job.getString("id"))
        assertEquals(testLoot, job.getInteger("loot").toByte())
        assertEquals(testMoney, job.getInteger("money").toByte())
        assertEquals(testEnvy, job.getBoolean("envy"))
    }

    @Test
    fun testJSONDecode() {
        val testId = "card-id"
        val testCardSet = CardSet.RETRO
        val testCardType = CardType.BSOUL
        val testLoot: Byte = 10
        val testMoney: Byte = 25
        val testGuppyItems: Byte = 3
        val testEnvy = true
        val testSloth = false
        val testStrawberry = true
        val testCard = BonusSoulCard().apply {
            id = testId
            cardSet = testCardSet
            cardType = testCardType
            loot = testLoot
            money = testMoney
            guppyItems = testGuppyItems
            isEnvy = testEnvy
            isSloth = testSloth
            isStrawberry = testStrawberry
        }
        val job = JsonObject.mapFrom(testCard)
        val parsedTestCard = job.mapTo(BonusSoulCard::class.java)
        assertEquals(testMoney, parsedTestCard.money)
        assertEquals(testLoot, parsedTestCard.loot)
        assertEquals(testEnvy, parsedTestCard.isEnvy)
        assertEquals(testId, parsedTestCard.id)
        val job1 = JsonObject.mapFrom(parsedTestCard)
        assertEquals(job, job1)
    }
}