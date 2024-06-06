package io.github.camposmdev.foursouls.core.api.message.payload

import io.github.camposmdev.foursouls.core.api.basement.BasementUser
import io.github.camposmdev.foursouls.core.api.message.MessageFactory
import io.vertx.core.json.JsonObject

interface Payload

/**
 * Builds message payloads
 */
object PayloadFactory {
    private const val MSG_FIELD = "message"

    /**
     * Returns JsonObject value in 'payload' field
     */
    fun parse(obj: JsonObject): JsonObject {
        val field = MessageFactory.PAYLOAD_FIELD
        if (!obj.containsKey(field))
            throw IllegalArgumentException("Missing '$field' field")
        return obj.getJsonObject(field)
    }

    fun message(message: String): JsonObject {
        return JsonObject.of(MSG_FIELD, message)
    }

    fun basement(): BasementPayloadFactory {
        return BasementPayloadFactory
    }

    object BasementPayloadFactory {
        fun greeting(host: Boolean, username: String?, users: List<BasementUser>): BasementGreeting {
            return BasementGreeting(host, username, users)
        }

        fun chat(username: String?, message: String): BasementChat {
            return BasementChat(username, message)
        }

        fun done(chestId: String): BasementDone {
            return BasementDone(chestId)
        }

        fun leave(): BasementLeave {
            return BasementLeave()
        }

        fun leave(message: String): BasementLeave {
            return BasementLeave(message)
        }
    }
}