package io.github.camposmdev.server.basement

import io.github.camposmdev.server.basement.model.BasementClient
import io.github.camposmdev.server.basement.model.BasementRegistry
import io.vertx.core.Vertx
import io.vertx.core.http.HttpServerOptions
import io.vertx.core.http.HttpServerRequest

class BasementServerApp {
    companion object {
        private const val PORT = 7070

        @JvmStatic
        fun main(args: Array<String>) {
            val vertx = Vertx.vertx()
            val options = HttpServerOptions()
            options.port = PORT
            val server = vertx.createHttpServer(options)
            server.requestHandler(Companion::handleRequest)
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
                if (BasementRegistry.isFull() || userId == null) {
                    it.close()
                } else {
                    /* otherwise, add the client to the registry */
                    val client = BasementClient(it, userId.value)
                    if (BasementRegistry.isEmpty()) {
                        BasementRegistry.hostId = client.id()
                    }
                    BasementRegistry.add(client)
                }
            }
        }
    }
}
