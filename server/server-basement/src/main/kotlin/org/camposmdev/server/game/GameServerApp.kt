package org.camposmdev.server.game

import io.vertx.core.Vertx
import io.vertx.core.http.HttpServerOptions
import org.camposmdev.server.game.model.ClientRegistry
import org.camposmdev.server.game.vertx.WSClient

class GameServerApp {
    companion object {
        private const val PORT = 5000
        private const val N_CLIENTS = 4
        @JvmStatic
        fun main(args: Array<String>) {
            val vertx = Vertx.vertx()
            val options = HttpServerOptions()
            options.port = PORT
            val server = vertx.createHttpServer(options)
            server.webSocketHandler {
                if (ClientRegistry.size() > N_CLIENTS) {
                    it.reject()
                } else {
                    val client = WSClient(it)
                    ClientRegistry.add(client)
                }
            }
            server.listen().onSuccess {
                println("Server listening on port $PORT")
            }.onFailure {
                println("Failed to bind server to port $PORT")
            }
        }
    }
}
