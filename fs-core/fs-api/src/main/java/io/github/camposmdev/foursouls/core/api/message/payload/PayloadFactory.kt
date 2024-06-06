package io.github.camposmdev.foursouls.core.api.message.payload

import io.github.camposmdev.foursouls.core.api.basement.BasementUser
import io.vertx.core.json.JsonObject

interface Payload

/**
 * Builds message payloads
 */
object PayloadFactory {
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

    fun basementGreeting(host: Boolean, users: List<BasementUser>): BasementGreeting {
        return BasementGreeting(host, users)
    }

    fun basementChat(username: String, message: String): BasementChat {
        return BasementChat(username, message)
    }

    fun basementFinished(chestId: String): BasementDone {
        return BasementDone(chestId)
    }
}