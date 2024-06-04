package io.github.camposmdev.foursouls.server.basement

import io.github.camposmdev.foursouls.server.basement.model.BasementAuth
import io.github.camposmdev.foursouls.server.basement.model.BasementClient
import io.github.camposmdev.foursouls.server.basement.model.BasementRegistry
import io.vertx.core.Vertx
import io.vertx.core.http.HttpServerOptions
import io.vertx.core.http.HttpServerRequest

object BasementServerApp {
    private const val DEFAULT_MOM_HOST_NAME = "localhost"
    private const val DEFAULT_MOM_PORT = 8080
    private const val DEFAULT_BASEMENT_PORT = 7070
    private const val USER_ID_COOKIE = "userId"
    private var auth: BasementAuth? = null

    @JvmStatic
    fun main(args: Array<String>) {
        val vertx = Vertx.vertx()
        auth = BasementAuth(vertx, DEFAULT_MOM_HOST_NAME, DEFAULT_MOM_PORT)
        val options = HttpServerOptions()
        options.port = DEFAULT_BASEMENT_PORT
        val server = vertx.createHttpServer(options)
        server.requestHandler(BasementServerApp::handleRequest)
        server.listen().onSuccess {
            println("Server listening on port $DEFAULT_BASEMENT_PORT")
        }.onFailure {
            println("Failed to bind server to port $DEFAULT_BASEMENT_PORT")
        }
    }

    private fun handleRequest(req: HttpServerRequest) {
        /* fetch userId cookie */
        val userId = req.getCookie(USER_ID_COOKIE)
        /* verify userId */
        auth!!.verifyUserId(userId.value).onSuccess { username ->
            req.toWebSocket().onSuccess {
                /* if the lobby is full or userId is null, then close */
                if (BasementRegistry.isFull() || userId == null) {
                    it.close()
                } else {
                    /* otherwise, add the client to the registry */
                    val client = BasementClient(it, userId.value, username)
                    if (BasementRegistry.isEmpty()) {
                        BasementRegistry.hostId = client.id()
                        client.state.host = true
                    }
                    BasementRegistry.add(client)
                }
            }
        }.onFailure {
            req.response().setStatusCode(400).send("Invalid")
        }
    }
}
