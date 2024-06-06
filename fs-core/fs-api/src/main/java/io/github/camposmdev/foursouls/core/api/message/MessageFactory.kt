package io.github.camposmdev.foursouls.core.api.message

import io.github.camposmdev.foursouls.core.api.basement.BasementUser
import io.github.camposmdev.foursouls.core.api.message.payload.BasementChat
import io.github.camposmdev.foursouls.core.api.message.payload.PayloadFactory
import io.vertx.core.json.JsonObject

object MessageFactory {
    private const val MTYPE_FIELD = "mtype"
    private const val PAYLOAD_FIELD = "payload"

    fun err(message: String): String {
        val payload = PayloadFactory.message(message)
        return JsonObject.of(MTYPE_FIELD, MType.ERROR, PAYLOAD_FIELD, payload).toString()
    }

    fun basementChat(chat: BasementChat): String {
        val payload = JsonObject.mapFrom(chat)
        return JsonObject.of(MTYPE_FIELD, MType.BASEMENT_CHAT, PAYLOAD_FIELD, payload).toString()
    }

    fun basementGreeting(host: Boolean, users: List<BasementUser>): String {
        val payload = PayloadFactory.basementGreeting(host, users)
        return JsonObject.of(MTYPE_FIELD, MType.BASEMENT_GREETING, PAYLOAD_FIELD, payload).toString()
    }
}