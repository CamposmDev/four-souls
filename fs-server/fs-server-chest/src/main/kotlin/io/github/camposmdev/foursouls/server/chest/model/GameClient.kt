package io.github.camposmdev.foursouls.server.chest.model

import io.github.camposmdev.foursouls.model.net.WSClient
import io.vertx.core.buffer.Buffer
import io.vertx.core.http.ServerWebSocket
import java.util.*

class GameClient(private val ws: ServerWebSocket) :
    io.github.camposmdev.foursouls.model.net.WSClient {
    private val id: String = UUID.randomUUID().toString()

    init {
        this.ws.binaryMessageHandler(this::binaryMessageHandler)
        this.ws.textMessageHandler(this::textMessageHandler)
        this.ws.closeHandler(this::closeHandler)
    }

    override fun id(): String {
        return id
    }

    override fun binaryMessageHandler(data: Buffer) {
        println("Client[$id]: Sends ${data.length()} bytes")
    }

    override fun textMessageHandler(text: String) {
        println("Client[$id] says:\n$text")
    }

    override fun closeHandler(arg0: Void?) {
        ChestRegistry.remove { x -> x.id == this.id }
        println("Client[$id] disconnected")
    }
}