package io.github.camposmdev.foursouls.core.api.mom

import io.github.camposmdev.foursouls.core.atlas.MasterCardAtlas
import io.vertx.core.Vertx
import io.vertx.junit5.VertxExtension
import io.vertx.junit5.VertxTestContext
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNotNull

@ExtendWith(VertxExtension::class)
@DisplayName("Mom API Tests")
class MomAPITest {
    companion object {
        private const val HOST = "localhost"
        private const val PORT = 8000
    }

    @Test
    @DisplayName("Get All Cards: Correct Result")
    fun testGetAllCards(v: Vertx, ctx: VertxTestContext) {
        val api = MomAPI(v, HOST, PORT)
        api.getAllDecks().onSuccess { res ->
            assertEquals(res.statusCode(), 200)
            val job = res.bodyAsJsonArray()
            assertNotNull(job)
            ctx.completeNow()
        }.onFailure { ctx.failNow(it) }
    }

    @Test
    @DisplayName("Get All Cards (Pretty): Correct Result")
    fun testGetAllCardsPretty(v: Vertx, ctx: VertxTestContext) {
        val api = MomAPI(v, HOST, PORT)
        api.getAllDecks(true).onSuccess { res ->
            assertEquals(res.statusCode(), 200)
            val job = res.bodyAsJsonObject()
            assertNotNull(job)
            assertFalse(job.fieldNames().isEmpty())
            ctx.completeNow()
        }.onFailure { ctx.failNow(it) }
    }

    @Test
    @DisplayName("Get All Cards (Pretty): Parse to POJO")
    fun testGetAllCardsPrettyToPOJO(v: Vertx, ctx: VertxTestContext) {
        val api = MomAPI(v, HOST, PORT)
        api.getAllDecks(true).onSuccess { res ->
            assertEquals(res.statusCode(), 200)
            val buffer = res.bodyAsBuffer()
            val atlas = MasterCardAtlas.deserialize(buffer.bytes)
            assertNotNull(atlas)
            ctx.completeNow()
        }.onFailure { ctx.failNow(it) }
    }
}