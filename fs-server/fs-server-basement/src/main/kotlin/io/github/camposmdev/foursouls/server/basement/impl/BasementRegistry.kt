package io.github.camposmdev.foursouls.server.basement.impl

import io.github.camposmdev.foursouls.core.api.basement.BasementUser
import io.github.camposmdev.foursouls.core.api.message.payload.BasementChat
import io.github.camposmdev.foursouls.core.util.ClientRegistry

object BasementRegistry : ClientRegistry<BasementServerWSManager>() {
    private const val SZ = 4
    private var hostId: String? = null

    fun sendChatMessageToAll(chat: BasementChat) {
        clients.forEach {
            it.chat(chat)
        }
    }

    fun isFull(): Boolean {
        return clients.size >= SZ
    }

    fun users(): List<BasementUser> {
        val lst = mutableListOf<BasementUser>()
        clients.forEach {
            lst.add(it.state)
        }
        return lst
    }

    override fun add(client: BasementServerWSManager): Boolean {
        if (isEmpty()) {
            hostId = client.state.id
            client.state.host = true
        }
        return super.add(client)
    }
}