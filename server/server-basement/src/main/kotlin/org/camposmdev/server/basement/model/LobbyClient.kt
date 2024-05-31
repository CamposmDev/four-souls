package org.camposmdev.server.basement.model

import io.vertx.core.buffer.Buffer
import io.vertx.core.http.ServerWebSocket
import io.vertx.core.json.DecodeException
import io.vertx.core.json.JsonObject
import org.camposmdev.model.net.WSClient
import org.camposmdev.model.net.message.MType
import org.camposmdev.model.net.message.Message
import java.util.*

class LobbyClient(private val ws: ServerWebSocket, val userId: String) : WSClient {
    private val id: String = UUID.randomUUID().toString()   /* ID of the client */

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
        println("Client[$id]: Sends ${data.length()} bytes")
    }

    override fun textMessageHandler(text: String) {
        println("Client[$id] says:\n$text")
        try {
            /* try to parse {text} as JsonObject */
            val obj = JsonObject(text)
            val mtype = MType.parse(obj)
            val payload = obj.getJsonObject("payload") ?: throw IllegalArgumentException()
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
        println(mtype)
        when (mtype) {
            /* TODO - set name utilizing server-mom GET request? */
            MType.LOCAL_CHAT -> {
                /* send message to lobby */
                val message = payload.getString("message")
                LobbyRegistry.sendLocalChatMessageToAll(message)
            }
            else -> {
                ws.writeTextMessage(Message.err("Invalid MType"))
            }
        }
    }

    fun sendLocalChatMessage(message: String) {
        ws.writeTextMessage(Message.localChat(id, message))
    }

    fun sendHostMessage() {
        ws.writeTextMessage(Message.host())
    }
}
