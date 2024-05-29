package org.camposmdev.model.net.message.payload

import io.vertx.core.json.JsonObject

object Payload {
    fun message(message: String): JsonObject {
        return JsonObject.of("message", message)
    }
}