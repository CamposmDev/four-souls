package io.github.camposmdev.foursouls.server.basement.impl

import io.github.camposmdev.foursouls.core.api.basement.BasementUser
import io.github.camposmdev.foursouls.core.api.logger.WSLogger
import io.github.camposmdev.foursouls.core.api.message.BasementMType
import io.github.camposmdev.foursouls.core.api.message.PacketFactory
import io.github.camposmdev.foursouls.core.api.message.WSPacket
import io.github.camposmdev.foursouls.core.api.message.payload.BasementChat
import io.github.camposmdev.foursouls.core.util.AbstractServerWSClient
import io.github.camposmdev.foursouls.server.basement.BasementServer
import io.vertx.core.Future
import io.vertx.core.Promise
import io.vertx.core.buffer.Buffer
import io.vertx.core.http.ServerWebSocket
import io.vertx.core.json.JsonObject

class BasementServerWSClient(
    ws: ServerWebSocket,
    userId: String
) : AbstractServerWSClient<BasementMType>(ws) {
    private lateinit var log: WSLogger
    val state = BasementUser(id, userId)

    init {
        /* verify userId */
        BasementServer.Auth.verifyId(userId).onSuccess {
            state.username = it
            ws.binaryMessageHandler(::binaryMessageHandler)
            ws.textMessageHandler(::textMessageHandler)
            ws.closeHandler(::closeHandler)
            BasementRegistry.add(this)
            log = WSLogger(BasementServer::class.java).apply {
                debug = true
            }
            log.debug("${state.username} joined the basement")
            greeting()
        }.onFailure {
            log.error(it)
            ws.close()
        }
    }

    override fun binaryMessageHandler(buffer: Buffer) {
        log.readBinary(BasementServer.NAME, state.username, buffer)
    }

    override fun textMessageHandler(text: String) {
        try {
            val obj = WSPacket.decode(text)
            val mtype = BasementMType.valueOf(obj.mtype)
            val payload = obj.payload
            log.readText(BasementServer.NAME, state.username, text)
            decodeMessage(mtype, payload)
        } catch (ex: Exception) {
            ex.printStackTrace()
            sendText(PacketFactory.basement().err(ex.toString()))
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
                sendText(PacketFactory.basement().err("Invalid MType: $mtype"))
            }
        }
    }

    fun chat(chat: BasementChat) {
        val msg = PacketFactory.basement().chat(chat)
        sendText(msg)
    }

    fun greeting() {
        val msg = PacketFactory.basement().greeting(state.host, state.username, BasementRegistry.users())
        sendText(msg)
    }

    override fun sendText(text: String): Future<Void> {
        val promise = Promise.promise<Void>()
        ws.writeTextMessage(text).onSuccess {
            log.writeText(BasementServer.NAME, state.username, text)
            promise.complete()
        }.onFailure {
            it.printStackTrace()
            promise.fail(it)
        }
        return promise.future()
    }
}
