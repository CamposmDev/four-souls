package io.github.camposmdev.foursouls.server.basement

import io.github.camposmdev.foursouls.core.api.message.WSPacketFactory
import io.github.camposmdev.foursouls.core.util.logger.Logger
import io.github.camposmdev.foursouls.server.basement.impl.BasementRegistry
import io.github.camposmdev.foursouls.server.basement.impl.BasementServerWSClient
import io.github.camposmdev.foursouls.server.basement.spi.BasementAuth
import io.github.camposmdev.foursouls.server.basement.spi.BasementOpts
import io.vertx.core.Vertx
import io.vertx.core.http.HttpMethod
import io.vertx.core.http.HttpServerOptions
import io.vertx.core.http.HttpServerRequest

object BasementServer {
    const val NAME = "Basement"
    private const val USER_ID_COOKIE = "userId"
    private lateinit var vertx: Vertx
    lateinit var auth: BasementAuth
    private lateinit var log: Logger

    @JvmStatic
    fun main(args: Array<String>) {
        /* parse arguments */
        val opts = BasementOpts.parse(args)
        vertx = Vertx.vertx()
        /* initialize auth module */
        auth = BasementAuth(vertx, opts.momHost, opts.momPort)
        /* initialize log */
        log = Logger(BasementServer::class.java)
        /* initialize server */
        val options = HttpServerOptions()
        options.port = opts.basementPort
        val server = vertx.createHttpServer(options)
        server.requestHandler(::reqHandler)
        server.listen().onSuccess {
            log.info("Server listening on port ${opts.basementPort}")
        }.onFailure {
            log.error(it)
        }
    }

    private fun reqHandler(req: HttpServerRequest) {
        if (req.method() != HttpMethod.GET) {
            req.response().setStatusCode(405).end()
            return
        }
        if (BasementRegistry.isFull()) {
            val payload = WSPacketFactory.err("Basement is Full")
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
        req.toWebSocket().onSuccess { ws -> BasementServerWSClient(ws, userId.value)
        }.onFailure { req.response().setStatusCode(500).send() }
    }

    fun close() {
        vertx.close()
    }

}
