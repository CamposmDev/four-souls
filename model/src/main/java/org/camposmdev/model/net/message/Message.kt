package org.camposmdev.model.net.message

import io.vertx.core.json.JsonObject
import org.camposmdev.model.net.message.payload.BasementChat
import org.camposmdev.model.net.message.payload.Payload

object Message {
    private const val MTYPE_FIELD = "mtype"
    private const val PAYLOAD_FIELD = "payload"

    fun err(message: String): String {
        val payload = Payload.message(message)
        return JsonObject.of(MTYPE_FIELD, MType.ERROR, PAYLOAD_FIELD, payload).toString()
    }

    fun basementChat(chat: BasementChat): String {
        val payload = JsonObject.mapFrom(chat)
        return JsonObject.of(MTYPE_FIELD, MType.BASEMENT_CHAT, PAYLOAD_FIELD, payload).toString()
    }

    fun greeting(): String {
        val payload = Payload.message("Joined Lobby")
        return JsonObject.of(MTYPE_FIELD, MType.BASEMENT_GREETING, PAYLOAD_FIELD, payload).toString()
    }
}