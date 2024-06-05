package io.github.camposmdev.foursouls.server.basement.model

import io.github.camposmdev.foursouls.model.api.ClientWS
import io.github.camposmdev.foursouls.model.api.basement.BasementUser
import io.github.camposmdev.foursouls.model.api.message.MType
import io.github.camposmdev.foursouls.model.api.message.MessageFactory
import io.github.camposmdev.foursouls.model.api.message.payload.BasementChat
import io.github.camposmdev.foursouls.model.api.message.payload.PayloadFactory
import io.github.camposmdev.foursouls.server.basement.BasementServer
import io.vertx.core.buffer.Buffer
import io.vertx.core.http.ServerWebSocket
import io.vertx.core.json.DecodeException
import io.vertx.core.json.JsonObject

class BasementClientWS(private val ws: ServerWebSocket, userId: String) : ClientWS {
    val state = BasementUser(userId)

    init {
        /* verify userId */
        BasementServer.auth.verify(userId).onSuccess { username ->
            state.username = username
            ws.binaryMessageHandler(::binaryMessageHandler)
            ws.textMessageHandler(::textMessageHandler)
            ws.closeHandler(::closeHandler)
            BasementRegistry.add(this)
            sendBasementGreeting()
        }.onFailure {
            ws.close()
        }
    }

    override fun id(): String {
        return state.id
    }

    override fun binaryMessageHandler(data: Buffer) {
        println("Client[${state.id}]: Sends ${data.length()} bytes...")
    }

    override fun textMessageHandler(text: String) {
        println("Client[${state.id}] says:\n$text")
        try {
            /* try to parse {text} as JsonObject */
            val obj = JsonObject(text)
            val mtype = MType.parse(obj)
            val payload = PayloadFactory.parse(obj)
            decodeMessage(mtype, payload)
        } catch (err: DecodeException) {
            ws.writeTextMessage(MessageFactory.err("Invalid JSON"))
        } catch (err: NullPointerException) {
            ws.writeTextMessage(MessageFactory.err("Invalid MType"))
        } catch (err: IllegalArgumentException) {
            ws.writeTextMessage(MessageFactory.err("Missing 'payload' field"))
        }
    }

    override fun closeHandler(arg0: Void?) {
        BasementRegistry.remove { x -> x.id() == state.id }
        println("Client[${state.id}] disconnected")
    }

    private fun decodeMessage(mtype: MType, payload: JsonObject) {
        when (mtype) {
            MType.BASEMENT_CHAT -> {
                /* relay chat message to all users */
                val chat = payload.mapTo(BasementChat::class.java)
                BasementRegistry.sendChatMessageToAll(chat)
            }
            else -> {
                ws.writeTextMessage(MessageFactory.err("Invalid MType"))
            }
        }
    }

    fun sendBasementChat(chat: BasementChat) {
        ws.writeTextMessage(MessageFactory.basementChat(chat))
    }

    fun sendBasementGreeting() {
        ws.writeTextMessage(MessageFactory.basementGreeting(state.host, BasementRegistry.users()))
    }
}
