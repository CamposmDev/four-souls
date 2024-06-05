package io.github.camposmdev.foursouls.server.basement

import io.github.camposmdev.foursouls.model.context.FourSoulsContext
import io.vertx.core.Vertx
import io.vertx.junit5.VertxExtension
import io.vertx.junit5.VertxTestContext
import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

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
        fun setup(v: Vertx, ctx: VertxTestContext) {
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
    @DisplayName("Test Join as Host: Correct Result")
    fun testJoinAsHost(v: Vertx, ctx: VertxTestContext) {
        val fs = FourSoulsContext(v, TEST_MOM_HOST, TEST_MOM_PORT)
        /* login user */
        fs.mom().store().loginUser(TEST_USERNAME0, TEST_PASSWORD0).onSuccess {
            val userId = fs.mom().state().userId as String
            fs.state().basement.store().connect(TEST_BASEMENT_HOST, TEST_BASEMENT_PORT, userId).onSuccess {
                /* successfully joined basement */
                ctx.completeNow()
            }.onFailure { ctx.failNow(it.cause) }
        }.onFailure { ctx.failNow(it.cause) }
    }
}