package io.github.camposmdev.foursouls.server.basement.impl

import io.github.camposmdev.foursouls.core.api.logger.WSLogger
import io.github.camposmdev.foursouls.core.api.basement.BasementUser
import io.github.camposmdev.foursouls.core.api.message.MType
import io.github.camposmdev.foursouls.core.api.message.MType.BASEMENT_CHAT
import io.github.camposmdev.foursouls.core.api.message.MessageFactory
import io.github.camposmdev.foursouls.core.api.message.payload.BasementChat
import io.github.camposmdev.foursouls.core.api.message.payload.PayloadFactory
import io.github.camposmdev.foursouls.core.util.WSClient
import io.github.camposmdev.foursouls.server.basement.BasementServer
import io.vertx.core.buffer.Buffer
import io.vertx.core.http.ServerWebSocket
import io.vertx.core.json.DecodeException
import io.vertx.core.json.JsonObject

class BasementWSClient(private val ws: ServerWebSocket, userId: String) :
    WSClient {
    val state = BasementUser(userId)
    val log = WSLogger(BasementServer::class.java)

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

    override fun id(): String {
        return state.id
    }

    override fun binaryMessageHandler(data: Buffer) {
        println("Client[${state.username}]: Sends ${data.length()} bytes...")
    }

    override fun textMessageHandler(text: String) {
        try {
            /* try to parse {text} as JsonObject */
            val obj = JsonObject(text)
            val mtype = MType.parse(obj)
            val payload = PayloadFactory.parse(obj)
            log.read(BasementServer.NAME, state.username, text)
            decodeMessage(mtype, payload)
        } catch (err: DecodeException) {
            log.error(err)
            sendText(MessageFactory.basement().err(err.toString()))
        } catch (err: NullPointerException) {
            log.error(err)
            sendText(MessageFactory.basement().err(err.toString()))
        } catch (err: IllegalArgumentException) {
            log.error(err)
            sendText(MessageFactory.basement().err(err.toString()))
        }
    }

    override fun closeHandler(arg0: Void?) {
        BasementRegistry.remove { x -> x.id() == state.id }
        log.debug("${state.username} left the basement")
    }

    private fun decodeMessage(mtype: MType, payload: JsonObject) {
        when (mtype) {
            BASEMENT_CHAT -> {
                /* relay chat message to all users */
                val chat = payload.mapTo(BasementChat::class.java)
                BasementRegistry.sendChatMessageToAll(chat)
            }
            else -> {
                sendText(MessageFactory.basement().err("Invalid MType: $mtype"))
            }
        }
    }

    fun chat(chat: BasementChat) {
        val msg = MessageFactory.basement().chat(chat)
        sendText(msg)
    }

    fun greeting() {
        val msg = MessageFactory.basement().greeting(state.host, state.username, BasementRegistry.users())
        sendText(msg)
    }

    override fun sendText(text: String) {
        ws.writeTextMessage(text).onComplete {
            if (it.succeeded()) {
                log.write(BasementServer.NAME, state.username, text)
            } else {
                log.error(it.cause())
            }
        }
    }
}
