package io.github.camposmdev.foursouls.server.chest.model

import io.github.camposmdev.foursouls.core.util.ClientRegistry

object ChestRegistry : ClientRegistry<ChestClientWS>() {
    private const val SZ = 4
    private var hostId: String? = null

    fun isFull(): Boolean {
        return clients.size >= SZ
    }

    override fun add(client: ChestClientWS): Boolean {
        if (isEmpty()) {
            hostId = client.id()
//            client.state.host = true
        }
        return super.add(client)
    }
}