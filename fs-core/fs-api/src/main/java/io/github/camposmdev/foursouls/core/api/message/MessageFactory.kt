package io.github.camposmdev.foursouls.core.api.message

import io.github.camposmdev.foursouls.core.api.basement.BasementUser
import io.github.camposmdev.foursouls.core.api.message.payload.BasementChat
import io.github.camposmdev.foursouls.core.api.message.payload.PayloadFactory
import io.vertx.core.json.JsonObject

object MessageFactory {
    const val MTYPE_FIELD = "mtype"
    const val PAYLOAD_FIELD = "payload"

    fun err(message: String): String {
        val payload = PayloadFactory.message(message)
        return JsonObject.of(MTYPE_FIELD, MType.ERROR, PAYLOAD_FIELD, payload).toString()
    }

    fun basement(): BasementFactory {
        return BasementFactory
    }

    fun chest(): ChestFactory {
        return ChestFactory
    }

    object BasementFactory {
        fun err(message: String): String {
            val payload = PayloadFactory.message(message)
            return JsonObject.of(MTYPE_FIELD, MType.BASEMENT_ERROR, PAYLOAD_FIELD, payload).toString()
        }

        fun chat(chat: BasementChat): String {
            val payload = JsonObject.mapFrom(chat)
            return JsonObject.of(MTYPE_FIELD, MType.BASEMENT_CHAT, PAYLOAD_FIELD, payload).toString()
        }

        fun greeting(host: Boolean, username: String?, users: List<BasementUser>): String {
            val payload = PayloadFactory.basement().greeting(host, username, users)
            return JsonObject.of(MTYPE_FIELD, MType.BASEMENT_GREETING, PAYLOAD_FIELD, payload).toString()
        }

        fun done(chestId: String): String {
            val payload = PayloadFactory.basement().done(chestId)
            return JsonObject.of(MTYPE_FIELD, MType.BASEMENT_DONE, PAYLOAD_FIELD, payload).toString()
        }

        fun leave(): String {
            val payload = PayloadFactory.basement().leave()
            return JsonObject.of(MTYPE_FIELD, MType.BASEMENT_LEAVE, PAYLOAD_FIELD, payload).toString()
        }
    }

    object ChestFactory {

    }
}