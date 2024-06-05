package io.github.camposmdev.foursouls.server.basement

import io.github.camposmdev.foursouls.server.basement.model.BasementAuth
import io.github.camposmdev.foursouls.server.basement.model.BasementClientWS
import io.github.camposmdev.foursouls.server.basement.model.BasementOpts
import io.github.camposmdev.foursouls.server.basement.model.BasementRegistry
import io.vertx.core.Vertx
import io.vertx.core.http.HttpMethod
import io.vertx.core.http.HttpServerOptions
import io.vertx.core.http.HttpServerRequest

object BasementServer {
    private const val USER_ID_COOKIE = "userId"
    private lateinit var vertx: Vertx
    lateinit var auth: BasementAuth

    @JvmStatic
    fun main(args: Array<String>) {
        /* parse arguments */
        val opts = BasementOpts.parse(args);
        vertx = Vertx.vertx()
        /* initialize auth module */
        auth = BasementAuth(vertx, opts.momHost, opts.momPort)
        /* initialize server */
        val options = HttpServerOptions()
        options.port = opts.basementPort
        val server = vertx.createHttpServer(options)
        server.requestHandler(BasementServer::handleRequest)
        server.listen().onSuccess {
            println("Basement Server listening on port ${opts.basementPort}")
        }.onFailure {
            println("Failed to bind Basement Server to port ${opts.basementPort}")
        }
    }

    fun close() {
        vertx.close()
    }

    private fun handleRequest(req: HttpServerRequest) {
        /* fetch userId cookie */
        if (req.method() != HttpMethod.GET) {
            req.response().setStatusCode(405).end()
            return
        }
        /* extract the userId cookie, reject ws request if null */
        val userId = req.getCookie(USER_ID_COOKIE)
        if (userId == null) {
            req.response().setStatusCode(400).end("Missing '$USER_ID_COOKIE' cookie")
            return
        }
        /* upgrade to web socket */
        req.toWebSocket().onSuccess {
            /* if the lobby is full or userId is null, then close */
            if (BasementRegistry.isFull()) {
                it.close(503, "Basement is Full")
            } else { /* register the client */
                BasementClientWS(it, userId.value)
            }
        }.onFailure { req.response().setStatusCode(500).send() }
    }
}
