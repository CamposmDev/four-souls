package io.github.camposmdev.foursouls.core.api.message.payload

import io.github.camposmdev.foursouls.core.api.basement.BasementUser
import io.github.camposmdev.foursouls.core.api.chest.ChestUser
import io.vertx.core.json.JsonObject

interface Payload

/**
 * Builds payloads for WSPackets
 */
object PayloadFactory {
    private const val MSG_FIELD = "message"

    fun message(message: String): JsonObject {
        return JsonObject.of(MSG_FIELD, message)
    }

    fun basement(): BasementPayloadFactory {
        return BasementPayloadFactory
    }

    fun chest(): ChestPayloadFactory {
        return ChestPayloadFactory
    }
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

object ChestPayloadFactory {
    fun greeting(host: Boolean, username: String?, users: List<ChestUser>): ChestGreeting {
        return ChestGreeting(host, username, users)
    }
}