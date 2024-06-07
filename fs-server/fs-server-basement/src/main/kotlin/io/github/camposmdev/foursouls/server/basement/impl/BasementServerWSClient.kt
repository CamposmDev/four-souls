package io.github.camposmdev.foursouls.server.basement.impl

import io.github.camposmdev.foursouls.core.api.basement.BasementUser
import io.github.camposmdev.foursouls.core.api.logger.WSLogger
import io.github.camposmdev.foursouls.core.api.message.BasementMType
import io.github.camposmdev.foursouls.core.api.message.WSPacket
import io.github.camposmdev.foursouls.core.api.message.WSPacketFactory
import io.github.camposmdev.foursouls.core.api.message.payload.BasementChat
import io.github.camposmdev.foursouls.core.util.AbstractServerWSClient
import io.github.camposmdev.foursouls.server.basement.BasementServer
import io.vertx.core.buffer.Buffer
import io.vertx.core.http.ServerWebSocket
import io.vertx.core.json.JsonObject

class BasementServerWSClient(
    private val ws: ServerWebSocket,
    userId: String
) : AbstractServerWSClient<BasementMType>() {
    val state = BasementUser(id, userId)
    private val log = WSLogger(BasementServer::class.java)

    init {
        /* verify userId */
        BasementServer.auth.verify(userId).onSuccess { username ->
            state.username = username
            ws.binaryMessageHandler(::binaryMessageHandler)
            ws.textMessageHandler(::textMessageHandler)
            ws.closeHandler(::closeHandler)
            BasementRegistry.add(this)
            log.debug("${state.username} joined the basement")
            greeting()
        }.onFailure {
            ws.close()
        }
    }

    override fun binaryMessageHandler(buffer: Buffer) {
        log.read(BasementServer.NAME, state.username, buffer)
    }

    override fun textMessageHandler(text: String) {
        try {
            val obj = WSPacket.decode(text)
            val mtype = BasementMType.valueOf(obj.mtype)
            val payload = obj.payload
            log.read(BasementServer.NAME, state.username, text)
            decodeMessage(mtype, payload)
        } catch (ex: Exception) {
            ex.printStackTrace()
            writeText(WSPacketFactory.basement().err(ex.toString()))
        }
    }

    override fun closeHandler(arg0: Void?) {
        BasementRegistry.remove { x -> x.state.id == state.id }
        log.debug("${state.username} left the basement")
    }

    override fun decodeMessage(mtype: BasementMType, payload: JsonObject) {
        when (mtype) {
            BasementMType.CHAT -> {
                /* relay chat message to all users */
                val chat = payload.mapTo(BasementChat::class.java)
                BasementRegistry.sendChatMessageToAll(chat)
            }
            else -> {
                writeText(WSPacketFactory.basement().err("Invalid MType: $mtype"))
            }
        }
    }

    fun chat(chat: BasementChat) {
        val msg = WSPacketFactory.basement().chat(chat)
        writeText(msg)
    }

    fun greeting() {
        val msg = WSPacketFactory.basement().greeting(state.host, state.username, BasementRegistry.users())
        writeText(msg)
    }

    override fun writeText(text: String) {
        ws.writeTextMessage(text).onSuccess {
            log.write(BasementServer.NAME, state.username, text)
        }.onFailure {
            it.printStackTrace()
        }
    }
}
