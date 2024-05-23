package org.camposmdev.server.mom.vertx.router

import io.vertx.core.Vertx
import io.vertx.ext.web.Router
import org.camposmdev.server.mom.vertx.controller.VertxUserController

class UserRouter(v: Vertx) {
    private val controller = VertxUserController()
    private val router: Router = Router.router(v)

    init {
        router.post("/user/login").handler(controller::loginUser)
        router.post("/user/logout").handler(controller::logoutUser)
    }

    companion object {
        fun create(v: Vertx): Router {
            return UserRouter(v).router
        }
    }
}
