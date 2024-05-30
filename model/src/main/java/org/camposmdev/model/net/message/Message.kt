package org.camposmdev.model.net.message

import io.vertx.core.json.JsonObject
import org.camposmdev.model.net.message.payload.Payload

object Message {
    fun error(message: String): String {
        val payload = Payload.message(message)
        return JsonObject.of("mtype", MType.ERROR, "payload", payload).toString()
    }

    fun localChat(userId: String, message: String): String {
        val payload = Payload.local_chat(userId, message)
        return JsonObject.of("mtype", MType.LOCAL_CHAT,"payload", payload).toString()
    }
}