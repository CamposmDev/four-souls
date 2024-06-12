package io.github.camposmdev.foursouls.core.api.message

import io.github.camposmdev.foursouls.core.api.basement.BasementUser
import io.github.camposmdev.foursouls.core.api.chest.ChestUser
import io.github.camposmdev.foursouls.core.api.message.payload.BasementChat
import io.github.camposmdev.foursouls.core.api.message.payload.ChestPayloadFactory
import io.github.camposmdev.foursouls.core.api.message.payload.PayloadFactory

/**
 * Builds WSPackets
 */
object PacketFactory {
    fun err(message: String): String {
        val payload = PayloadFactory.message(message)
        return WSPacket.encode(MType.ERROR.name, payload)
    }

    fun basement(): BasementPacketFactory {
        return BasementPacketFactory
    }

    fun chest(): ChestPacketFactory {
        return ChestPacketFactory
    }
}

object BasementPacketFactory {
    fun err(message: String): String {
        val payload = PayloadFactory.message(message)
        return WSPacket.encode(BasementMType.ERROR, payload)
    }
    fun greeting(host: Boolean, username: String?, users: List<BasementUser>): String {
        val payload = PayloadFactory.basement().greeting(host, username, users)
        return WSPacket.encode(BasementMType.GREETING, payload)
    }

    fun chat(chat: BasementChat): String {
        return WSPacket.encode(BasementMType.CHAT, chat)
    }

    fun done(chestId: String): String {
        val payload = PayloadFactory.basement().done(chestId)
        return WSPacket.encode(BasementMType.DONE, payload)
    }

    fun leave(): String {
        val payload = PayloadFactory.basement().leave()
        return WSPacket.encode(BasementMType.LEAVE, payload)
    }
}

object ChestPacketFactory {
    fun greeting(host: Boolean, username: String?, users: List<ChestUser>): String {
        val payload = ChestPayloadFactory.greeting(host, username, users)
        return WSPacket.encode(ChestMType.GREETING, payload)
    }

    fun err(message: String): String {
        val payload = PayloadFactory.message(message)
        return WSPacket.encode(ChestMType.ERROR, payload)
    }
}