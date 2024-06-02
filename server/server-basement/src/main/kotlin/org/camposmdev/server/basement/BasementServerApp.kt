package org.camposmdev.server.basement

import io.vertx.core.Vertx
import io.vertx.core.http.HttpServerOptions
import io.vertx.core.http.HttpServerRequest
import org.camposmdev.server.basement.model.LobbyClient
import org.camposmdev.server.basement.model.LobbyRegistry

class BasementServerApp {
    companion object {
        private const val PORT = 7000

        @JvmStatic
        fun main(args: Array<String>) {
            val vertx = Vertx.vertx()
            val options = HttpServerOptions()
            options.port = PORT
            val server = vertx.createHttpServer(options)
            server.requestHandler(BasementServerApp::handleRequest)
            server.listen().onSuccess {
                println("Server listening on port $PORT")
            }.onFailure {
                println("Failed to bind server to port $PORT")
            }
        }

        private fun handleRequest(req: HttpServerRequest) {
            /* fetch userId cookie */
            val userId = req.getCookie("userId")
            req.toWebSocket().onSuccess {
                /* if the lobby is full or userId is null, then close */
                if (LobbyRegistry.isFull() || userId == null) {
                    it.close()
                } else {
                    /* otherwise, add the client to the registry */
                    val client = LobbyClient(it, userId.value)
                    if (LobbyRegistry.isEmpty()) {
                        LobbyRegistry.hostId = client.id()
                    }
                    LobbyRegistry.add(client)
                }
            }
        }
    }
}
