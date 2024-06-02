package org.camposmdev.model.net.message.payload

import io.vertx.core.json.JsonObject

object Payload {
    private const val USERNAME_FIELD = "username"
    private const val MSG_FIELD = "message"

    fun message(message: String): JsonObject {
        return JsonObject.of(MSG_FIELD, message)
    }

    fun localChat(username: String, message: String): JsonObject {
        return JsonObject.of(USERNAME_FIELD, username, MSG_FIELD, message)
    }
}