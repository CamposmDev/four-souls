package org.camposmdev.server.basement

import io.vertx.core.Vertx
import io.vertx.core.http.HttpServerOptions
import org.camposmdev.server.basement.model.LobbyClient
import org.camposmdev.server.basement.model.LobbyRegistry

class BasementServerApp {
    companion object {
        private const val PORT = 7000
        private const val N_CLIENTS = 4

        @JvmStatic
        fun main(args: Array<String>) {
            val vertx = Vertx.vertx()
            val options = HttpServerOptions()
            options.port = PORT
            val server = vertx.createHttpServer(options)
            server.webSocketHandler {
                if (LobbyRegistry.isEmpty()) {
                    /* mark client as host */
                    val client = LobbyClient(it, true)
                    LobbyRegistry.add(client)
                    LobbyRegistry.hostId = client.id()
                    println("Updated Host: ${LobbyRegistry.hostId}")
                } else if (LobbyRegistry.size() > N_CLIENTS) {
                    it.reject()
                } else {
                    val client = LobbyClient(it)
                    LobbyRegistry.add(client)
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