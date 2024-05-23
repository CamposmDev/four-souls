package org.camposmdev.server.mom

import io.vertx.core.Vertx
import io.vertx.core.http.HttpServerOptions
import io.vertx.core.impl.logging.LoggerFactory
import io.vertx.ext.web.Router
import io.vertx.ext.web.handler.BodyHandler
import org.camposmdev.server.mom.vertx.router.UserRouter

object MomServerApp {
    private const val PORT = 4000
    @JvmStatic
    fun main(args: Array<String>) {
        val log = LoggerFactory.getLogger(MomServerApp::class.java)
        val v = Vertx.vertx()
        val options = HttpServerOptions()
        options.port = PORT
        val server = v.createHttpServer(options)
        val apiRouter = Router.router(v)
        apiRouter.route().handler(BodyHandler.create())
        apiRouter.route("/api/*").subRouter(UserRouter.create(v))
        server.requestHandler(apiRouter).listen().onSuccess {
            log.info("Server listening on port $PORT")
        }.onFailure {
            log.error("Failed to bind server to port $PORT")
        }
    }
}
