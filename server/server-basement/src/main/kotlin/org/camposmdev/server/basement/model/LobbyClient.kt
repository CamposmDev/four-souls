package org.camposmdev.server.basement.model

import io.vertx.core.buffer.Buffer
import io.vertx.core.http.ServerWebSocket
import io.vertx.core.json.DecodeException
import io.vertx.core.json.JsonObject
import org.camposmdev.model.net.WSClient
import org.camposmdev.model.net.message.MType
import org.camposmdev.model.net.message.Message
import java.util.*

class LobbyClient(private val ws: ServerWebSocket, host: Boolean = false) : WSClient {
    private val id: String = UUID.randomUUID().toString()
    private var displayName: String? = null

    init {
        ws.binaryMessageHandler(this::binaryMessageHandler)
        ws.textMessageHandler(this::textMessageHandler)
        ws.closeHandler(this::closeHandler)
        ws.writeTextMessage(JsonObject.of("mtype", MType.GREETING.name,
            "payload", JsonObject.of("id", id, "host", host)).toString())
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
            val mtype = MType.valueOf(obj.getString("mtype"))
            val payload = obj.getJsonObject("payload") ?: throw IllegalArgumentException()
            decodeMessage(mtype, payload)
        } catch (err: DecodeException) {
            ws.writeTextMessage(Message.error("Invalid JSON"))
        } catch (err: NullPointerException) {
            ws.writeTextMessage(Message.error("Invalid MType"))
        } catch (err: IllegalArgumentException) {
            ws.writeTextMessage(Message.error("Missing 'payload' field"))
        }
    }

    override fun closeHandler(arg0: Void?) {
        LobbyRegistry.remove { x -> x.id == this.id }
        println("Client[$id] disconnected")
    }

    private fun decodeMessage(mtype: MType, payload: JsonObject) {
        println(mtype)
        when (mtype) {
            MType.DISPLAY_NAME -> {
                /* update display name */
            }
            MType.L_CHAT -> {
                /* send message to lobby */
            }
            else -> {
                ws.writeTextMessage(Message.error("Invalid MType"))
            }
        }
    }
}
