package io.github.camposmdev.foursouls.core.api.basement

import io.github.camposmdev.foursouls.core.api.AbstractWSManager
import io.github.camposmdev.foursouls.core.api.message.BasementChatCodec
import io.github.camposmdev.foursouls.core.api.message.BasementGreetingCodec
import io.github.camposmdev.foursouls.core.api.message.BasementMType
import io.github.camposmdev.foursouls.core.api.message.WSPacketFactory
import io.github.camposmdev.foursouls.core.api.message.payload.*
import io.vertx.core.Future
import io.vertx.core.Vertx
import io.vertx.core.json.JsonObject
import java.util.*

class BasementAPI(vertx: Vertx)
    : AbstractWSManager<BasementMType, BasementPayload>(vertx, "basement.api.${UUID.randomUUID()}", BasementAPI::class.java), IBasementAPI {
    private val eb = vertx.eventBus()

    init {
        registerDefaultCodec(BasementGreeting::class.java, BasementGreetingCodec())
        registerDefaultCodec(BasementChat::class.java, BasementChatCodec())
    }

    override fun decodeMessage(mtype: BasementMType, payload: JsonObject) {
        when (mtype) {
            BasementMType.GREETING ->
                eb.publish(sign(mtype), payload.mapTo(BasementGreeting::class.java))
            BasementMType.CHAT ->
                eb.publish(sign(mtype), payload.mapTo(BasementChat::class.java))
            BasementMType.DONE ->
                eb.publish(sign(mtype), payload.mapTo(BasementDone::class.java))
            BasementMType.USERS ->
                eb.publish(sign(mtype), payload.mapTo(BasementUsers::class.java))
            BasementMType.CLOSED ->
                eb.publish(sign(mtype), payload.mapTo(BasementClosed::class.java))
            BasementMType.ERROR ->
                eb.publish(sign(mtype), payload.mapTo(BasementError::class.java))
            else -> {
                log.error("This mtype shouldn't be here: $mtype")
            }
        }
    }

    override fun decodeMType(mtype: String): BasementMType {
        return BasementMType.valueOf(mtype)
    }

    override fun chat(username: String?, message: String): Future<Void> {
        val chat = PayloadFactory.basement().chat(username, message)
        return writeText(WSPacketFactory.basement().chat(chat))
    }

    override fun done(chestId: String): Future<Void> {
        return writeText(WSPacketFactory.basement().done(chestId))
    }

    override fun leave(): Future<Void> {
        return writeText(WSPacketFactory.basement().leave())
    }
}