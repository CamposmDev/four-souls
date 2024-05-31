package org.camposmdev.model.net.message.payload

import io.vertx.core.json.JsonObject

object Payload {
    private const val USER_ID_FIELD = "userId"
    private const val MSG_FIELD = "message"
    fun message(message: String): JsonObject {
        return JsonObject.of(MSG_FIELD, message)
    }

    fun localChat(userId: String, message: String): JsonObject {
        return JsonObject.of(USER_ID_FIELD, userId, MSG_FIELD, message)
    }
}