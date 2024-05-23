package org.camposmdev.server.mom.vertx.controller

import io.vertx.core.json.DecodeException
import io.vertx.core.json.JsonObject
import io.vertx.ext.web.RoutingContext
import org.camposmdev.model.request.LoginBodyRequest

class VertxUserController {
    fun loginUser(c: RoutingContext) {
        if (c.body().isEmpty) {    /* missing body */
            val body = JsonObject.of("message", "Missing body.")
            c.response().setStatusCode(400).send(body.toString())
            return
        }
        try {
            var body = c.body().asPojo(LoginBodyRequest::class.java)
            JsonObject.mapFrom(body)
            /* ensure username field is valid */
            if (body.username.isNullOrBlank()) {
                c.response().setStatusCode(400).send(JsonObject.of(
                        "message", "Invalid 'username' field"
                    ).toString())
                return
            }
            /* ensure password field is valid */
            if (body.password.isNullOrBlank()) {
                c.response().setStatusCode(400).send(JsonObject.of(
                        "message", "Invalid 'password' field"
                    ).toString())
                return
            }
            /* ensure password is PWD_LEN characters long */
            if (body.password.length < PWD_LEN) {
                c.response().setStatusCode(400).send(JsonObject.of(
                        "message", "Password must be at least $PWD_LEN characters"
                    ).toString())
                return
            }
            /* TODO - ensure username is not taken */
            /* TODO - if existing user, ensure passwords match */
            /* TODO - inject token as cookie if login OK */
            c.response().setStatusCode(501).send()
        } catch (e: DecodeException) {
            c.response().setStatusCode(400).send()
        }
    }

    fun logoutUser(c: RoutingContext) {
        if (c.body().isEmpty) {
            val body = JsonObject.of("message", "Missing body.")
            c.response().setStatusCode(400).send(body.toString());
        }
        /* TODO - implement feature to logout user */
        c.response().setStatusCode(501).send();
    }
    companion object {
        private const val PWD_LEN = 7
    }
}
