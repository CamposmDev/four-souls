package io.github.camposmdev.foursouls.core.api.logger

import io.github.camposmdev.foursouls.core.api.message.WSPacket
import io.github.camposmdev.foursouls.core.util.logger.Logger
import io.vertx.core.buffer.Buffer
import java.util.concurrent.atomic.AtomicInteger

open class WSLogger : Logger {
    private var seq = AtomicInteger(0)

    constructor(clazz: Class<*>) : super(clazz)
    constructor(name: String) : super(name)

    fun readText(sender: String?, recipient: String?, text: String) {
        val obj = WSPacket.decode(text)
        val mtype = obj.mtype
        val payload = obj.payload
        message("$sender < ($GREEN$mtype$RESET) < $recipient: $GRAY$payload$RESET")
    }

    fun writeText(sender: String?, recipient: String?, text: String) {
        val obj = WSPacket.decode(text)
        val mtype = obj.mtype
        val payload = obj.payload
        message("$sender > ($GREEN$mtype$RESET) > $recipient: $GRAY$payload$RESET")
    }

    fun readBinary(sender: String?, recipient: String?, buffer: Buffer) {
        message("$sender > ($GREEN${buffer.length()}-length bytes$RESET) > $recipient")
    }

    fun receiveText(text: String) {
        val obj = WSPacket.decode(text)
        val mtype = obj.mtype
        val payload = obj.payload
        message("Received ($GREEN$mtype$RESET): $GRAY$payload$RESET")
    }

    fun sendText(text: String) {
        val obj = WSPacket.decode(text)
        val mtype = obj.mtype
        val payload = obj.payload
        message("Sent ($GREEN$mtype$RESET): $GRAY$payload$RESET")
    }

    fun message(data: String) {
        val seq = this.seq.getAndIncrement()
        info("${PURPLE}Seq #$seq$RESET $data")
    }
}
