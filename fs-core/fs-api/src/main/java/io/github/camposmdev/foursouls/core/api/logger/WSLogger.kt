package io.github.camposmdev.foursouls.core.api.logger

import io.github.camposmdev.foursouls.core.api.message.WSPacket
import io.github.camposmdev.foursouls.core.util.logger.Logger
import io.vertx.core.buffer.Buffer
import java.util.concurrent.atomic.AtomicInteger

class WSLogger(clazz: Class<*>? = null) : Logger(clazz) {
    private var seq = AtomicInteger(0)

    fun read(sender: String?, recipient: String?, text: String) {
        val obj = WSPacket.decode(text)
        val mtype = obj.mtype
        val payload = obj.payload
        message("$sender < ($GREEN$mtype$RESET) < $recipient: $GRAY$payload$RESET")
    }

    fun write(sender: String?, recipient: String?, text: String) {
        val obj = WSPacket.decode(text)
        val mtype = obj.mtype
        val payload = obj.payload
        message("$sender > ($GREEN$mtype$RESET) > $recipient: $GRAY$payload$RESET")
    }

    fun read(sender: String?, recipient: String?, buffer: Buffer) {
        message("$sender > ($GREEN${buffer.length()}-length bytes$RESET) > $recipient")
    }

    fun read(text: String) {
        val obj = WSPacket.decode(text)
        val mtype = obj.mtype
        val payload = obj.payload
        message("Received ($GREEN$mtype$RESET): $GRAY$payload$RESET")
    }

    fun write(text: String) {
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