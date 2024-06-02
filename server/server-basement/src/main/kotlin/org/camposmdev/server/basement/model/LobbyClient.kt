package org.camposmdev.server.basement.model

import io.vertx.core.buffer.Buffer
import io.vertx.core.http.ServerWebSocket
import io.vertx.core.json.DecodeException
import io.vertx.core.json.JsonObject
import org.camposmdev.model.net.WSClient
import org.camposmdev.model.net.message.MType
import org.camposmdev.model.net.message.Message
import org.camposmdev.model.net.message.payload.BasementChat
import org.camposmdev.model.net.message.payload.Payload
import java.util.*

class LobbyClient(private val ws: ServerWebSocket, private val userId: String) : WSClient {
    private val id: String = UUID.randomUUID().toString()   /* ID of the client */
    private var username: String? = null

    init {
        ws.binaryMessageHandler(this::binaryMessageHandler)
        ws.textMessageHandler(this::textMessageHandler)
        ws.closeHandler(this::closeHandler)
        ws.writeTextMessage(Message.greeting())
    }

    override fun id(): String {
        return id
    }

    override fun binaryMessageHandler(data: Buffer) {
        println("Client[$id]: Sends ${data.length()} bytes...")
    }

    override fun textMessageHandler(text: String) {
        println("Client[$id] says:\n$text")
        try {
            /* try to parse {text} as JsonObject */
            val obj = JsonObject(text)
            val mtype = MType.parse(obj)
            val payload = Payload.parse(obj)
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
        LobbyRegistry.remove { x -> x.id == this.id }
        println("Client[$id] disconnected")
    }

    private fun decodeMessage(mtype: MType, payload: JsonObject) {
        when (mtype) {
            MType.BASEMENT_CHAT -> {
                /* send message to lobby */
                val chat = payload.mapTo(BasementChat::class.java)
                LobbyRegistry.sendChatMessageToAll(chat)
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
