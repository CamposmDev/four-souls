package io.github.camposmdev.foursouls.core.api.message

import io.github.camposmdev.foursouls.core.api.message.payload.Payload
import io.vertx.core.json.Json
import io.vertx.core.json.JsonObject

data class WSPacket(
    val mtype: String,
    val payload: JsonObject
) {
    fun encode(): String {
        return Json.encode(this)
    }
    companion object {
        private const val MTYPE_FIELD = "mtype"
        private const val PAYLOAD_FIELD = "payload"
        @JvmStatic
        fun decode(text: String): WSPacket {
            val job = JsonObject(text)
            val mtype = job.getString(MTYPE_FIELD)
            val payload = job.getJsonObject(PAYLOAD_FIELD)
            return WSPacket(mtype, payload)
        }

        @JvmStatic
        fun encode(mtype: String, payload: JsonObject): String {
            return WSPacket(mtype, payload).encode()
        }

        @JvmStatic
        fun encode(mtype: Enum<*>, payload: JsonObject): String {
            return WSPacket(mtype.name, payload).encode()
        }

        @JvmStatic
        fun encode(mtype: Enum<*>, payload: Payload): String {
            return WSPacket(mtype.name, JsonObject.mapFrom(payload)).encode()
        }
    }
}