package io.github.camposmdev.foursouls.core.api.logger

import io.github.camposmdev.foursouls.core.api.message.MType
import io.github.camposmdev.foursouls.core.api.message.payload.PayloadFactory
import io.github.camposmdev.foursouls.core.util.logger.Logger
import io.vertx.core.json.JsonObject
import java.util.concurrent.atomic.AtomicInteger

class WSLogger(clazz: Class<*>? = null) : Logger(clazz) {
    private var seq = AtomicInteger(0)

    fun read(sender: String?, recipient: String?, text: String) {
        val obj = JsonObject(text)
        val mtype = MType.parse(obj)
        val payload = PayloadFactory.parse(obj)
        message("$sender < ($GREEN$mtype$RESET) < $recipient: $GRAY$payload$RESET")
    }

    fun write(sender: String?, recipient: String?, text: String) {
        val obj = JsonObject(text)
        val mtype = MType.parse(obj)
        val payload = PayloadFactory.parse(obj)
        message("$sender > ($GREEN$mtype$RESET) > $recipient: $GRAY$payload$RESET")
    }

    fun read(text: String) {
        val obj = JsonObject(text)
        val mtype = MType.parse(obj)
        val payload = PayloadFactory.parse(obj)
        message("Received ($GREEN$mtype$RESET): $GRAY$payload$RESET")
    }

    fun write(text: String) {
        val obj = JsonObject(text)
        val mtype = MType.parse(obj)
        val payload = PayloadFactory.parse(obj)
        message("Sent ($GREEN$mtype$RESET): $GRAY$payload$RESET")
    }

    fun message(data: String) {
        val seq = this.seq.getAndIncrement()
        info("${PURPLE}Seq #$seq$RESET $data")
    }
}
