package io.github.camposmdev.foursouls.server.chest.impl

import io.github.camposmdev.foursouls.core.api.message.ChestMType
import io.github.camposmdev.foursouls.core.util.ClientRegistry

object ChestRegistry : ClientRegistry<ChestServerWSClient, ChestMType>() {
    private const val SZ = 4
    private var hostId: String? = null

    fun isFull(): Boolean {
        return clients.size >= SZ
    }

    override fun add(client: ChestServerWSClient): Boolean {
        if (isEmpty()) {
            hostId = client.id
            TODO("Not yet implemented")
//            client.state.host = true
        }
        return super.add(client)
    }
}