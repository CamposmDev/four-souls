package io.github.camposmdev.foursouls.model.net.message.payload

import io.vertx.core.json.JsonObject

interface Payload

/**
 * Builds message payloads
 */
object PayloadBuilder {
    private const val PAYLOAD_FIELD = "payload"
    private const val MSG_FIELD = "message"

    /**
     * Returns JsonObject value in 'payload' field
     */
    fun parse(obj: JsonObject): JsonObject {
        if (!obj.containsKey(PAYLOAD_FIELD))
            throw IllegalArgumentException("Missing '$PAYLOAD_FIELD' field")
        return obj.getJsonObject(PAYLOAD_FIELD)
    }

    fun message(message: String): JsonObject {
        return JsonObject.of(MSG_FIELD, message)
    }

    fun basementChat(username: String, message: String): BasementChat {
        return BasementChat(username, message)
    }

    fun basementFinished(chestId: String): BasementDone {
        return BasementDone(chestId)
    }
}