package io.github.camposmdev.foursouls.server.basement.model

import io.vertx.core.buffer.Buffer
import io.vertx.core.http.ServerWebSocket
import io.vertx.core.json.DecodeException
import io.vertx.core.json.JsonObject
import io.github.camposmdev.foursouls.model.net.BasementUser
import io.github.camposmdev.foursouls.model.net.message.MType
import io.github.camposmdev.foursouls.model.net.message.Message
import io.github.camposmdev.foursouls.model.net.message.payload.BasementChat
import io.github.camposmdev.foursouls.model.net.message.payload.PayloadBuilder

class BasementClient(private val ws: ServerWebSocket, userId: String) :
    io.github.camposmdev.foursouls.model.net.WSClient {
    private val state = BasementUser()

    init {
        state.userId = userId
        ws.binaryMessageHandler(this::binaryMessageHandler)
        ws.textMessageHandler(this::textMessageHandler)
        ws.closeHandler(this::closeHandler)
        ws.writeTextMessage(Message.greeting())
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
            val payload = PayloadBuilder.parse(obj)
            decodeMessage(mtype, payload)
        } catch (err: DecodeException) {
            ws.writeTextMessage(Message.err("Invalid JSON"))
        } catch (err: NullPointerException) {
            ws.writeTextMessage(Message.err("Invalid MType"))
        } catch (err: IllegalArgumentException) {
            ws.writeTextMessage(Message.err("Missing 'payload' field"))
        }
    }

    override fun closeHandler(arg0: Void?) {
        BasementRegistry.remove { x -> x.id() == state.id }
        println("Client[${state.id}] disconnected")
    }

    private fun decodeMessage(mtype: MType, payload: JsonObject) {
        when (mtype) {
            MType.BASEMENT_CHAT -> {
                /* send message to lobby */
                val chat = payload.mapTo(BasementChat::class.java)
                BasementRegistry.sendChatMessageToAll(chat)
            }
            else -> {
                ws.writeTextMessage(Message.err("Invalid MType"))
            }
        }
    }

    fun sendBasementChatMessage(chat: BasementChat) {
        ws.writeTextMessage(Message.basementChat(chat))
    }
}
