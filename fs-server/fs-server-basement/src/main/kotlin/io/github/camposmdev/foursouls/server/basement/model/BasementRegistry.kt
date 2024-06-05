package io.github.camposmdev.foursouls.server.basement.model

import io.github.camposmdev.foursouls.model.api.ClientRegistry
import io.github.camposmdev.foursouls.model.api.basement.BasementUser
import io.github.camposmdev.foursouls.model.api.message.payload.BasementChat

object BasementRegistry : ClientRegistry<BasementClientWS>() {
    private const val SZ = 4
    var hostId: String? = null

    fun sendChatMessageToAll(chat: BasementChat) {
        clients.forEach {
            it.sendBasementChat(chat)
        }
    }

    fun isFull(): Boolean {
        return clients.size > SZ
    }

    fun users(): List<BasementUser> {
        val lst = mutableListOf<BasementUser>()
        clients.forEach {
            lst.add(it.state)
        }
        return lst
    }

    override fun add(client: BasementClientWS): Boolean {
        if (isEmpty()) {
            hostId = client.id()
            client.state.host = true
        }
        return super.add(client)
    }
}