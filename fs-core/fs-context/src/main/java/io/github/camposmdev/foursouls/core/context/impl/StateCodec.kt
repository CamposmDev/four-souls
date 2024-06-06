package io.github.camposmdev.foursouls.core.context.impl

import io.github.camposmdev.foursouls.core.context.store.state.BasementState
import io.vertx.core.buffer.Buffer
import io.vertx.core.eventbus.MessageCodec
import io.vertx.core.json.Json

object StateCodec {
    class Basement : MessageCodec<BasementState, BasementState> {
        override fun encodeToWire(buffer: Buffer, payload: BasementState?) {
            val jsonStr = Json.encode(payload)
            buffer.appendInt(jsonStr.length)
            buffer.appendString(jsonStr)
        }

        override fun decodeFromWire(pos: Int, buffer: Buffer): BasementState {
            val length = buffer.getInt(pos)
            val jsonStr = buffer.getString(pos+4,pos+4+length)
            return Json.decodeValue(jsonStr, BasementState::class.java)
        }

        override fun name(): String {
            return javaClass.simpleName
        }

        override fun systemCodecID(): Byte {
            return -1
        }

        override fun transform(payload: BasementState): BasementState {
            return payload
        }
    }
}