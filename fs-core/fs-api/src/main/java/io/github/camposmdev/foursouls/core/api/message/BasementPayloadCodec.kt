package io.github.camposmdev.foursouls.core.api.message

import io.github.camposmdev.foursouls.core.api.message.payload.BasementChat
import io.github.camposmdev.foursouls.core.api.message.payload.BasementGreeting
import io.vertx.core.buffer.Buffer
import io.vertx.core.eventbus.MessageCodec
import io.vertx.core.json.Json

class BasementGreetingCodec : MessageCodec<BasementGreeting, BasementGreeting> {
    override fun encodeToWire(buffer: Buffer, payload: BasementGreeting?) {
        val jsonStr = Json.encode(payload)
        buffer.appendInt(jsonStr.length)
        buffer.appendString(jsonStr)
    }

    override fun decodeFromWire(pos: Int, buffer: Buffer): BasementGreeting {
        val length = buffer.getInt(pos)
        val jsonStr = buffer.getString(pos + 4, pos + 4 + length)
        return Json.decodeValue(jsonStr, BasementGreeting::class.java)
    }

    override fun name(): String {
        return javaClass.simpleName
    }

    override fun systemCodecID(): Byte {
        return -1
    }

    override fun transform(payload: BasementGreeting): BasementGreeting {
        return payload
    }
}

class BasementChatCodec : MessageCodec<BasementChat, BasementChat> {
    override fun encodeToWire(buffer: Buffer, payload: BasementChat?) {
        val jsonStr = Json.encode(payload)
        buffer.appendInt(jsonStr.length)
        buffer.appendString(jsonStr)
    }

    override fun decodeFromWire(pos: Int, buffer: Buffer): BasementChat {
        val length = buffer.getInt(pos)
        val jsonStr = buffer.getString(pos + 4, pos + 4 + length)
        return Json.decodeValue(jsonStr, BasementChat::class.java)
    }

    override fun name(): String {
        return javaClass.simpleName
    }

    override fun systemCodecID(): Byte {
        return -1
    }

    override fun transform(payload: BasementChat): BasementChat {
        return payload
    }
}
