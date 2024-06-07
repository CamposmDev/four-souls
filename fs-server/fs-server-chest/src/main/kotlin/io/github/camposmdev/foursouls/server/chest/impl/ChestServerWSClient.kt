package io.github.camposmdev.foursouls.server.chest.impl

import io.github.camposmdev.foursouls.core.api.logger.WSLogger
import io.github.camposmdev.foursouls.core.api.message.ChestMType
import io.github.camposmdev.foursouls.core.api.message.WSPacket
import io.github.camposmdev.foursouls.core.api.message.WSPacketFactory
import io.github.camposmdev.foursouls.core.util.AbstractServerWSClient
import io.vertx.core.buffer.Buffer
import io.vertx.core.http.ServerWebSocket
import io.vertx.core.json.JsonObject

class ChestServerWSClient(
    private val ws: ServerWebSocket,
    userId: String
) : AbstractServerWSClient<ChestMType>() {
    private val log = WSLogger(ChestServerWSClient::class.java)

    init {
        TODO("Verify userId")
        this.ws.binaryMessageHandler(this::binaryMessageHandler)
        this.ws.textMessageHandler(this::textMessageHandler)
        this.ws.closeHandler(this::closeHandler)
        log.debug("${TODO("state.username")} joined the chest")
    }

    override fun binaryMessageHandler(data: Buffer) {
        TODO("Not yet implemented")
    }

    override fun textMessageHandler(text: String) {
        TODO("Not yet implemented")
        try {
            val body = WSPacket.decode(text)
            val mtype = ChestMType.valueOf(body.mtype)
            val payload = body.payload
            TODO("Not yet implemented")
//            log.read(ChestServer.NAME, state.username, text)
            decodeMessage(mtype, payload)
        } catch (ex: Exception) {
            ex.printStackTrace()
            writeText(WSPacketFactory.basement().err(ex.toString()))
        }
    }

    override fun closeHandler(arg0: Void?) {
        ChestRegistry.remove { x -> x.id == this.id }
        log.debug("${TODO("state.username")} left the chest")
    }

    override fun decodeMessage(mtype: ChestMType, payload: JsonObject) {
        TODO("Not yet implemented")
        when (mtype) {
            else -> writeText(WSPacketFactory.basement().err("Invalid MType: $mtype"))
        }
    }

    override fun writeText(text: String?) {
        ws.writeTextMessage(text).onSuccess {
            TODO("Log sent text message")
        }.onFailure {
            it.printStackTrace()
        }
    }
}