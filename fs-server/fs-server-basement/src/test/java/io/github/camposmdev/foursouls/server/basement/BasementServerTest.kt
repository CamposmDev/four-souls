package io.github.camposmdev.foursouls.server.basement

import io.github.camposmdev.foursouls.core.api.message.BasementMType
import io.github.camposmdev.foursouls.core.api.message.payload.BasementChat
import io.github.camposmdev.foursouls.core.api.message.payload.BasementGreeting
import io.github.camposmdev.foursouls.core.context.FourSoulsContext
import io.vertx.core.Vertx
import io.vertx.core.impl.future.CompositeFutureImpl
import io.vertx.junit5.VertxExtension
import io.vertx.junit5.VertxTestContext
import org.junit.jupiter.api.*
import org.junit.jupiter.api.extension.ExtendWith
import kotlin.test.assertEquals

@ExtendWith(VertxExtension::class)
@DisplayName("Basement Server Test")
class BasementServerTest {
    companion object {
        private const val TEST_USERNAME0 = "Guest"
        private const val TEST_PASSWORD0 = "supersecret"
        private const val TEST_BASEMENT_HOST = "localhost"
        private const val TEST_BASEMENT_PORT = 7000
        private const val TEST_MOM_HOST = "localhost"
        private const val TEST_MOM_PORT = 8000

        @JvmStatic
        @BeforeAll
        fun setup(ctx: VertxTestContext) {
            BasementServer.main(arrayOf(
                "--port", "$TEST_BASEMENT_PORT",
                "--mom-host", TEST_MOM_HOST,
                "--mom-port", "$TEST_MOM_PORT"
            ))
            ctx.completeNow()
        }

        @JvmStatic
        @AfterAll
        fun done(ctx: VertxTestContext) {
            BasementServer.close()
            ctx.completeNow()
        }
    }

    @Test
    @Order(1)
    @DisplayName("Join (Host): Correct Result")
    fun testJoinAsHost(v: Vertx, ctx: VertxTestContext) {
        val fs = FourSoulsContext(v, TEST_MOM_HOST, TEST_MOM_PORT)
        /* login user */
        fs.mom().store().loginUser(TEST_USERNAME0, TEST_PASSWORD0).onSuccess {
            val userId = fs.mom().state().userId as String
            fs.state().basement.store().connect(TEST_BASEMENT_HOST, TEST_BASEMENT_PORT, userId).onComplete {
                if (it.succeeded())
                    ctx.checkpoint()
                else
                    ctx.failNow(it.cause())
            }.onFailure { ctx.failNow(it.cause) }
        }.onFailure { ctx.failNow(it.cause) }

        fs.basement().subscribeTo(BasementMType.GREETING).handler {
            val payload = it.body() as BasementGreeting
            assertEquals("Joined Basement", payload.message)
            assertEquals(true, payload.host)
            assertEquals(1, payload.users.size)
            assertEquals(TEST_USERNAME0, payload.username)
            ctx.completeNow()
        }
    }

    @Test
    @Order(2)
    @DisplayName("Chat: Correct Result")
    fun testChat(v: Vertx, ctx: VertxTestContext) {
        val testMessage = "hello world"
        val fs = FourSoulsContext(v, TEST_MOM_HOST, TEST_MOM_PORT)
        /* login user */
        fs.mom().store().loginUser(TEST_USERNAME0, TEST_PASSWORD0).onSuccess {
            val userId = fs.mom().state().userId as String
            fs.basement().store().connect(TEST_BASEMENT_HOST, TEST_BASEMENT_PORT, userId).onComplete {
                if (it.succeeded())
                    ctx.checkpoint()
                else
                    ctx.failNow(it.cause())
            }.onFailure { ctx.failNow(it.cause) }
        }.onFailure { ctx.failNow(it.cause) }

        val sub1 = fs.basement().store().subscribe()
        sub1.handler {
            /* ensure the state updated */
            val state = it.body()
            assertEquals(true, state.connected)
            assertEquals(true, state.host)
            assertEquals(TEST_USERNAME0, state.username)
            assertEquals(1, state.users.size)
            assertEquals(true, state.subs.isNotEmpty())
            fs.basement().store().chat(testMessage)
            ctx.checkpoint(1)
        }

        val sub2 = fs.basement().subscribeTo(BasementMType.CHAT)
        sub2.handler {
            /* ensure the state updated */
            val payload = it.body() as BasementChat
            /* ensure the message came from same user */
            assertEquals(TEST_USERNAME0, payload.username)
            assertEquals(testMessage, payload.message)
            ctx.checkpoint(2)
            /* unregister subscriptions */
            val future = CompositeFutureImpl.all(sub1.unregister(), sub2.unregister())
            future.onComplete { ar ->
                if (ar.succeeded()) {
                    ctx.completeNow()
                }
            }
        }
    }
}