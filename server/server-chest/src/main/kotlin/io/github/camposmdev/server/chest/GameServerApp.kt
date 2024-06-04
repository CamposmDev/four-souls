package io.github.camposmdev.server.chest

import io.github.camposmdev.server.chest.model.ChestRegistry
import io.github.camposmdev.server.chest.model.GameClient
import io.vertx.core.Vertx
import io.vertx.core.http.HttpServerOptions

class GameServerApp {
    companion object {
        private const val PORT = 6060
        private const val N_CLIENTS = 4
        @JvmStatic
        fun main(args: Array<String>) {
            val vertx = Vertx.vertx()
            val options = HttpServerOptions()
            options.port = PORT
            val server = vertx.createHttpServer(options)
            server.webSocketHandler {
                if (ChestRegistry.size() > N_CLIENTS) {
                    it.reject()
                } else {
                    val client = GameClient(it)
                    ChestRegistry.add(client)
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
