package io.github.camposmdev.foursouls.server.chest

import io.github.camposmdev.foursouls.core.api.message.PacketFactory
import io.github.camposmdev.foursouls.core.util.logger.Logger
import io.github.camposmdev.foursouls.server.chest.impl.ChestRegistry
import io.github.camposmdev.foursouls.server.chest.impl.ChestServerWSClient
import io.github.camposmdev.foursouls.server.chest.spi.ChestAuth
import io.github.camposmdev.foursouls.server.chest.spi.ChestOpts
import io.vertx.core.Vertx
import io.vertx.core.http.HttpMethod
import io.vertx.core.http.HttpServerOptions
import io.vertx.core.http.HttpServerRequest

object ChestServer {
    const val NAME = "Chest"
    private const val USER_ID_COOKIE = "userId"
    private lateinit var vertx: Vertx
    private lateinit var log: Logger
    lateinit var Auth: ChestAuth

    @JvmStatic
    fun main(args: Array<String>) {
        /* parse arguments */
        val opts = ChestOpts.parse(args)
        val vertx = Vertx.vertx()
        /* initialize auth module */
        Auth = ChestAuth(vertx, opts.momHost, opts.momPort)
        /* initialize server */
        val options = HttpServerOptions()
        options.port = opts.chestPort
        val server = vertx.createHttpServer(options)
        server.requestHandler(::reqHandler)
        server.listen().onSuccess {
            log.info("Server listening on port ${opts.chestPort}")
        }.onFailure {
            log.error(it)
        }
    }

    private fun reqHandler(req: HttpServerRequest) {
        if (req.method() != HttpMethod.GET) {
            req.response().setStatusCode(405).end()
            return
        }
        if (ChestRegistry.isFull()) {
            val payload = PacketFactory.err("Chest is Full")
            req.response().setStatusCode(503).end(payload)
            return
        }
        /* extract the userId cookie, reject ws request if null */
        val userId = req.getCookie(USER_ID_COOKIE)
        if (userId == null) {
            req.response().setStatusCode(400).end("Missing '$USER_ID_COOKIE' cookie")
            return
        }
        /* upgrade to web socket */
        req.toWebSocket().onSuccess { ws -> ChestServerWSClient(ws, userId.value)
        }.onFailure {req.response().setStatusCode(500).send()}
    }

    fun close() {
        vertx.close()
    }
}
