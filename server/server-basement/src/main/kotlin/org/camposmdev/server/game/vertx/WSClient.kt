package org.camposmdev.server.game.vertx

import io.vertx.core.buffer.Buffer
import io.vertx.core.http.ServerWebSocket
import org.camposmdev.server.game.model.ClientRegistry
import java.util.*

class WSClient(private val ws: ServerWebSocket) {
    val id: String = UUID.randomUUID().toString()

    init {
        this.ws.binaryMessageHandler(this::binaryMessageHandler)
        this.ws.textMessageHandler(this::textMessageHandler)
        this.ws.closeHandler(this::closeHandler)
    }

    private fun binaryMessageHandler(data: Buffer) {
        println("Client[$id]: Sends ${data.length()} bytes")
    }

    private fun textMessageHandler(text: String) {
        println("Client[$id] says:\n$text")
    }

    private fun closeHandler(arg0: Void) {
        ClientRegistry.remove { x -> x.id == this.id }
        println("Client[$id] disconnected")
    }
}