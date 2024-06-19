package io.github.camposmdev.foursouls.core.api.message

import io.vertx.core.buffer.Buffer
import io.vertx.core.eventbus.MessageCodec
import io.vertx.core.json.Json

abstract class JsonMessageCodec<T> : MessageCodec<T, T> {
    open fun clazz(): Class<T> {
        throw NotImplementedError("Subclasses must implement clazz() method")
    }

    override fun encodeToWire(buffer: Buffer, payload: T) {
        val str = Json.encode(payload)
        buffer.appendInt(str.length)
        buffer.appendString(str)
    }

    override fun decodeFromWire(pos: Int, buffer: Buffer): T {
        val len = buffer.getInt(pos)
        val str = buffer.getString(pos+4, pos+4+len)
        return Json.decodeValue(str, clazz())
    }

    override fun name(): String {
        return clazz().simpleName
    }

    override fun systemCodecID(): Byte {
        return -1
    }

    override fun transform(payload: T): T {
        return payload
    }
}