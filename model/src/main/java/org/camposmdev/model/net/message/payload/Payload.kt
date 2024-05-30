package org.camposmdev.model.net.message.payload

import io.vertx.core.json.JsonObject

object Payload {
    fun message(message: String): JsonObject {
        return JsonObject.of("message", message)
    }

    fun local_chat(userId: String, message: String): JsonObject {
        return JsonObject.of("userId", userId, "message", message)
    }
}