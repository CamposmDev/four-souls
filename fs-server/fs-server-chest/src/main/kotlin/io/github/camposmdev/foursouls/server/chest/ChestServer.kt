package io.github.camposmdev.foursouls.server.chest

import io.github.camposmdev.foursouls.core.api.message.WSPacketFactory
import io.github.camposmdev.foursouls.server.chest.impl.ChestRegistry
import io.github.camposmdev.foursouls.server.chest.impl.ChestIServerWSManager
import io.github.camposmdev.foursouls.server.chest.spi.ChestOpts
import io.vertx.core.Vertx
import io.vertx.core.http.HttpMethod
import io.vertx.core.http.HttpServerOptions
import io.vertx.core.http.HttpServerRequest

object ChestServer {
    private lateinit var vertx: Vertx

    @JvmStatic
    fun main(args: Array<String>) {
        val opts = ChestOpts.parse(args)
        val vertx = Vertx.vertx()
        val options = HttpServerOptions()
        options.port = opts.chestPort
        val server = vertx.createHttpServer(options)
        server.requestHandler(::reqHandler)
        server.webSocketHandler {
            if (ChestRegistry.isFull()) {
                it.reject()
            } else {
                val client = ChestIServerWSManager(it)
                ChestRegistry.add(client)
            }
        }
        server.listen().onSuccess {
            println("Chest Server listening on port ${opts.chestPort}")
        }.onFailure {
            println("Failed to bind Chest Server to port ${opts.chestPort}")
        }
    }

    private fun reqHandler(req: HttpServerRequest) {
        if (req.method() != HttpMethod.GET) {
            req.response().setStatusCode(405).end()
            return
        }
        if (ChestRegistry.isFull()) {
            val payload = WSPacketFactory.err("Chest is Full")
            req.response().setStatusCode(503).end(payload)
            return
        }
        /* upgrade to web socket */
        req.toWebSocket().onSuccess { ws -> ChestIServerWSManager(ws)
        }.onFailure {req.response().setStatusCode(500).send()}
    }

    fun close() {
        vertx.close()
    }
}
